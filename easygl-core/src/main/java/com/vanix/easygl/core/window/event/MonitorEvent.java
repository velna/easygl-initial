package com.vanix.easygl.core.window.event;

import com.vanix.easygl.commons.event.Event;
import com.vanix.easygl.core.window.Monitor;
import lombok.Getter;

@Getter
public class MonitorEvent extends Event<Monitor> {
    private final boolean connect;
    public MonitorEvent(Monitor source, boolean connect) {
        super(source);
        this.connect = connect;
    }
}
