package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.InputController;
import com.vanix.easygl.core.graphics.Keyboard;
import com.vanix.easygl.core.graphics.Mouse;
import com.vanix.easygl.core.graphics.Window;

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
