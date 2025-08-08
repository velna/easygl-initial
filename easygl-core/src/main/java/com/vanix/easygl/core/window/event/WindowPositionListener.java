package com.vanix.easygl.core.window.event;

import com.vanix.easygl.commons.event.EventListener;
import com.vanix.easygl.core.window.Window;

public interface WindowPositionListener extends EventListener {
    void windowOnPosition(Window window);
}
