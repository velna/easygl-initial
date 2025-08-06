package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.InputDevice;
import com.vanix.easygl.core.window.Mouse;

public class MouseEvent extends InputEvent<Mouse, Mouse.Button> {
    public MouseEvent(Mouse device, Mouse.Button button, InputDevice.Action action) {
        super(device, button, action);
    }

}
