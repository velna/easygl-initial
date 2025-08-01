package com.vanix.easygl.glfw;

import com.vanix.easygl.core.window.InputController;
import com.vanix.easygl.core.window.Keyboard;
import com.vanix.easygl.core.window.Mouse;
import com.vanix.easygl.core.window.Window;

public class GlInputController implements InputController {

	private final Mouse mouse;
	private final Keyboard keyboard;

	public GlInputController(Window window) {
		mouse = new GlMouse(window);
		keyboard = new GlKeyboard(window);
	}

	@Override
	public Mouse mouse() {
		return mouse;
	}

	@Override
	public Keyboard keyboard() {
		return keyboard;
	}

}
