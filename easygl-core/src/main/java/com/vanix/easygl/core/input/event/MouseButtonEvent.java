package com.vanix.easygl.core.input.event;

import com.vanix.easygl.core.input.InputDevice;
import com.vanix.easygl.core.input.Mouse;

public class MouseButtonEvent extends MouseEvent {
    public MouseButtonEvent(Mouse device, Mouse.Button button, InputDevice.Action action) {
        super(device, button, action);
    }

    public Mouse.Button button() {
        return input;
    }
}
