package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.window.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import com.vanix.easygl.glfw.GlWindow;
import com.vanix.easygl.opengl.GlGraphics;

public class C32ShaderInterpolation {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = new GlGraphics();
             var vertex = Shader.vertex("v1");
             var fragment = Shader.fragment("f1");
             var program = Program.of("p1");
             var vao = VertexArray.of();
             var vbo = Buffer.ofArray(vao, DataType.Float)) {
            window.bind().inputCtlr().keyboard().onKey(Keyboard.KEY_ESCAPE).subscribe(C32ShaderInterpolation::processInput);
            graphics.viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());

            program.attach(vertex.source("""
                                    #version 330 core
                                    layout (location = 0) in vec3 aPos;
                                    layout (location = 1) in vec3 aColor;
                                    out vec3 ourColor;
                                    void main() {
                                        gl_Position = vec4(aPos, 1.0);
                                        ourColor = aColor;
                                    }
                                    """)
                            .compile())
                    .attach(fragment.source("""
                                    #version 330 core
                                    out vec4 FragColor;
                                    in vec3 ourColor;
                                    void main(){
                                        FragColor = vec4(ourColor, 1.0f);;
                                    }
                                    """)
                            .compile())
                    .link();

            vao.bind().attributes(vbo.bind()
                    .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                            // positions         // colors
                            0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f,  // bottom right
                            -0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f,  // bottom left
                            0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f   // top
                    }), 3, 3);

            while (!window.shouldClose()) {
                graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(Graphics.BufferMask.Color);

                program.bind();
                vao.drawArray(DrawMode.Triangles, vbo);

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