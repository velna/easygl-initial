package com.vanix.easygl.learnopengl.gettingstarted;

import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.graphics.Keyboard;
import com.vanix.easygl.core.graphics.Window;
import com.vanix.easygl.core.graphics.WindowHints;
import com.vanix.easygl.core.graphics.gl.GlGraphics;
import com.vanix.easygl.core.graphics.gl.GlWindow;

public class HelloWindow {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        var window = Window.of(800, 600, "LearnOpenGL").bind();
        window.inputCtlr().keyboard().onKey(Keyboard.KEY_ESCAPE).subscribe(HelloWindow::processInput);
        var graphics = new GlGraphics();
        graphics.viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());

        while (!window.shouldClose()) {
            graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f);
            graphics.clear(Graphics.BufferMask.Color);
            window.swapBuffers();
            window.pollEvents();
        }
        window.dispose();
        GlWindow.systemTerminate();
    }

    private static void processInput(Keyboard keyboard, int key, int scancode, int action, int modifiers) {
        keyboard.window().shouldClose(true);
    }
}
