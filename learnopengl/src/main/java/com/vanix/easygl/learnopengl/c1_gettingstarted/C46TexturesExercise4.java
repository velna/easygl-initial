package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import com.vanix.easygl.core.input.event.KeyboardEvent;

import java.io.IOException;

public class C46TexturesExercise4 {

    static float mixValue = 0.2f;

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
            window.bind().inputs().keyboard().onKey()
                    .subscribe(C46TexturesExercise4::processInput);

            program.attachResource(Shader.Type.Vertex, "shaders/1_getting_started/4.6.texture.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/1_getting_started/4.6.texture.fs")
                    .link();

            vao.bind().attributes(vbo.bind(Buffer.Target.Array)
                    .realloc(Buffer.DataUsage.StaticDraw, new float[]{
                            // positions          // colors           // texture coords
                            0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, // top right
                            0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, // bottom right
                            -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, // bottom left
                            -0.5f, 0.5f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f  // top left
                    }), 3, 3, 2);
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

            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.Color);

                Texture.Unit.U0.bind();
                texture1.bind(Texture.Target.T2D);
                Texture.Unit.U1.bind();
                texture2.bind(Texture.Target.T2D);

                program.bind().setFloat("mixValue", mixValue);
                vao.drawElements(DrawMode.Triangles, vbo, ebo, 0);

                window.swapBuffers().pollEvents();
            }
        }
    }

    static void processInput(KeyboardEvent event) {
        if (event.key() == Keyboard.FunctionKey.ESCAPE) {
            event.source().window().shouldClose(true);
        }
        if (event.source().isPressed(Keyboard.FunctionKey.UP)) {
            mixValue = Math.min(mixValue + 0.001f, 1.0f);
        }
        if (event.source().isPressed(Keyboard.FunctionKey.DOWN)) {
            mixValue = Math.max(mixValue - 0.001f, 0.0f);
        }
    }
}
