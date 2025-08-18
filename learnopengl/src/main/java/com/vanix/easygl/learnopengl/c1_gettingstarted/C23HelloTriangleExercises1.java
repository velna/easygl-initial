package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;

public class C23HelloTriangleExercises1 {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var vertex = Shader.vertex();
             var fragment = Shader.fragment();
             var program = Program.of();
             var vao = VertexArray.of();
             var vbo = Buffer.of(DataType.Float)) {
            window.bind().inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE)
                    .subscribe((event) -> event.source().window().shouldClose(true));

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

            vao.bind().attributes(vbo.bind(Buffer.Type.Array)
                    .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                            // first triangle
                            -0.9f, -0.45f, 0.0f,  // left
                            -0.0f, -0.45f, 0.0f,  // right
                            -0.45f, 0.45f, 0.0f,  // top
                            // second triangle
                            0.0f, -0.45f, 0.0f,  // left
                            0.9f, -0.45f, 0.0f,  // right
                            0.45f, 0.45f, 0.0f   // top
                    }), 3);

            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.Color);

                program.bind();
                vao.drawArray(DrawMode.Triangles, vbo);

                window.swapBuffers().pollEvents();
            }
        }
    }

}
