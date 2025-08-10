package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.Window;
import lombok.Getter;

@Getter
public class WindowFocusEvent extends WindowEvent {
    private final boolean focus;

    public WindowFocusEvent(Window source, boolean focus) {
        super(source);
        this.focus = focus;
    }
}
