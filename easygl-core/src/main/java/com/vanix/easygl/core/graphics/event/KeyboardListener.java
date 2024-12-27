package com.vanix.easygl.core.graphics.event;

import com.vanix.easygl.core.graphics.Keyboard;
import com.vanix.easygl.commons.event.EventListener;

public interface KeyboardListener extends EventListener {
	void keyboardOnKey(Keyboard keyboard, int key, int scancode, int action, int modifiers);
}
