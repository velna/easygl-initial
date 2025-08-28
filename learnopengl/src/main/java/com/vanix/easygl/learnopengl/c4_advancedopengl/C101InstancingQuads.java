package com.vanix.easygl.learnopengl.c4_advancedopengl;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Vector2f;

import java.io.IOException;

public class C101InstancingQuads {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenglForwardCompat.enable();
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var instanceVbo = Buffer.of(DataType.Float);
             var quadVao = VertexArray.of();
             var quadVbo = Buffer.of(DataType.Float);
             var program = Program.of()) {

            window.inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);
            graphics.depthTest().enable();

            program.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/10.1.instancing.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/10.1.instancing.fs")
                    .link();

            // set up vertex data (and buffer(s)) and configure vertex attributes
            // ------------------------------------------------------------------
            quadVbo.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, new float[]{
                    // positions     // colors
                    -0.05f, 0.05f, 1.0f, 0.0f, 0.0f,
                    0.05f, -0.05f, 0.0f, 1.0f, 0.0f,
                    -0.05f, -0.05f, 0.0f, 0.0f, 1.0f,

                    -0.05f, 0.05f, 1.0f, 0.0f, 0.0f,
                    0.05f, -0.05f, 0.0f, 1.0f, 0.0f,
                    0.05f, 0.05f, 0.0f, 1.0f, 1.0f
            });
            quadVao.bind().enableAttributes(2f, 3f);

            var translations = new Vector2f[100];
            var index = 0;
            var offset = 0.1f;
            for (int y = -10; y < 10; y += 2) {
                for (int x = -10; x < 10; x += 2) {
                    translations[index++] = new Vector2f(x / 10.0f + offset, y / 10.0f + offset);
                }
            }
            instanceVbo.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, translations);
            quadVao.attribute(2)
                    .enable()
                    .setPointer(2, DataType.Float, false, 2 * Float.BYTES, 0)
                    .setDivisor(1);

            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                program.bind();
                quadVao.bind().drawArrayInstanced(DrawMode.Triangles, 0, 6, translations.length);

                window.swapBuffers().pollEvents();
            }
        }
    }


}
