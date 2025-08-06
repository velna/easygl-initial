package com.vanix.easygl.core;

import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.input.InputController;
import com.vanix.easygl.core.window.Window;

public interface RenderContext {

    Graphics graphics();

    Window window();

    default InputController inputCtlr() {
        return window().inputCtlr();
    }

    double tickDelta();
}
