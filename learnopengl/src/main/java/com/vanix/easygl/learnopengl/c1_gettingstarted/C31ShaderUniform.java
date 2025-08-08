package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;

public class C31ShaderUniform {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var vertex = Shader.vertex("v1");
             var fragment = Shader.fragment("f1");
             var program = Program.of("p1");
             var vao = VertexArray.of();
             var vbo = Buffer.ofArray(vao, DataType.Float)) {
            window.bind().inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE)
                    .subscribe((event) -> event.source().window().shouldClose(true));

            program.attach(vertex.source("""
                                    #version 330 core
                                    layout (location = 0) in vec3 aPos;
                                    void main() {
                                        gl_Position = vec4(aPos, 1.0);
                                    }
                                    """)
                            .compile())
                    .attach(fragment.source("""
                                    #version 330 core
                                    out vec4 FragColor;
                                    uniform vec4 ourColor;
                                    void main(){
                                        FragColor = ourColor;
                                    }
                                    """)
                            .compile())
                    .link();

            vao.bind().attributes(vbo.bind()
                    .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                            0.5f, -0.5f, 0.0f,  // bottom right
                            -0.5f, -0.5f, 0.0f,  // bottom left
                            0.0f, 0.5f, 0.0f   // top
                    }), 3);

            while (!window.shouldClose()) {
                graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(Graphics.BufferMask.Color);

                program.bind();
                float greenValue = (float) (Math.sin(System.currentTimeMillis() / 1000.0) / 2.0 + 0.5);
                program.set("ourColor", 0.0f, greenValue, 0.0f, 1.0f);
                vao.drawArray(DrawMode.Triangles, vbo);

                window.swapBuffers().pollEvents();
            }
        }
    }

}