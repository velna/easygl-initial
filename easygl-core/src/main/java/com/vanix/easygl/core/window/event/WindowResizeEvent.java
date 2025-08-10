package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.Window;
import lombok.Getter;

@Getter
public class WindowResizeEvent extends WindowEvent {
    private final int width;
    private final int height;

    public WindowResizeEvent(Window source, int width, int height) {
        super(source);
        this.width = width;
        this.height = height;
    }
}
