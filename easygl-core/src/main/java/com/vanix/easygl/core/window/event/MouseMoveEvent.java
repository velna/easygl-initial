package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.InputDevice;
import com.vanix.easygl.core.window.Mouse;

public class MouseMoveEvent extends MouseEvent {
    private final double x;
    private final double y;

    public MouseMoveEvent(Mouse device, double x, double y) {
        super(device, null, null);
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }
}
