package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.window.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.glfw.GlWindow;

public class C22HelloTriangleIndexed {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var vertex = Shader.vertex("v1");
             var fragment = Shader.fragment("f1");
             var program = Program.of("p1");
             var vao = VertexArray.of();
             var vbo = Buffer.ofArray(vao, DataType.Float);
             var ebo = Buffer.ofElementArray(vao, DataType.UnsignedInt)) {
            window.bind().inputCtlr().keyboard().onKey(Keyboard.KEY_ESCAPE).subscribe(C22HelloTriangleIndexed::processInput);
            var graphics = new GlGraphics()
                    .viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());

            program.attach(vertex.source("""
                                    #version 330 core
                                    layout (location = 0) in vec3 aPos;
                                    void main() {
                                        gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);
                                    }
                                    """)
                            .compile())
                    .attach(fragment.source("""
                                    #version 330 core
                                    out vec4 FragColor;
                                    void main(){
                                        FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);
                                    }
                                    """)
                            .compile())
                    .link();

            vao.bind().attributes(vbo.bind()
                    .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                            0.5f, 0.5f, 0.0f,  // top right
                            0.5f, -0.5f, 0.0f,  // bottom right
                            -0.5f, -0.5f, 0.0f,  // bottom left
                            -0.5f, 0.5f, 0.0f   // top left
                    }), 3);
            ebo.bind()
                    .realloc(Buffer.DataUsage.STATIC_DRAW, new int[]{
                            // note that we start from 0!
                            0, 1, 3,  // first Triangle
                            1, 2, 3   // second Triangle
                    });

            while (!window.shouldClose()) {
                graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(Graphics.BufferMask.Color);

                program.bind();
                vao.drawElements(DrawMode.Triangles, vbo, ebo);

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
