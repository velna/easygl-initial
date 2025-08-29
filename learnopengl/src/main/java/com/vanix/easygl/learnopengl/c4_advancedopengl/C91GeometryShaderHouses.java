package com.vanix.easygl.learnopengl.c4_advancedopengl;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;

import java.io.IOException;

public class C91GeometryShaderHouses {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenglForwardCompat.set(true);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of();
             var vao = VertexArray.of();
             var vbo = Buffer.of(DataType.Float)) {

            window
                    .inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);

            graphics.depthTest().enable();

            program.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/9.1.geometry_shader.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/9.1.geometry_shader.fs")
                    .attachResource(Shader.Type.Geometry, "shaders/4_advanced_opengl/9.1.geometry_shader.gs")
                    .link();

            vbo.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, new float[]{
                    -0.5f, 0.5f, 1.0f, 0.0f, 0.0f, // top-left
                    0.5f, 0.5f, 0.0f, 1.0f, 0.0f, // top-right
                    0.5f, -0.5f, 0.0f, 0.0f, 1.0f, // bottom-right
                    -0.5f, -0.5f, 1.0f, 1.0f, 0.0f  // bottom-left
            });
            var triangleCount = vao.bind().enableAttributes(2f, 3f).countOfStride();

            while (!window.shouldClose()) {
                graphics.depthTest().enable().then()
                        .defaultFrameBuffer().setClearColor(0.1f, 0.1f, 0.1f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                program.bind();
                vao.bind().drawArray(DrawMode.Points, triangleCount);

                window.swapBuffers().pollEvents();
            }
        }
    }

}
