package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.Dimension;
import com.vanix.easygl.commons.Position;
import com.vanix.easygl.commons.event.EventListener;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.input.InputController;
import com.vanix.easygl.core.window.Monitor;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.event.*;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.lwjgl.glfw.GLFW.*;

public class GlWindow extends AbstractBindable<BindTarget.Default<Window>, Window> implements Window {
    private final ListenerSupport<WindowResizeListener> resizeListeners;
    private final ListenerSupport<WindowRefreshListener> refreshListeners;
    private final ListenerSupport<WindowPositionListener> positionListeners;
    private final ListenerSupport<WindowCloseListener> closeListeners;
    private final ListenerSupport<WindowContentScaleListener> contentScaleListeners;
    private final ListenerSupport<WindowFocusListener> focusListeners;
    private final ListenerSupport<WindowIconifyListener> iconifyListeners;
    private final ListenerSupport<WindowMaximizeListener> maximizeListeners;
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
        closeListeners = newListenerSupport(1, GLFW::glfwSetWindowCloseCallback, this::fireWindowClose);
        contentScaleListeners = newListenerSupport(1, GLFW::glfwSetWindowContentScaleCallback, this::fireWindowContentScale);
        focusListeners = newListenerSupport(1, GLFW::glfwSetWindowFocusCallback, this::fireWindowFocus);
        iconifyListeners = newListenerSupport(1, GLFW::glfwSetWindowIconifyCallback, this::fireWindowIconify);
        maximizeListeners = newListenerSupport(1, GLFW::glfwSetWindowMaximizeCallback, this::fireWindowMaximize);
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
        var event = new WindowResizeEvent(this, width, height);
        resizeListeners.forEach(0, l -> l.windowOnResize(event));
    }

    private void fireWindowPosition(long window, int x, int y) {
        this.x = x;
        this.y = y;
        var event = new WindowPositionEvent(this, x, y);
        positionListeners.forEach(0, l -> l.windowOnPosition(event));
    }

    private void fireWindowRefresh(long window) {
        var event = new WindowEvent(this);
        refreshListeners.forEach(0, l -> l.windowOnRefresh(event));
    }

    private void fireWindowClose(long window) {
        var event = new WindowEvent(this);
        closeListeners.forEach(0, l -> l.windowOnClose(event));
    }

    private void fireWindowContentScale(long window, float xScale, float yScale) {
        var event = new WindowContentScaleEvent(this, xScale, yScale);
        contentScaleListeners.forEach(0, l -> l.windowOnContentScale(event));
    }

    private void fireWindowFocus(long window, boolean focus) {
        var event = new WindowFocusEvent(this, focus);
        focusListeners.forEach(0, l -> l.windowOnFocus(event));
    }

    private void fireWindowIconify(long window, boolean iconify) {
        var event = new WindowIconifyEvent(this, iconify);
        iconifyListeners.forEach(0, l -> l.windowOnIconify(event));
    }

    private void fireWindowMaximize(long window, boolean maximize) {
        var event = new WindowMaximizeEvent(this, maximize);
        maximizeListeners.forEach(0, l -> l.windowOnMaximize(event));
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

    @Override
    public Window focus() {
        glfwFocusWindow(handle);
        return this;
    }

    @Override
    public Window hide() {
        glfwHideWindow(handle);
        return this;
    }

    @Override
    public Window show() {
        glfwShowWindow(handle);
        return this;
    }

    @Override
    public float opacity() {
        return glfwGetWindowOpacity(handle);
    }

    @Override
    public Window opacity(float value) {
        glfwSetWindowOpacity(handle, value);
        return this;
    }

    @Override
    public Window iconify() {
        glfwIconifyWindow(handle);
        return this;
    }

    @Override
    public Window maximize() {
        glfwMaximizeWindow(handle);
        return this;
    }

    @Override
    public Window requestAttention() {
        glfwRequestWindowAttention(handle);
        return this;
    }

    @Override
    public Window restore() {
        glfwRestoreWindow(handle);
        return this;
    }

    @Override
    public Vector2f contentScale() {
        float[] x = new float[1];
        float[] y = new float[1];
        glfwGetMonitorContentScale(handle, x, y);
        return new Vector2f(x[0], y[0]);
    }

    @Override
    public Monitor monitor() {
        long monitor = glfwGetWindowMonitor(handle);
        return monitor == MemoryUtil.NULL ? null : GlMonitor.of(monitor);
    }

    @Override
    public Window monitor(Monitor monitor, int x, int y, int width, int height, int refreshRate) {
        glfwSetWindowMonitor(handle, monitor == null ? MemoryUtil.NULL : monitor.handle(), x, y, width, height, refreshRate);
        return this;
    }
}
