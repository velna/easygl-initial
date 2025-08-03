package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.window.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;

public class C11HelloWindow {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of()) {
            window.bind().inputCtlr().keyboard().onKey(Keyboard.KEY_ESCAPE)
                    .subscribe((keyboard, key, scancode, action, modifiers) -> keyboard.window().shouldClose(true));
            graphics.viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());

            while (!window.shouldClose()) {
                window.swapBuffers().pollEvents();
            }
        }
    }

}
