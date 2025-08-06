package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.InputDevice;
import com.vanix.easygl.core.window.Mouse;

public class MouseScrollEvent extends InputEvent<Mouse, Mouse.Button> {
    private final double xOffset;
    private final double yOffset;

    public MouseScrollEvent(Mouse device, double xOffset, double yOffset) {
        super(device, null, null);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public double xOffset() {
        return xOffset;
    }

    public double yOffset() {
        return yOffset;
    }
}
