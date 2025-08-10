package com.vanix.easygl.core.window.event;

import com.vanix.easygl.commons.event.EventListener;

public interface WindowIconifyListener extends EventListener {
    void windowOnIconify(WindowIconifyEvent event);
}
