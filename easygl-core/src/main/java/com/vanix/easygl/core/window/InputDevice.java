package com.vanix.easygl.core.window;

import com.vanix.easygl.core.meta.MetaSystem;

public interface InputDevice<I extends InputDevice.Input> {

    Action actionOf(I input);

    default boolean isPressed(I input) {
        return actionOf(input) == Action.Press;
    }

    Window window();

    Type type();

    interface Input {
        int code();
    }

    enum Type {
        Mouse,
        Keyboard
    }

    enum Action {
        Release(MetaSystem.Window.queryInt("RELEASE")),
        Press(MetaSystem.Window.queryInt("PRESS")),
        Repeat(MetaSystem.Window.queryInt("REPEAT"));
        private final int value;

        Action(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

}
