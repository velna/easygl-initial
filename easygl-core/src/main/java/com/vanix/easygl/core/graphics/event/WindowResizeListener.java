package com.vanix.easygl.core.graphics.event;

import com.vanix.easygl.core.graphics.Window;
import com.vanix.easygl.commons.event.EventListener;

public interface WindowResizeListener extends EventListener {

	void onWindowResized(Window window);

}
