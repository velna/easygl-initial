package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.Mouse;
import com.vanix.easygl.commons.event.EventListener;

public interface MouseScrollListener extends EventListener {
	void mouseOnScroll(MouseScrollEvent event);
}
