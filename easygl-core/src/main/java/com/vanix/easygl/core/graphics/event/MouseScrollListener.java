package com.vanix.easygl.core.graphics.event;

import com.vanix.easygl.core.graphics.Mouse;
import com.vanix.easygl.commons.event.EventListener;

public interface MouseScrollListener extends EventListener {
	void onMouseScroll(Mouse mouse, double xoffset, double yoffset);
}
