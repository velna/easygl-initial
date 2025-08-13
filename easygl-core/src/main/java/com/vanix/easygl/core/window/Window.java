package com.vanix.easygl.core.window;

import com.vanix.easygl.commons.Dimensional;
import com.vanix.easygl.commons.Positional;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.input.InputController;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.window.event.WindowPositionListener;
import com.vanix.easygl.core.window.event.WindowRefreshListener;
import com.vanix.easygl.core.window.event.WindowResizeListener;
import org.joml.Vector2f;

public interface Window extends Bindable<BindTarget.Default<Window>, Window>, Dimensional, Positional {
    BindableMeta<BindTarget.Default<Window>, Window> Meta = MetaSystem.Window.of(Window.class);
    BindTarget.Default<Window> Target = new BindTarget.Default<>("Window", Meta);

    String getTitle();

    void setTitle(String title);

    boolean shouldClose();

    void shouldClose(boolean close);

    InputController inputs();

    ListenerOperation<WindowResizeListener> onResize();

    ListenerOperation<WindowRefreshListener> onRefresh();

    ListenerOperation<WindowPositionListener> onPosition();

    Window swapBuffers();

    Window pollEvents();

    static Window of(int width, int height, String title) {
        return Meta.create((Object) width, height, title);
    }

    int frameBufferWidth();

    int frameBufferHeight();

    @Override
    default void setX(double x) {
        setX((int) x);
    }

    @Override
    default void setY(double y) {
        setY((int) y);
    }

    void setX(int x);

    void setY(int x);

    int getXAsInt();

    int getYAsInt();

    @Override
    default double getX() {
        return getXAsInt();
    }

    @Override
    default double getY() {
        return getYAsInt();
    }

    default float getAspect() {
        return getWidth() * 1.0f / getHeight();
    }

    Window focus();

    Window hide();

    Window show();

    float opacity();

    Window opacity(float value);

    Window iconify();

    Window maximize();

    Window requestAttention();

    Window restore();

    Vector2f contentScale();

    Monitor monitor();

    Window monitor(Monitor monitor, int x, int y, int width, int height, int refreshRate);

    String clipboard();

    Window clipboard(String value);

    WindowAttributes attributes();

}
