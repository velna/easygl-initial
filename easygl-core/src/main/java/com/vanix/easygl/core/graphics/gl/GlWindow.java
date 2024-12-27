package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.event.WindowRefreshListener;
import com.vanix.easygl.core.graphics.event.WindowResizeListener;
import com.vanix.easygl.core.graphics.InputController;
import com.vanix.easygl.core.graphics.Window;
import com.vanix.easygl.commons.event.ListenerKey;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Slf4j
public class GlWindow implements Window {

    private final static ListenerKey<WindowResizeListener> WindowResizeKey = ListenerKey.of(0);
    private final static ListenerKey<WindowRefreshListener> WindowRefreshKey = ListenerKey.of(1);
    private final long handle;
    private final ListenerSupport listenerSupport = new ListenerSupport();
    private final InputController inputCtlr;

    private int width;
    private int height;

    private int frameBufferWidth;
    private int frameBufferHeight;
    private String title;

    public GlWindow(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.out));
        this.handle = glfwCreateWindow(width, height, title, NULL, NULL);
        bind();
        GL.createCapabilities();
        glfwSetFramebufferSizeCallback(handle, this::onWindowResized);
        onWindowResized(handle, width, height);
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
    public void swapBuffers() {
        glfwSwapBuffers(handle);
    }

    @Override
    public void pollEvents() {
        glfwPollEvents();
    }

    @Override
    public InputController inputCtlr() {
        return inputCtlr;
    }

    @Override
    public long nativeHandle() {
        return handle;
    }

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    @Override
    public void shouldClose(boolean close) {
        glfwSetWindowShouldClose(handle, close);
    }

    @Override
    public Window bind() {
        glfwMakeContextCurrent(handle);
        return this;
    }

    @Override
    public Window unbind() {
        glfwMakeContextCurrent(NULL);
        return this;
    }

    @Override
    public Window assertBinding() throws IllegalStateException {
        if (glfwGetCurrentContext() != handle) {
            throw new IllegalStateException("Window not bind.");
        }
        return this;
    }

    @Override
    public void dispose() {
        glfwDestroyWindow(handle);
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
}
