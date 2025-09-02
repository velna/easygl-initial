package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.Window;
import lombok.Getter;

@Getter
public class WindowFpsEvent extends WindowEvent {
    private final float fps;

    public WindowFpsEvent(Window source, float fps) {
        super(source);
        this.fps = fps;
    }
}
