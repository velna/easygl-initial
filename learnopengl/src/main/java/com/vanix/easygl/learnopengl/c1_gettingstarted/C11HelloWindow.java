package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.window.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.glfw.GlWindow;

public class C11HelloWindow {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL").bind()) {
            window.inputCtlr().keyboard().onKey(Keyboard.KEY_ESCAPE).subscribe(C11HelloWindow::processInput);
            new GlGraphics()
                    .viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());

            while (!window.shouldClose()) {
                window.swapBuffers().pollEvents();
            }
        } finally {
            GlWindow.systemTerminate();
        }
    }

    private static void processInput(Keyboard keyboard, int key, int scancode, int action, int modifiers) {
        keyboard.getWindow().shouldClose(true);
    }
}
