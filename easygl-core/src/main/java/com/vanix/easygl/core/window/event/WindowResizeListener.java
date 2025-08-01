package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.commons.event.EventListener;

public interface WindowResizeListener extends EventListener {

	void onWindowResized(Window window);

}
