package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.event.WindowRefreshListener;
import com.vanix.easygl.core.graphics.event.WindowResizeListener;
import com.vanix.easygl.core.graphics.gl.GlWindow;
import com.vanix.easygl.commons.event.ListenerOperation;

public interface Window extends Bindable<Window> {

    long nativeHandle();

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

    void dispose();

    static Window of(int width, int height, String title) {
        return new GlWindow(width, height, title);
    }

    int frameBufferWidth();

    int frameBufferHeight();
}
