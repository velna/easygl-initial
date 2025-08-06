package com.vanix.easygl.core.input;

public interface InputController extends Iterable<InputDevice<?>> {
    Mouse mouse();

    Keyboard keyboard();
}
