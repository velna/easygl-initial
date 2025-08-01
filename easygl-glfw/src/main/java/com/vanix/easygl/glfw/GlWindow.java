package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.event.ListenerKey;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.window.event.WindowRefreshListener;
import com.vanix.easygl.core.window.event.WindowResizeListener;
import com.vanix.easygl.core.window.InputController;
import com.vanix.easygl.core.window.Window;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class GlWindow extends AbstractBindable<BindTarget.Default<Window>, Window> implements Window {
    static {
        GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.out));
    }

    protected static final BindTarget.Default<Window> Target = new BindTarget.Default<>(
            BindingState.<BindTarget.Default<Window>, Window>ofLong("Window", GLFW::glfwMakeContextCurrent)
    );

    private static final AtomicBoolean INIT = new AtomicBoolean();
    private static final ListenerKey<WindowResizeListener> WindowResizeKey = ListenerKey.of(0);
    private static final ListenerKey<WindowRefreshListener> WindowRefreshKey = ListenerKey.of(1);
    private final ListenerSupport listenerSupport = new ListenerSupport();
    private final InputController inputCtlr;

    private int width;
    private int height;

    private int frameBufferWidth;
    private int frameBufferHeight;
    private final String title;

    protected GlWindow(Object... args) {
        super(GLFW.glfwCreateWindow((Integer) args[0], (Integer) args[1], (String) args[2], MemoryUtil.NULL, MemoryUtil.NULL),
                Target,
                org.lwjgl.glfw.GLFW::glfwDestroyWindow);
        this.width = (Integer) args[0];
        this.height = (Integer) args[1];
        this.title = (String) args[2];
        bind();
        //FIXME:
        GL.createCapabilities();
        var prevCallback = GLFW.glfwSetFramebufferSizeCallback(handle(), this::onWindowResized);
        if (prevCallback != null) {
            prevCallback.close();
        }
        onWindowResized(handle(), width, height);
        inputCtlr = new GlInputController(this);
    }

    private void onWindowResized(long window, int width, int height) {
        log.info("window resized to {},{}", width, height);
        this.width = width;
        this.height = height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer bWidth = stack.callocInt(1);
            IntBuffer bHeight = stack.callocInt(1);
            org.lwjgl.glfw.GLFW.glfwGetFramebufferSize(window, bWidth, bHeight);
            frameBufferWidth = bWidth.get();
            frameBufferHeight = bHeight.get();
        }
        listenerSupport.forEach(WindowResizeKey, l -> l.onWindowResized(this));
    }

    @Override
    public Window swapBuffers() {
        GLFW.glfwSwapBuffers(handle());
        return this;
    }

    @Override
    public Window pollEvents() {
        GLFW.glfwPollEvents();
        return this;
    }

    @Override
    public InputController inputCtlr() {
        return inputCtlr;
    }

    @Override
    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(handle());
    }

    @Override
    public void shouldClose(boolean close) {
        GLFW.glfwSetWindowShouldClose(handle(), close);
    }

    @Override
    public ListenerOperation<WindowResizeListener> onResize() {
        return listenerSupport.of(WindowResizeKey);
    }

    @Override
    public ListenerOperation<WindowRefreshListener> onRefresh() {
        return listenerSupport.of(WindowRefreshKey);
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public int frameBufferWidth() {
        return frameBufferWidth;
    }

    @Override
    public int frameBufferHeight() {
        return frameBufferHeight;
    }

    public static void systemInit() {
        if (INIT.compareAndSet(false, true)) {
            GLFW.glfwInit();
        }
    }

    public static void systemTerminate() {
        if (INIT.compareAndSet(true, false)) {
            GLFW.glfwTerminate();
        }
    }
}
