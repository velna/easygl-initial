package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.Dimension;
import com.vanix.easygl.commons.Position;
import com.vanix.easygl.commons.event.EventListener;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.input.InputController;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.event.WindowPositionListener;
import com.vanix.easygl.core.window.event.WindowRefreshListener;
import com.vanix.easygl.core.window.event.WindowResizeListener;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class GlWindow extends AbstractBindable<BindTarget.Default<Window>, Window> implements Window {
    private final ListenerSupport<WindowResizeListener> resizeListeners;
    private final ListenerSupport<WindowRefreshListener> refreshListeners;
    private final ListenerSupport<WindowPositionListener> positionListeners;
    private final List<Runnable> closeRunnableList = new ArrayList<>();
    private InputController inputController;
    private int x;
    private int y;
    private int width;
    private int height;
    private int frameBufferWidth;
    private int frameBufferHeight;
    private String title;

    protected GlWindow(int width, int height, String title) {
        super(GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL),
                Target,
                org.lwjgl.glfw.GLFW::glfwDestroyWindow);
        this.width = width;
        this.height = height;
        this.title = title;
        resizeListeners = autoListenerSupport(1, GLFW::glfwSetFramebufferSizeCallback, this::fireWindowResized);
        positionListeners = autoListenerSupport(1, GLFW::glfwSetWindowPosCallback, this::fireWindowPosition);
        refreshListeners = newListenerSupport(1, GLFW::glfwSetWindowRefreshCallback, this::fireWindowRefresh);
        fireWindowResized(handle(), width, height);
    }

    private <C> void setCallback(BiFunction<Long, C, NativeResource> setter, C callback) {
        NativeResource closeable = setter.apply(handle, callback);
        if (closeable != null) {
            closeable.close();
        }
    }

    <T extends EventListener, C> ListenerSupport<T> autoListenerSupport(int maxKeys, BiFunction<Long, C, NativeResource> setter, C callback) {
        setCallback(setter, callback);
        closeRunnableList.add(() -> setCallback(setter, null));
        return new ListenerSupport<>(maxKeys);
    }

    <T extends EventListener, C> ListenerSupport<T> newListenerSupport(int maxKeys, BiFunction<Long, C, NativeResource> setter, C callback) {
        return new ListenerSupport<>(maxKeys,
                () -> setCallback(setter, callback),
                () -> setCallback(setter, null));
    }

    private void fireWindowResized(long window, int width, int height) {
        this.width = width;
        this.height = height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer bWidth = stack.callocInt(1);
            IntBuffer bHeight = stack.callocInt(1);
            org.lwjgl.glfw.GLFW.glfwGetFramebufferSize(window, bWidth, bHeight);
            frameBufferWidth = bWidth.get();
            frameBufferHeight = bHeight.get();
        }
        resizeListeners.forEach(0, l -> l.windowOnResize(this));
    }

    private void fireWindowPosition(long window, int x, int y) {
        this.x = x;
        this.y = y;
        positionListeners.forEach(0, l -> l.windowOnPosition(this));
    }

    private void fireWindowRefresh(long window) {
        refreshListeners.forEach(0, l -> l.windowOnRefresh(this));
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
    public InputController inputs() {
        if (inputController == null) {
            inputController = new GlInputController(this);
        }
        return inputController;
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
        return resizeListeners.listen();
    }

    @Override
    public ListenerOperation<WindowRefreshListener> onRefresh() {
        return refreshListeners.listen();
    }

    @Override
    public ListenerOperation<WindowPositionListener> onPosition() {
        return positionListeners.listen();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setWidth(int w) {
        GLFW.glfwSetWindowSize(handle, w, height);
    }

    @Override
    public void setHeight(int h) {
        GLFW.glfwSetWindowSize(handle, width, h);
    }

    @Override
    public void setDimension(Dimension dimension) {
        GLFW.glfwSetWindowSize(handle, dimension.getWidth(), dimension.getHeight());
    }

    @Override
    public int getXAsInt() {
        return x;
    }

    @Override
    public int getYAsInt() {
        return y;
    }

    @Override
    public void setX(int v) {
        GLFW.glfwSetWindowPos(handle, v, this.y);
    }

    @Override
    public void setY(int v) {
        GLFW.glfwSetWindowPos(handle, this.x, v);
    }

    @Override
    public void setPosition(Position position) {
        GLFW.glfwSetWindowPos(handle, (int) position.getX(), (int) position.getY());
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        GLFW.glfwSetWindowTitle(handle, title);
        this.title = title;
    }

    @Override
    public int frameBufferWidth() {
        return frameBufferWidth;
    }

    @Override
    public int frameBufferHeight() {
        return frameBufferHeight;
    }

    @Override
    public void close() {
        super.close();
        closeRunnableList.forEach(Runnable::run);
    }
}
