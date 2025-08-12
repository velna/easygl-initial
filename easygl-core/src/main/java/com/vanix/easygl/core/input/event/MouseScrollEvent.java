package com.vanix.easygl.core.input.event;

import com.vanix.easygl.core.input.Mouse;

public class MouseScrollEvent extends MouseEvent {
    private final double xOffset;
    private final double yOffset;

    public MouseScrollEvent(Mouse device, double xOffset, double yOffset) {
        super(device);
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
