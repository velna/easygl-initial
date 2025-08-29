package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public class C61CoordinateSystems {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of();
             var vao = VertexArray.of();
             var vbo = Buffer.of(DataType.Float);
             var ebo = Buffer.of(DataType.UnsignedInt);
             var texture1 = Texture.of2D();
             var texture2 = Texture.of2D()) {
            window.bind().inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE)
                    .subscribe((event) -> event.source().window().shouldClose(true));

            program.attachResource(Shader.Type.Vertex, "shaders/1_getting_started/6.1.coordinate_systems.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/1_getting_started/6.1.coordinate_systems.fs")
                    .link();
            vbo.bind(Buffer.Target.Array)
                    .realloc(Buffer.DataUsage.StaticDraw, new float[]{
                            // positions          // texture coords
                            0.5f, 0.5f, 0.0f, 1.0f, 1.0f, // top right
                            0.5f, -0.5f, 0.0f, 1.0f, 0.0f, // bottom right
                            -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, // bottom left
                            -0.5f, 0.5f, 0.0f, 0.0f, 1.0f  // top left
                    });
            vao.bind().enableAttributes(3f, 2f);
            ebo.bind(Buffer.Target.ElementArray).realloc(Buffer.DataUsage.StaticDraw, new int[]{
                    0, 1, 3, // first triangle
                    1, 2, 3  // second triangle
            });

            texture1.bind(Texture.Target.T2D)
                    .wrapS(Texture.Wrap.Repeat)
                    .wrapT(Texture.Wrap.Repeat)
                    .minFilter(MinFilter.Linear)
                    .magFilter(MagFilter.Linear);
            try (var image = Image.load("textures/container.jpg")) {
                texture1.load(image).generateMipmap();
            }

            texture2.bind(Texture.Target.T2D)
                    .wrapS(Texture.Wrap.Repeat)
                    .wrapT(Texture.Wrap.Repeat)
                    .minFilter(MinFilter.Linear)
                    .magFilter(MagFilter.Linear);
            try (var image = Image.load("textures/awesomeface.png")) {
                texture2.load(image, 0, InternalPixelFormat.BaseFormat.RGB).generateMipmap();
            }

            program.bind()
                    .setInt("texture1", 0)
                    .setInt("texture2", 1);

            long start = System.currentTimeMillis();
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);
            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.Color);

                Texture.Unit.U0.bind();
                texture1.bind(Texture.Target.T2D);
                Texture.Unit.U1.bind();
                texture2.bind(Texture.Target.T2D);

                program.bind()
                        .setMatrix4("model", new Matrix4f()
                                .rotate(Math.toRadians(-55.0f), new Vector3f(1.0f, 0.0f, 0.0f))
                                .get(mat4f))
                        .setMatrix4("view", new Matrix4f()
                                .translate(new Vector3f(0.0f, 0.0f, -3.0f))
                                .get(mat4f))
                        .setMatrix4("projection", new Matrix4f()
                                .perspective(Math.toRadians(45.0f), window.getAspect(), 0.1f, 100.0f)
                                .get(mat4f));
                vao.drawElements(DrawMode.Triangles, ebo, 0);

                window.swapBuffers().pollEvents();
            }
        }
    }

}
