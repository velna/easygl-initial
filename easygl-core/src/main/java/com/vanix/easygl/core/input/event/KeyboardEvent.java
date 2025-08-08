package com.vanix.easygl.core.input.event;

import com.vanix.easygl.core.input.InputDevice;
import com.vanix.easygl.core.input.Keyboard;

public class KeyboardEvent extends InputEvent<Keyboard, Keyboard.Key> implements ActionInputEvent, ModifierInputEvent {
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

    @Override
    public InputDevice.Action action() {
        return action;
    }

    public int scancode() {
        return scancode;
    }

    public int modifiers() {
        return modifiers;
    }
}
