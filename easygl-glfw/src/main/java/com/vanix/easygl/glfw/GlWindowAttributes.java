package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.attr.AbstractAttribute;
import com.vanix.easygl.commons.attr.BooleanAttribute;
import com.vanix.easygl.commons.attr.UpdatableBooleanAttribute;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowAttributes;
import org.lwjgl.glfw.GLFW;

public class GlWindowAttributes extends WindowAttributes {

    private final Window window;

    public GlWindowAttributes(Window window) {
        this.window = window;
    }

    @Override
    protected BooleanAttribute ofBoolean(int key) {
        return new WindowBooleanAttribute(key);
    }

    @Override
    protected UpdatableBooleanAttribute<Window> ofUpdatableBoolean(int key) {
        return new WindowBooleanAttribute(key);
    }

    private static class AbstractWindowAttribute extends AbstractAttribute {
        AbstractWindowAttribute(int key) {
            super(key);
        }
    }

    private class WindowBooleanAttribute extends AbstractWindowAttribute implements UpdatableBooleanAttribute<Window> {
        public WindowBooleanAttribute(int key) {
            super(key);
        }

        @Override
        public boolean getAsBoolean() {
            return GLFW.glfwGetWindowAttrib(window.handle(), key()) == GLFW.GLFW_TRUE;
        }

        @Override
        public Window set(boolean value) {
            GLFW.glfwSetWindowAttrib(window.handle(), key(), value ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
            return window;
        }
    }

}
