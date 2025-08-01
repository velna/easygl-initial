package com.vanix.easygl.core.window;

import com.vanix.easygl.commons.Position;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.window.event.MouseMoveListener;
import com.vanix.easygl.core.window.event.MouseScrollListener;

public interface Mouse extends Position {

    enum CursorMode {
        UNKNOWN(-1), //
        CURSOR_NORMAL(MetaSystem.Window.queryInt("CURSOR_NORMAL")), //
        CURSOR_HIDDEN(MetaSystem.Window.queryInt("CURSOR_HIDDEN")), //
        CURSOR_DISABLED(MetaSystem.Window.queryInt("CURSOR_DISABLED")), //
        CURSOR_CAPTURED(MetaSystem.Window.queryInt("CURSOR_CAPTURED"));

        private final int value;

        CursorMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static CursorMode valueOf(int v) {
            for (CursorMode mode : CursorMode.values()) {
                if (mode.value == v) {
                    return mode;
                }
            }
            return UNKNOWN;
        }
    }

    Window getWindow();

    void setCursorMode(CursorMode mode);

    CursorMode getCursorMode();

    ListenerOperation<MouseScrollListener> onScroll();

    ListenerOperation<MouseMoveListener> onMove();

    float yaw();

    float pitch();

    float sensitivity();

}
