package com.vanix.easygl.core;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.graphics.gl.GlGraphics;
import com.vanix.easygl.commons.value.DoubleValue;

import static org.lwjgl.glfw.GLFW.*;

public class ClientFactory {

    static ClientFactory Instance = new ClientFactory();

    public Graphics createGraphics() {
        return new GlGraphics();
    }

    public Window createMainWindow() {
        return Window.of(800, 600, "Demo");
    }

    public RenderContext createRenderContext(ClientApp clientApp, Window window, Graphics graphics, DoubleValue tickDelta) {
        return new DefaultRenderContext(window, graphics, tickDelta);
    }

    public void initWindowSystem() throws GraphicsException {
        if (!glfwInit()) {
            throw new GraphicsException("glfwInit error.");
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API);
        glfwWindowHint(GLFW_CONTEXT_CREATION_API, GLFW_NATIVE_CONTEXT_API);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
    }

    public void terminateWindowSystem() {
        glfwTerminate();
    }

    public static ClientFactory get() {
        return Instance;
    }
}
