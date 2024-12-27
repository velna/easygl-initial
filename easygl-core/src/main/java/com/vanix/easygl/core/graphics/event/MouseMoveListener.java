package com.vanix.easygl.core.graphics.event;

import com.vanix.easygl.core.graphics.Mouse;
import com.vanix.easygl.commons.event.EventListener;

public interface MouseMoveListener extends EventListener {
	void onMouseMove(Mouse mouse);

}
