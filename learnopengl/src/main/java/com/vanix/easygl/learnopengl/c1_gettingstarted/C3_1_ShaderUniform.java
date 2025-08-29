package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;

public class C3_1_ShaderUniform {
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
            vbo.bind(Buffer.Target.Array)
                    .realloc(Buffer.DataUsage.StaticDraw, new float[]{
                            0.5f, -0.5f, 0.0f,  // bottom right
                            -0.5f, -0.5f, 0.0f,  // bottom left
                            0.0f, 0.5f, 0.0f   // top
                    });
            vao.bind().enableAttributes(3f);

            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.Color);

                program.bind();
                float greenValue = (float) (Math.sin(System.currentTimeMillis() / 1000.0) / 2.0 + 0.5);
                program.setVec4("ourColor", 0.0f, greenValue, 0.0f, 1.0f);
                vao.drawArray(DrawMode.Triangles, 3);

                window.swapBuffers().pollEvents();
            }
        }
    }

}