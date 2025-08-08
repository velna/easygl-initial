package com.vanix.easygl.core.input.event;

import com.vanix.easygl.core.input.InputDevice;
import com.vanix.easygl.core.input.Mouse;

public class MouseButtonEvent extends MouseEvent implements ActionInputEvent, ModifierInputEvent {
    private final int modifiers;

    public MouseButtonEvent(Mouse device, Mouse.Button button, InputDevice.Action action, int modifiers) {
        super(device, button, action);
        this.modifiers = modifiers;
    }

    @Override
    public InputDevice.Action action() {
        return action;
    }

    @Override
    public int modifiers() {
        return modifiers;
    }

    public Mouse.Button button() {
        return input;
    }
}
