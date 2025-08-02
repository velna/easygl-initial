package com.vanix.easygl.core;

import com.vanix.easygl.commons.value.DoubleValue;
import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.window.Window;

import java.util.ServiceLoader;


public class ClientFactory {

    static ClientFactory Instance = new ClientFactory();

    public Graphics createGraphics() {
        return ServiceLoader.load(Graphics.class).findFirst().orElseThrow();
    }

    public Window createMainWindow() {
        return Window.of(800, 600, "Demo");
    }

    public RenderContext createRenderContext(ClientApp clientApp, Window window, Graphics graphics, DoubleValue tickDelta) {
        return new DefaultRenderContext(window, graphics, tickDelta);
    }

    public static ClientFactory get() {
        return Instance;
    }
}
