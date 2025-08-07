package com.vanix.easygl.core.input;

import com.vanix.easygl.commons.Position;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.value.FloatValue;
import com.vanix.easygl.core.input.event.MouseButtonListener;
import com.vanix.easygl.core.input.event.MouseMoveListener;
import com.vanix.easygl.core.input.event.MouseScrollListener;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Mouse extends Position, InputDevice<Mouse.Button> {

    void cursorMode(CursorMode mode);

    CursorMode cursorMode();

    ListenerOperation<MouseButtonListener> onButton(Button... buttons);

    ListenerOperation<MouseScrollListener> onScroll();

    ListenerOperation<MouseMoveListener> onMove();

    float yaw();

    float pitch();

    FloatValue sensitivity();

    @Override
    default Type type() {
        return Type.Mouse;
    }

    enum Button implements Input {
        BTN1(MetaSystem.Window.queryInt("MOUSE_BUTTON_1")),
        BTN2(MetaSystem.Window.queryInt("MOUSE_BUTTON_2")),
        BTN3(MetaSystem.Window.queryInt("MOUSE_BUTTON_3")),
        BTN4(MetaSystem.Window.queryInt("MOUSE_BUTTON_4")),
        BTN5(MetaSystem.Window.queryInt("MOUSE_BUTTON_5")),
        BTN6(MetaSystem.Window.queryInt("MOUSE_BUTTON_6")),
        BTN7(MetaSystem.Window.queryInt("MOUSE_BUTTON_7")),
        BTN8(MetaSystem.Window.queryInt("MOUSE_BUTTON_8")),
        LAST(MetaSystem.Window.queryInt("MOUSE_BUTTON_LAST")),
        LEFT(MetaSystem.Window.queryInt("MOUSE_BUTTON_LEFT")),
        RIGHT(MetaSystem.Window.queryInt("MOUSE_BUTTON_RIGHT")),
        MIDDLE(MetaSystem.Window.queryInt("MOUSE_BUTTON_MIDDLE"));

        private final int code;

        Button(int code) {
            this.code = code;
        }

        @Override
        public int code() {
            return code;
        }

    }

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

        public int value() {
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

}
