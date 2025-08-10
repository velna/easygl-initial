package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.Window;
import lombok.Getter;

@Getter
public class WindowContentScaleEvent extends WindowEvent {
    private final float xScale;
    private final float yScale;

    public WindowContentScaleEvent(Window source, float xScale, float yScale) {
        super(source);
        this.xScale = xScale;
        this.yScale = yScale;
    }
}
