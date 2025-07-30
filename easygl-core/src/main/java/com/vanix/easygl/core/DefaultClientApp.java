package com.vanix.easygl.core;

import com.vanix.easygl.commons.Ticket;
import com.vanix.easygl.commons.util.Counters;
import com.vanix.easygl.commons.value.DoubleValue;
import com.vanix.easygl.commons.value.Value;
import com.vanix.easygl.core.graphics.*;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DefaultClientApp implements ClientApp {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected final ClientFactory clientFactory;
    protected final Counters counters = new Counters();
    protected Graphics graphics;
    protected Window window;
    private final Map<String, Renderer<DefaultClientApp, RenderContext>> renderers = new ListOrderedMap<>();
    protected final Ticket ticket;

    private boolean shutdown;

    public DefaultClientApp(String id, ClientFactory clientFactory) {
        this.clientFactory = ClientFactory.Instance = clientFactory;
        ticket = new Ticket(id, 100);
        new Thread(this::mainLoop, "Loop").start();
        Thread counterThread = new Thread(this::counterLoop, "Counters");
        counterThread.setDaemon(true);
        counterThread.start();
    }

    private void mainLoop() {
        while (!shutdown) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
        Ticket.shutdown();
    }

    private void counterLoop() {
        while (true) {
            counters.snapshot();
            counters.print(System.out);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    protected void initGraphics() throws GraphicsException {
        log.info("Client application startup.");
        clientFactory.initWindowSystem();
        graphics = clientFactory.createGraphics();
        window = clientFactory.createMainWindow().bind();
        graphics.viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());
    }

    public void run() throws GraphicsException {
        initGraphics();
        initRenderers();
        DoubleValue tickDelta = Value.of(0.0);
        RenderContext context = clientFactory.createRenderContext(this, window, graphics, tickDelta);
        Counters.Counter fps = counters.create("client.core", "fps", 0);
        long lastFrame = System.currentTimeMillis();
        while (!window.shouldClose()) {
            window.pollEvents();
            long frameStart = System.currentTimeMillis();
            updateAndRender(context);
            fps.incr();
            if (ticket.tick() < 0) {
                log.info("ticket shutdown");
                break;
            }
            tickDelta.set((System.currentTimeMillis() - lastFrame) / 1000.0);
            lastFrame = frameStart;
        }
    }

    private void updateAndRender(RenderContext context) {
        var error = graphics.getError();
        if (error.isError()) {
            log.error(String.format("GL error: %s", error));
        }
        renderers.forEach((id, renderer) -> {
            try {
                if (renderer.isActive()) {
                    renderer.render(context);
                }
            } catch (GraphicsException e) {
                log.error("Render error: ", e);
            }
        });
        window.swapBuffers();
    }

    public void close() {
        closeRenderers();
        log.info("window close");
        shutdown = true;
        if (window != null) {
            window.close();
        }
        clientFactory.terminateWindowSystem();
        log.info("Client application shutdown.");
    }

    protected void initRenderers() {
        renderers.forEach((id, renderer) -> {
            try {
                renderer.init(this);
            } catch (GraphicsException e) {
                log.error("Renderer init error: ", e);
            }
        });
    }

    protected void closeRenderers() {
        renderers.forEach((id, renderer) -> {
            try {
                renderer.close();
            } catch (Exception e) {
                log.error("Error close renderer: ", e);
            }
        });
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void registerRenderer(Renderer<? extends ClientApp, ? extends RenderContext> renderer) {
        this.renderers.put(renderer.id(), (Renderer) renderer);
    }

    @Override
    public Window window() {
        return window;
    }

    @Override
    public Graphics graphics() {
        return graphics;
    }

    @Override
    public Ticket ticket() {
        return ticket;
    }

    @Override
    public Counters counters() {
        return counters;
    }
}
