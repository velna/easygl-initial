package com.vanix.easygl.learnopengl.c4_advancedopengl;

import com.vanix.easygl.core.g3d.ControllableCamera;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Math;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public class C51FrameBuffer {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenglForwardCompat.set(true);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of();
             var screenProgram = Program.of();
             var cubeVAO = VertexArray.of();
             var cubeVBO = Buffer.of(DataType.Float);
             var planeVAO = VertexArray.of();
             var planeVBO = Buffer.of(DataType.Float);
             var quadVAO = VertexArray.of();
             var quadVBO = Buffer.of(DataType.Float);
             var frameBuffer = FrameBuffer.of();
             var textureColor = Texture.of2D();
             var cubeTexture = Texture.of2D();
             var renderBuffer = RenderBuffer.of();
             var floorTexture = Texture.of2D()) {

            window
                    .inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);

            graphics.depthTest().enable();

            program.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/5.1.framebuffers.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/5.1.framebuffers.fs")
                    .link();
            screenProgram.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/5.1.framebuffers_screen.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/5.1.framebuffers_screen.fs")
                    .link();

            cubeVBO.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, new float[]{
                    // positions          // texture Coords
                    -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,
                    0.5f, -0.5f, -0.5f, 1.0f, 0.0f,
                    0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                    0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                    -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
                    -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,

                    -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                    0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
                    0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
                    0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
                    -0.5f, 0.5f, 0.5f, 0.0f, 1.0f,
                    -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,

                    -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                    -0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                    -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                    -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                    -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                    -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

                    0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                    0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                    0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                    0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                    0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                    0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

                    -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                    0.5f, -0.5f, -0.5f, 1.0f, 1.0f,
                    0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
                    0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
                    -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                    -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,

                    -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
                    0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                    0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                    0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                    -0.5f, 0.5f, 0.5f, 0.0f, 0.0f,
                    -0.5f, 0.5f, -0.5f, 0.0f, 1.0f
            });

            cubeVAO.bind().attributes(cubeVBO, 3f, 2f);

            planeVBO.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, new float[]{
                    // positions          // texture Coords (note we set these higher than 1 (together with GL_REPEAT as texture wrapping mode). this will cause the floor texture to repeat)
                    5.0f, -0.5f, 5.0f, 2.0f, 0.0f,
                    -5.0f, -0.5f, 5.0f, 0.0f, 0.0f,
                    -5.0f, -0.5f, -5.0f, 0.0f, 2.0f,

                    5.0f, -0.5f, 5.0f, 2.0f, 0.0f,
                    -5.0f, -0.5f, -5.0f, 0.0f, 2.0f,
                    5.0f, -0.5f, -5.0f, 2.0f, 2.0f
            });
            planeVAO.bind().attributes(planeVBO, 3f, 2f);

            quadVBO.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, new float[]{
                    // vertex attributes for a quad that fills the entire screen in Normalized Device Coordinates.
                    // positions   // texCoords
                    -1.0f, 1.0f, 0.0f, 1.0f,
                    -1.0f, -1.0f, 0.0f, 0.0f,
                    1.0f, -1.0f, 1.0f, 0.0f,

                    -1.0f, 1.0f, 0.0f, 1.0f,
                    1.0f, -1.0f, 1.0f, 0.0f,
                    1.0f, 1.0f, 1.0f, 1.0f
            });
            quadVAO.bind().attributes(quadVBO, 2f, 2f);

            cubeTexture.bind(Texture.Target.T2D)
                    .minFilter(MinFilter.LinearMipmapLinear)
                    .load("textures/container.jpg")
                    .generateMipmap();
            floorTexture.bind(Texture.Target.T2D)
                    .minFilter(MinFilter.LinearMipmapLinear)
                    .load("textures/metal.png")
                    .generateMipmap();

            program.bind().setInt("texture1", 0);
            screenProgram.bind().setInt("screenTexture", 0);

            frameBuffer.bindDraw()
                    .attach(FrameInnerBuffer.Attachment.ofColor(0),
                            textureColor.bind(Texture.Target.T2D)
                                    .minFilter(MinFilter.Linear)
                                    .magFilter(MagFilter.Linear)
                                    .load(Image.empty(PixelFormat.RGB, window.frameBufferWidth(), window.frameBufferHeight())))
                    .attach(FrameInnerBuffer.Attachment.DepthStencil,
                            renderBuffer.bind().storage(InternalPixelFormat.BaseFormat.DEPTH24_STENCIL8, window.frameBufferWidth(), window.frameBufferHeight()))
                    .checkStatus();

            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);

            while (!window.shouldClose()) {
                graphics.depthTest().enable();
                frameBuffer.bind().setClearColor(0.1f, 0.1f, 0.1f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);
                var view = camera.update().view();

                program.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f));

                cubeTexture.bind(Texture.Target.T2D, Texture.Unit.U0);
                program.setMatrix4("model", new Matrix4f().translate(-1.0f, 0.0f, -1.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeVBO);
                program.setMatrix4("model", new Matrix4f().translate(2.0f, 0.0f, 0.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeVBO);

                floorTexture.bind();
                program.setMatrix4("model", new Matrix4f().get(mat4f));
                planeVAO.bind().drawArray(DrawMode.Triangles, planeVBO);

                graphics.depthTest().disable().then()
                        .defaultFrameBuffer().bind()
                        .setClearColor(0.0f, 0.0f, 1.0f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.Color);

                screenProgram.bind();
                textureColor.bind();
                quadVAO.bind().drawArray(DrawMode.Triangles, quadVBO);

                window.swapBuffers().pollEvents();
            }
        }
    }

}
