package com.vanix.easygl.core.graphics;

public interface RenderContext {

    Graphics graphics();

    Window window();

    default InputController inputCtlr() {
        return window().inputCtlr();
    }

    double tickDelta();
}
