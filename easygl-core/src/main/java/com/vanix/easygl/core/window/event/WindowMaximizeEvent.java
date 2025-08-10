package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.Window;
import lombok.Getter;

@Getter
public class WindowMaximizeEvent extends WindowEvent {
    private final boolean maximize;

    public WindowMaximizeEvent(Window source, boolean maximize) {
        super(source);
        this.maximize = maximize;
    }
}
