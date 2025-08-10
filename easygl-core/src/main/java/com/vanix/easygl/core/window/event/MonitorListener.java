package com.vanix.easygl.core.window.event;

import com.vanix.easygl.commons.event.EventListener;

public interface MonitorListener extends EventListener {
    void monitorOnConnection(MonitorEvent event);
}
