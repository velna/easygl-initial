package com.vanix.easygl.core.window;

public interface InputController extends Iterable<InputDevice<?>> {
    Mouse mouse();

    Keyboard keyboard();
}
