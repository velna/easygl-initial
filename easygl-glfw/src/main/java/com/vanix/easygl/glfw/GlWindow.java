package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.event.EventListener;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.input.InputController;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.event.WindowRefreshListener;
import com.vanix.easygl.core.window.event.WindowResizeListener;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;

import java.nio.IntBuffer;
import java.util.function.BiFunction;

@Slf4j
public class GlWindow extends AbstractBindable<BindTarget.Default<Window>, Window> implements Window {
    static {
        GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.out));
    }

    protected static final BindTarget.Default<Window> Target = new BindTarget.Default<>(
            BindingState.<BindTarget.Default<Window>, Window>ofLong("Window", GLFW::glfwMakeContextCurrent)
    );

    private final ListenerSupport<WindowResizeListener> resizeListeners;
    private final ListenerSupport<WindowRefreshListener> refreshListeners;
    private final InputController inputController;

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
        inputController = new GlInputController(this);
        resizeListeners = newListenerSupport(1, GLFW::glfwSetFramebufferSizeCallback, this::onWindowResized);
        refreshListeners = newListenerSupport(1, GLFW::glfwSetWindowRefreshCallback, this::onWindowRefresh);
        onWindowResized(handle(), width, height);
    }

    private <C> void setCallback(BiFunction<Long, C, NativeResource> setter, C callback) {
        NativeResource closeable = setter.apply(handle, callback);
        if (closeable != null) {
            closeable.close();
        }
    }

    <T extends EventListener, C> ListenerSupport<T> newListenerSupport(int maxKeys, BiFunction<Long, C, NativeResource> setter, C callback) {
        return new ListenerSupport<>(maxKeys,
                () -> setCallback(setter, callback),
                () -> setCallback(setter, null));
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
        resizeListeners.forEach(0, l -> l.windowOnResize(this));
    }

    private void onWindowRefresh(long window) {
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
    public InputController inputCtlr() {
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
