package com.vanix.easygl.learnopengl.gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.graphics.gl.GlGraphics;
import com.vanix.easygl.core.graphics.gl.GlWindow;

public class HelloTriangle {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        var window = Window.of(800, 600, "LearnOpenGL").bind();
        window.inputCtlr().keyboard().onKey(Keyboard.KEY_ESCAPE).subscribe(HelloTriangle::processInput);
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
        float[] vertices = {
                0.5f, 0.5f, 0.0f,  // top right
                0.5f, -0.5f, 0.0f,  // bottom right
                -0.5f, -0.5f, 0.0f,  // bottom left
                -0.5f, 0.5f, 0.0f   // top left
        };
        int[] indices = {  // note that we start from 0!
                0, 1, 3,  // first Triangle
                1, 2, 3   // second Triangle
        };
        var vao = VertexArray.of().bind();
        var vbo = Buffer.of(Buffer.Type.Array, DataType.Float)
                .bind()
                .realloc(Buffer.DataUsage.STATIC_DRAW, vertices);
        var ebo = Buffer.of(Buffer.Type.ElementArray, DataType.UnsignedInt)
                .bind()
                .realloc(Buffer.DataUsage.STATIC_DRAW, indices);
        vao.attributes(vbo, 3);

        while (!window.shouldClose()) {
            graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f);
            graphics.clear(Graphics.BufferMask.Color);

            program.bind();
            vao.bind();
            graphics.drawTrianglesElements(6, 0);

            window.swapBuffers();
            window.pollEvents();
        }
        vao.close();
        vbo.close();
        ebo.close();
        program.close();
        window.dispose();
        GlWindow.systemTerminate();
    }

    private static void processInput(Keyboard keyboard, int key, int scancode, int action, int modifiers) {
        keyboard.window().shouldClose(true);
    }
}
