package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.graphics.gl.GlGraphics;
import com.vanix.easygl.core.graphics.gl.GlWindow;

public class C21HelloTriangle {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        var window = Window.of(800, 600, "LearnOpenGL").bind();
        window.inputCtlr().keyboard().onKey(Keyboard.KEY_ESCAPE).subscribe(C21HelloTriangle::processInput);
        var graphics = new GlGraphics();
        graphics.viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());

        var vertex = Shader.vertex("v1")
                .source("""
                        #version 330 core
                        layout (location = 0) in vec3 aPos;
                        void main() {
                            gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);
                        }
                        """)
                .compile();
        var fragment = Shader.fragment("f1")
                .source("""
                        #version 330 core
                        out vec4 FragColor;
                        void main(){
                            FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);
                        }
                        """)
                .compile();
        var program = Program.of("p1")
                .attach(vertex)
                .attach(fragment)
                .link();
        vertex.close();
        fragment.close();

        var vao = VertexArray.of();
        var vbo = Buffer.ofArray(vao, DataType.Float)
                .bind()
                .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                        0.5f, 0.5f, 0.0f,  // top right
                        0.5f, -0.5f, 0.0f,  // bottom right
                        -0.5f, -0.5f, 0.0f,  // bottom left
                        -0.5f, 0.5f, 0.0f   // top left
                });
        var ebo = Buffer.ofElementArray(vao, DataType.UnsignedInt)
                .bind()
                .realloc(Buffer.DataUsage.STATIC_DRAW, new int[]{
                        // note that we start from 0!
                        0, 1, 3,  // first Triangle
                        1, 2, 3   // second Triangle
                });

        vao.bind().attributes(vbo, 3);

        while (!window.shouldClose()) {
            graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f)
                    .clear(Graphics.BufferMask.Color);

            program.bind();
            vao.drawElements(DrawMode.Triangles, vbo, ebo);

            window.swapBuffers()
                    .pollEvents();
        }
        ebo.close();
        vbo.close();
        vao.close();
        program.close();

        window.close();
        GlWindow.systemTerminate();

    }

    private static void processInput(Keyboard keyboard, int key, int scancode, int action, int modifiers) {
        keyboard.window().shouldClose(true);
    }
}
