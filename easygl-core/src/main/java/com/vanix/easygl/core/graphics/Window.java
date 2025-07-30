package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.core.graphics.event.WindowRefreshListener;
import com.vanix.easygl.core.graphics.event.WindowResizeListener;
import com.vanix.easygl.core.graphics.gl.GlWindow;
import org.lwjgl.glfw.GLFW;

public interface Window extends Bindable<BindTarget.Default<Window>, Window> {
    BindTarget.Default<Window> Target = new BindTarget.Default<>(
            BindingState.<BindTarget.Default<Window>, Window>ofLong("Window", GLFW::glfwMakeContextCurrent)
    );

    @Override
    default int handle() {
        throw new UnsupportedOperationException();
    }

    int width();

    int height();

    String title();

    boolean shouldClose();

    void shouldClose(boolean close);

    InputController inputCtlr();

    ListenerOperation<WindowResizeListener> onResize();

    ListenerOperation<WindowRefreshListener> onRefresh();

    Window swapBuffers();

    Window pollEvents();

    static Window of(int width, int height, String title) {
        return new GlWindow(width, height, title);
    }

    int frameBufferWidth();

    int frameBufferHeight();
}
