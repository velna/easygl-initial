package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.FrameBuffer;
import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;

public class C12HelloWindowClear {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window)) {
            window.bind().inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE)
                    .subscribe((event) -> event.source().window().shouldClose(true));

            while (!window.shouldClose()) {
                graphics.setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameBuffer.Color);
                window.swapBuffers().pollEvents();
            }
        }
    }

}
