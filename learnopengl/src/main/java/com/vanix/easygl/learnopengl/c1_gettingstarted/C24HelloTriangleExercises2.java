package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.window.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.opengl.GlGraphics;
import com.vanix.easygl.glfw.GlWindow;

public class C24HelloTriangleExercises2 {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();
        try (var window = Window.of(800, 600, "LearnOpenGL");
             var vertex = Shader.vertex("v1");
             var fragment = Shader.fragment("f1");
             var program = Program.of("p1");
             var vaos = VertexArray.of(2);
             var vbos = Buffer.of(2, Buffer.Type.Array, DataType.Float)) {
            window.bind().inputCtlr().keyboard().onKey(Keyboard.KEY_ESCAPE).subscribe(C24HelloTriangleExercises2::processInput);
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

            vaos.getFirst().bind().attributes(vbos.getFirst().bind()
                    .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                            // first triangle
                            -0.9f, -0.45f, 0.0f,  // left
                            -0.0f, -0.45f, 0.0f,  // right
                            -0.45f, 0.45f, 0.0f
                    }), 3);

            vaos.getLast().bind().attributes(vbos.getLast().bind()
                    .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                            // second triangle
                            0.0f, -0.45f, 0.0f,  // left
                            0.9f, -0.45f, 0.0f,  // right
                            0.45f, 0.45f, 0.0f   // top
                    }), 3);

            while (!window.shouldClose()) {
                graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(Graphics.BufferMask.Color);

                program.bind();
                vaos.forEach((vao, i) -> vao.drawArray(DrawMode.Triangles, vbos.get(i)));
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
