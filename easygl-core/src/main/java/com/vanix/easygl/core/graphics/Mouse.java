package com.vanix.easygl.core.graphics;

import org.lwjgl.glfw.GLFW;

import com.vanix.easygl.core.graphics.event.MouseMoveListener;
import com.vanix.easygl.core.graphics.event.MouseScrollListener;
import com.vanix.easygl.commons.Position;
import com.vanix.easygl.commons.event.ListenerOperation;

public interface Mouse extends Position {

	enum CursorMode {
		UNKNOWN(-1), //
		CURSOR_NORMAL(GLFW.GLFW_CURSOR_NORMAL), //
		CURSOR_HIDDEN(GLFW.GLFW_CURSOR_HIDDEN), //
		CURSOR_DISABLED(GLFW.GLFW_CURSOR_DISABLED), //
		CURSOR_CAPTURED(GLFW.GLFW_CURSOR_CAPTURED);

		private final int value;

		private CursorMode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static CursorMode valueOf(int v) {
			for (CursorMode mode : CursorMode.values()) {
				if (mode.value == v) {
					return mode;
				}
			}
			return UNKNOWN;
		}
	}

	Window window();

	void setCursorMode(CursorMode mode);

	CursorMode getCursorMode();

	ListenerOperation<MouseScrollListener> onScroll();

	ListenerOperation<MouseMoveListener> onMove();

	float yaw();

	float pitch();

	float sensitivity();

}
