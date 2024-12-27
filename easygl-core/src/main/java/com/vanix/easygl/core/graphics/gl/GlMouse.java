package com.vanix.easygl.core.graphics.gl;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.glfwGetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

import com.vanix.easygl.core.graphics.Mouse;
import com.vanix.easygl.core.graphics.Window;
import com.vanix.easygl.core.graphics.event.MouseMoveListener;
import com.vanix.easygl.core.graphics.event.MouseScrollListener;
import com.vanix.easygl.commons.event.ListenerKey;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.commons.value.FloatValue;
import com.vanix.easygl.commons.value.Value;

public class GlMouse implements Mouse {
	private final static ListenerKey<MouseScrollListener> MouseScrollKey = ListenerKey.of(0);
	private final static ListenerKey<MouseMoveListener> MouseMoveKey = ListenerKey.of(1);

	private final Window window;
	private final ListenerSupport listenerSupport = new ListenerSupport();
	private float sensitivity = 0.05f;
	private double x;
	private double y;
	private double lastX;
	private double lastY;
	private FloatValue yaw = Value.of(-90.0f);
	private FloatValue pitch = Value.limited(0.0f, -89.0f, 89.0f);

	public GlMouse(Window window) {
		this.window = window;
		glfwSetCursorPosCallback(window.nativeHandle(), this::moveCallback);
		glfwSetScrollCallback(window.nativeHandle(), this::scollCallback);
		yaw.addInterceptor((oldV, newV) -> newV % 360.0f);
	}

	private void moveCallback(long window, double xpos, double ypos) {
		lastX = x;
		lastY = y;
		this.x = xpos;
		this.y = ypos;
		double xoffset = x - lastX;
		double yoffset = y - lastY;

		xoffset *= sensitivity;
		yoffset *= sensitivity;

		yaw.incr((float) xoffset);
		pitch.incr(-(float) yoffset);

		listenerSupport.forEach(MouseMoveKey, l -> l.onMouseMove(this));
	}

	private void scollCallback(long window, double xoffset, double yoffset) {
		listenerSupport.forEach(MouseScrollKey, l -> l.onMouseScroll(this, xoffset, yoffset));
	}

	@Override
	public CursorMode getCursorMode() {
		int mode = glfwGetInputMode(window.nativeHandle(), GLFW_CURSOR);
		return CursorMode.valueof(mode);
	}

	@Override
	public void setCursorMode(CursorMode mode) {
		glfwSetInputMode(window.nativeHandle(), GLFW_CURSOR, mode.getValue());
	}

	@Override
	public ListenerOperation<MouseMoveListener> onMove() {
		return listenerSupport.of(MouseMoveKey);
	}

	@Override
	public ListenerOperation<MouseScrollListener> onScroll() {
		return listenerSupport.of(MouseScrollKey);
	}

	@Override
	public Window window() {
		return window;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public float yaw() {
		return yaw.get();
	}

	@Override
	public float pitch() {
		return pitch.get();
	}

	@Override
	public float sensitivity() {
		return sensitivity;
	}
}
