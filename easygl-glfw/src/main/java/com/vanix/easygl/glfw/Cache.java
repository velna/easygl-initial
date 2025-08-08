package com.vanix.easygl.glfw;

import com.vanix.easygl.core.input.InputDevice;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import org.lwjgl.glfw.GLFW;

class Cache {
    private static final Mouse.Button[] BUTTONS = new Mouse.Button[GLFW.GLFW_MOUSE_BUTTON_LAST + 1];
    private static final Keyboard.Key[] KEYS = new Keyboard.Key[GLFW.GLFW_KEY_LAST + 1];
    private static final InputDevice.Action[] ACTIONS = {InputDevice.Action.Release, InputDevice.Action.Press, InputDevice.Action.Repeat};

    static {
        for (var key : Keyboard.PrintableKey.values()) {
            KEYS[key.code()] = key;
        }
        for (var key : Keyboard.FunctionKey.values()) {
            KEYS[key.code()] = key;
        }
        for (var button : Mouse.Button.values()) {
            BUTTONS[button.code()] = button;
        }
    }

    static InputDevice.Action actionOfCode(int code) {
        return ACTIONS[code];
    }

    static Keyboard.Key keyOfCode(int code) {
        return KEYS[code];
    }

    static Mouse.Button buttonOfCode(int code) {
        return BUTTONS[code];
    }
}
