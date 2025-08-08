package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.Position;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.input.event.*;
import com.vanix.easygl.core.window.Window;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.nio.DoubleBuffer;

public class GlMouse implements Mouse {
    private final ListenerSupport<MouseScrollListener> scrollListeners;
    private final ListenerSupport<MouseMoveListener> moveListeners;
    private final ListenerSupport<MouseButtonListener> buttonListeners;
    private final Window window;
    private final DoubleBuffer xPosBuf = BufferUtils.createDoubleBuffer(1);
    private final long xPosAddress = MemoryUtil.memAddressSafe(xPosBuf);
    private final DoubleBuffer yPosBuf = BufferUtils.createDoubleBuffer(1);
    private final long yPosAddress = MemoryUtil.memAddressSafe(yPosBuf);
    private final boolean rawMotionSupported;
//    private final FloatValue sensitivity = Value.of(0.05f);
//    private double x;
//    private double y;
//    private final FloatValue yaw = Value.of(-90.0f);
//    private final FloatValue pitch = Value.limited(0.0f, -89.0f, 89.0f);

    public GlMouse(GlWindow window) {
        this.window = window;
//        yaw.addInterceptor((oldV, newV) -> newV % 360.0f);
        moveListeners = window.newListenerSupport(1, GLFW::glfwSetCursorPosCallback, this::moveCallback);
        scrollListeners = window.newListenerSupport(1, GLFW::glfwSetScrollCallback, this::scrollCallback);
        buttonListeners = window.newListenerSupport(
                GLFW.GLFW_MOUSE_BUTTON_LAST + 1,
                GLFW::glfwSetMouseButtonCallback,
                this::buttonCallback);
        rawMotionSupported = GLFW.glfwRawMouseMotionSupported();
    }

    private void buttonCallback(long window, int button, int action, int mods) {
        MouseButtonEvent event = new MouseButtonEvent(
                this, Cache.buttonOfCode(button), Cache.actionOfCode(action), mods);
        buttonListeners.forEach(button, l -> l.mouseOnButton(event));
    }

    private void moveCallback(long window, double xpos, double ypos) {
//        double lastX = x;
//        double lastY = y;
//        this.x = xpos;
//        this.y = ypos;
//        double xoffset = x - lastX;
//        double yoffset = y - lastY;
//
//        float sens = sensitivity.get();
//        xoffset *= sens;
//        yoffset *= sens;
//
//        yaw.incr((float) xoffset);
//        pitch.incr(-(float) yoffset);

        MouseMoveEvent event = new MouseMoveEvent(this, xpos, ypos);
        moveListeners.forEach(0, l -> l.mouseOnMove(event));
    }

    private void scrollCallback(long window, double xOffset, double yOffset) {
        MouseScrollEvent event = new MouseScrollEvent(this, xOffset, yOffset);
        scrollListeners.forEach(0, l -> l.mouseOnScroll(event));
    }

    @Override
    public Action actionOf(Button input) {
        return Cache.actionOfCode(GLFW.glfwGetMouseButton(window.handle(), input.code()));
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
        GLFW.nglfwGetCursorPos(window.handle(), xPosAddress, MemoryUtil.NULL);
        return xPosBuf.get();
    }

    @Override
    public double getY() {
        GLFW.nglfwGetCursorPos(window.handle(), MemoryUtil.NULL, yPosAddress);
        return yPosBuf.get();
    }

    @Override
    public Position getPosition() {
        GLFW.nglfwGetCursorPos(window.handle(), xPosAddress, yPosAddress);
        return Position.of(xPosBuf.get(0), yPosBuf.get(0));
    }

    @Override
    public void setPosition(Position position) {
        GLFW.glfwSetCursorPos(window.handle(), position.getX(), position.getY());
    }

    @Override
    public void setX(double x) {
        GLFW.glfwSetCursorPos(window.handle(), x, yPosBuf.get(0));
    }

    @Override
    public void setY(double y) {
        GLFW.glfwSetCursorPos(window.handle(), xPosBuf.get(0), y);
    }

    @Override
    public boolean isRawMotionSupported() {
        return rawMotionSupported;
    }

    @Override
    public boolean rawMotion() {
        return rawMotionSupported && GLFW.glfwGetInputMode(window.handle(), GLFW.GLFW_RAW_MOUSE_MOTION) == GLFW.GLFW_TRUE;
    }

    @Override
    public void rawMotion(boolean enable) {
        if (rawMotionSupported) {
            GLFW.glfwSetInputMode(window.handle(), GLFW.GLFW_RAW_MOUSE_MOTION, enable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        }
    }
}
