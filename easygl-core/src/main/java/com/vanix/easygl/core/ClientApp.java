package com.vanix.easygl.core;

import com.vanix.easygl.commons.Ticket;
import com.vanix.easygl.commons.util.Counters;
import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.window.Window;

public interface ClientApp {

    Graphics graphics();

    Window window();

    Ticket ticket();

    Counters counters();

    static ClientApp of(String id, Window window) {
        return new DefaultClientApp(id, window);
    }
}
