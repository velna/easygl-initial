package com.vanix.easygl.core.input.event;

import com.vanix.easygl.core.input.InputDevice;

public interface ModifierInputEvent {
    int modifiers();

    default boolean hasModifier(InputDevice.Modifier modifier) {
        return (modifiers() & modifier.value()) != 0;
    }
}
