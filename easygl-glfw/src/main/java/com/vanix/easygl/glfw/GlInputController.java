package com.vanix.easygl.glfw;

import com.vanix.easygl.core.input.InputController;
import com.vanix.easygl.core.input.InputDevice;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

public class GlInputController implements InputController {
    private final Mouse mouse;
    private final Keyboard keyboard;
    private final List<InputDevice<?>> devices;

    public GlInputController(GlWindow window) {
        mouse = new GlMouse(window);
        keyboard = new GlKeyboard(window);
        devices = List.of(mouse, keyboard);
    }

    @Nonnull
    @Override
    public Iterator<InputDevice<?>> iterator() {
        return devices.iterator();
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
