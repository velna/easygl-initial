package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.InputDevice;
import com.vanix.easygl.core.window.Mouse;

public class MouseButtonEvent extends MouseEvent {
    public MouseButtonEvent(Mouse device, Mouse.Button button, InputDevice.Action action) {
        super(device, button, action);
    }

    public Mouse.Button button() {
        return input;
    }
}
