package com.vanix.easygl.core;

import com.vanix.easygl.commons.util.Counters;
import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.graphics.Window;
import com.vanix.easygl.commons.Ticket;

public interface ClientApp {

	Graphics graphics();

	Window window();

	Ticket ticket();

	Counters counters();
}
