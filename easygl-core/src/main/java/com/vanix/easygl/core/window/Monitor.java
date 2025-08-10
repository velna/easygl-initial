package com.vanix.easygl.core.window;

import com.vanix.easygl.commons.Dimension;
import com.vanix.easygl.commons.Position;
import com.vanix.easygl.commons.Rectangle;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.window.event.MonitorListener;
import org.joml.Vector2f;

import java.util.List;

public interface Monitor extends Position, Dimension, Handle {

    String name();

    Vector2f contentScale();

    Rectangle workArea();

    static Monitor primary() {
        return MetaSystem.Window.of(Monitor.class);
    }

    static List<Monitor> list() {
        return MetaSystem.Window.of(Monitor[].class);
    }

    static ListenerOperation<MonitorListener> onConnection() {
        return MetaSystem.Window.of(MonitorListener.class);
    }
}
