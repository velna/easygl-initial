package com.vanix.easygl.core.input.event;

import com.vanix.easygl.core.input.Mouse;

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
