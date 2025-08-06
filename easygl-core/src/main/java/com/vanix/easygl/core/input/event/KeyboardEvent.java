package com.vanix.easygl.core.input.event;

import com.vanix.easygl.core.input.InputDevice;
import com.vanix.easygl.core.input.Keyboard;

public class KeyboardEvent extends InputEvent<Keyboard, Keyboard.Key> {
    private final int scancode;
    private final int modifiers;

    public KeyboardEvent(Keyboard device, Keyboard.Key key, InputDevice.Action action, int scancode, int modifiers) {
        super(device, key, action);
        this.scancode = scancode;
        this.modifiers = modifiers;
    }

    public Keyboard.Key key() {
        return input;
    }

    public int scancode() {
        return scancode;
    }

    public int modifiers() {
        return modifiers;
    }

    public boolean hasModifier(Keyboard.Modifier modifier) {
        return (modifiers & modifier.value()) != 0;
    }

}
