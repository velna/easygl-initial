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
    private final FloatValue sensitivity = Value.of(0.05f);
    private double x;
    private double y;
    private final FloatValue yaw = Value.of(-90.0f);
    private final FloatValue pitch = Value.limited(0.0f, -89.0f, 89.0f);

    public GlMouse(Window window) {
        this.window = window;
        GLFW.glfwSetCursorPosCallback(window.handle(), this::moveCallback);
        GLFW.glfwSetScrollCallback(window.handle(), this::scrollCallback);
        yaw.addInterceptor((oldV, newV) -> newV % 360.0f);
    }

    private void moveCallback(long window, double xpos, double ypos) {
        double lastX = x;
        double lastY = y;
        this.x = xpos;
        this.y = ypos;
        double xoffset = x - lastX;
        double yoffset = y - lastY;

        float sens = sensitivity.get();
        xoffset *= sens;
        yoffset *= sens;

        yaw.incr((float) xoffset);
        pitch.incr(-(float) yoffset);

        listenerSupport.forEach(MouseMoveKey, l -> l.mouseOnMove(this));
    }

    private void scrollCallback(long window, double xOffset, double yOffset) {
        listenerSupport.forEach(MouseScrollKey, l -> l.mouseOnScroll(this, xOffset, yOffset));
    }

    @Override
    public CursorMode cursorMode() {
        int mode = GLFW.glfwGetInputMode(window.handle(), GLFW.GLFW_CURSOR);
        return CursorMode.valueOf(mode);
    }

    @Override
    public void cursorMode(CursorMode mode) {
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
    public FloatValue sensitivity() {
        return sensitivity;
    }
}
