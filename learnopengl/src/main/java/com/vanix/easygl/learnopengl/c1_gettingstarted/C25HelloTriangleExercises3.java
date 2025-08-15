package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;

public class C25HelloTriangleExercises3 {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();
        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var vertex = Shader.vertex();
             var fragmentOrange = Shader.fragment();
             var fragmentYellow = Shader.fragment();
             var programOrange = Program.of();
             var programYellow = Program.of();
             var vaos = VertexArray.of(2);
             var vbos = Buffer.of(2, Buffer.Type.Array, DataType.Float)) {
            window.bind().inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE)
                    .subscribe((event) -> event.source().window().shouldClose(true));

            vertex.source("""
                            #version 330 core
                            layout (location = 0) in vec3 aPos;
                            void main() {
                                gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);
                            }
                            """)
                    .compile();

            programOrange.attach(vertex)
                    .attach(fragmentOrange.source("""
                                    #version 330 core
                                    out vec4 FragColor;
                                    void main(){
                                        FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);
                                    }
                                    """)
                            .compile())
                    .link();
            programYellow.attach(vertex)
                    .attach(fragmentYellow.source("""
                                    #version 330 core
                                    out vec4 FragColor;
                                    void main(){
                                        FragColor = vec4(1.0f, 1.0f, 0.0f, 1.0f);
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
                graphics.setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameBuffers.Color);

                programOrange.bind();
                vaos.getFirst().drawArray(DrawMode.Triangles, vbos.getFirst());
                programYellow.bind();
                vaos.getLast().drawArray(DrawMode.Triangles, vbos.getLast());

                window.swapBuffers().pollEvents();
            }
        }
    }

}
