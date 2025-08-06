package com.vanix.easygl.core.window.event;

import com.vanix.easygl.core.window.Keyboard;

public class KeyboardEvent extends Event<Keyboard> {
    private final Keyboard.Key key;
    private final Keyboard.Action action;
    private final int scancode;
    private final int modifiers;

    public KeyboardEvent(Keyboard keyboard, Keyboard.Key key, int scancode, Keyboard.Action action, int modifiers) {
        super(keyboard);
        this.key = key;
        this.scancode = scancode;
        this.action = action;
        this.modifiers = modifiers;
    }

    public Keyboard.Key key() {
        return key;
    }

    public Keyboard.Action action() {
        return action;
    }

    public int scancode() {
        return scancode;
    }

    public int modifiers() {
        return modifiers;
    }

    public boolean hasModifier(Keyboard.Modifier modifier) {
        return (modifiers & modifier.value()) != 0;
    }

    public boolean isActionPress() {
        return action == Keyboard.Action.Press;
    }

    public boolean isActionRelease() {
        return action == Keyboard.Action.Release;
    }

    public boolean isActionRepeat() {
        return action == Keyboard.Action.Repeat;
    }
}
