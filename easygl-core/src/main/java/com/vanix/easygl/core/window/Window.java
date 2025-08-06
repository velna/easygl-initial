package com.vanix.easygl.core.window;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.core.input.InputController;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.window.event.WindowRefreshListener;
import com.vanix.easygl.core.window.event.WindowResizeListener;
import com.vanix.easygl.commons.util.TypeReference;

public interface Window extends Bindable<BindTarget.Default<Window>, Window> {
    BindableMeta<BindTarget.Default<Window>, Window> Meta = MetaSystem.Window.of(Window.class, new TypeReference<>() {
    });

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
        return Meta.create((Object) width, height, title);
    }

    int frameBufferWidth();

    int frameBufferHeight();
}
