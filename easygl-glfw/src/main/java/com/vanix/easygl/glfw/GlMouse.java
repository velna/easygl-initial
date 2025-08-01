package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.event.ListenerKey;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.commons.value.FloatValue;
import com.vanix.easygl.commons.value.Value;
import com.vanix.easygl.core.window.Mouse;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.event.MouseMoveListener;
import com.vanix.easygl.core.window.event.MouseScrollListener;
import org.lwjgl.glfw.GLFW;


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
        GLFW.glfwSetCursorPosCallback(window.handle(), this::moveCallback);
        GLFW.glfwSetScrollCallback(window.handle(), this::scollCallback);
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
        int mode = GLFW.glfwGetInputMode(window.handle(), GLFW.GLFW_CURSOR);
        return CursorMode.valueOf(mode);
    }

    @Override
    public void setCursorMode(CursorMode mode) {
        GLFW.glfwSetInputMode(window.handle(), GLFW.GLFW_CURSOR, mode.getValue());
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
    public Window getWindow() {
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
