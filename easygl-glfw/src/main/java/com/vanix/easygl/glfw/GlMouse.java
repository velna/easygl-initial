package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.value.FloatValue;
import com.vanix.easygl.commons.value.Value;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.input.event.*;
import com.vanix.easygl.core.window.Window;
import org.lwjgl.glfw.GLFW;


public class GlMouse implements Mouse {
    private final ListenerSupport<MouseScrollListener> scrollListeners;
    private final ListenerSupport<MouseMoveListener> moveListeners;
    private final ListenerSupport<MouseButtonListener> buttonListeners;

    private final Window window;
    private final FloatValue sensitivity = Value.of(0.05f);
    private double x;
    private double y;
    private final FloatValue yaw = Value.of(-90.0f);
    private final FloatValue pitch = Value.limited(0.0f, -89.0f, 89.0f);

    public GlMouse(GlWindow window) {
        this.window = window;
        yaw.addInterceptor((oldV, newV) -> newV % 360.0f);
        moveListeners = window.newListenerSupport(1, GLFW::glfwSetCursorPosCallback, this::moveCallback);
        scrollListeners = window.newListenerSupport(1, GLFW::glfwSetScrollCallback, this::scrollCallback);
        buttonListeners = window.newListenerSupport(
                GLFW.GLFW_MOUSE_BUTTON_LAST + 1,
                GLFW::glfwSetMouseButtonCallback,
                this::buttonCallback);
    }

    private void buttonCallback(long window, int button, int action, int mods) {

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

        MouseMoveEvent event = new MouseMoveEvent(this, xpos, ypos);
        moveListeners.forEach(0, l -> l.mouseOnMove(event));
    }

    private void scrollCallback(long window, double xOffset, double yOffset) {
        MouseScrollEvent event = new MouseScrollEvent(this, xOffset, yOffset);
        scrollListeners.forEach(0, l -> l.mouseOnScroll(event));
    }

    @Override
    public Action actionOf(Button input) {
        return Cache.actionOfValue(GLFW.glfwGetMouseButton(window.handle(), input.code()));
    }

    @Override
    public CursorMode cursorMode() {
        int mode = GLFW.glfwGetInputMode(window.handle(), GLFW.GLFW_CURSOR);
        return CursorMode.valueOf(mode);
    }

    @Override
    public void cursorMode(CursorMode mode) {
        GLFW.glfwSetInputMode(window.handle(), GLFW.GLFW_CURSOR, mode.value());
    }

    @Override
    public ListenerOperation<MouseButtonListener> onButton(Button... buttons) {
        return buttonListeners.listen(Button::code, buttons);
    }

    @Override
    public ListenerOperation<MouseMoveListener> onMove() {
        return moveListeners.listen();
    }

    @Override
    public ListenerOperation<MouseScrollListener> onScroll() {
        return scrollListeners.listen();
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
