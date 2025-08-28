package com.vanix.easygl.core.window.event;

import com.vanix.easygl.commons.event.EventListener;

public interface WindowFpsListener extends EventListener {
    void windowOnFpsUpdate(WindowFpsEvent event);
}
