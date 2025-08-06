package com.vanix.easygl.core.input.event;

import com.vanix.easygl.commons.event.Event;
import com.vanix.easygl.core.input.InputDevice;

public class InputEvent<T extends InputDevice<I>, I extends InputDevice.Input> extends Event<T> {
    protected final I input;
    private final InputDevice.Action action;

    public InputEvent(T device, I input, InputDevice.Action action) {
        super(device);
        this.input = input;
        this.action = action;
    }

    public InputDevice.Action action() {
        return action;
    }

    public boolean isActionPress() {
        return action == InputDevice.Action.Press;
    }

    public boolean isActionRelease() {
        return action == InputDevice.Action.Release;
    }

    public boolean isActionRepeat() {
        return action == InputDevice.Action.Repeat;
    }
}
