package com.vanix.easygl.learnopengl.c4_advancedopengl;

import com.vanix.easygl.core.g3d.ControllableCamera;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Math;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public class C1_2_DepthTestingView {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of();
             var cubeVAO = VertexArray.of();
             var cubeVBO = Buffer.of(DataType.Float);
             var planeVAO = VertexArray.of();
             var planeVBO = Buffer.of(DataType.Float);
             var cubeTexture = Texture.of2D();
             var floorTexture = Texture.of2D()) {

            window
                    .attributes().Resizable.disable()
                    .inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_CAPTURED);

            graphics.depthTest().enable().setFunction(CompareFunction.LessThan);

            program.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/1.2.depth_testing.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/1.2.depth_testing.fs")
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

            var cubeTriangleCount = cubeVAO.bind().enableAttributes(3f, 2f).countOfStride();

            planeVBO.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, new float[]{
                    // positions          // texture Coords (note we set these higher than 1 (together with GL_REPEAT as texture wrapping mode). this will cause the floor texture to repeat)
                    5.0f, -0.5f, 5.0f, 2.0f, 0.0f,
                    -5.0f, -0.5f, 5.0f, 0.0f, 0.0f,
                    -5.0f, -0.5f, -5.0f, 0.0f, 2.0f,

                    5.0f, -0.5f, 5.0f, 2.0f, 0.0f,
                    -5.0f, -0.5f, -5.0f, 0.0f, 2.0f,
                    5.0f, -0.5f, -5.0f, 2.0f, 2.0f
            });
            var planeTriangleCount = planeVAO.bind().enableAttributes(3f, 2f).countOfStride();

            cubeTexture.bind(Texture.Target.T2D)
                    .minFilter(MinFilter.LinearMipmapLinear)
                    .load("textures/marble.jpg")
                    .generateMipmap();
            floorTexture.bind(Texture.Target.T2D)
                    .minFilter(MinFilter.LinearMipmapLinear)
                    .load("textures/metal.png")
                    .generateMipmap();

            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);

            long start = System.currentTimeMillis();
            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.1f, 0.1f, 0.1f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                float time = (System.currentTimeMillis() - start) / 1000.0f;


                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);
                var view = camera.update().view();

                program.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f));

                cubeTexture.bind(Texture.Target.T2D, TextureUnit.U0);
                program.setMatrix4("model", new Matrix4f().translate(-1.0f, 0.0f, -1.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeTriangleCount);
                program.setMatrix4("model", new Matrix4f().translate(2.0f, 0.0f, 0.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeTriangleCount);

                floorTexture.bind(Texture.Target.T2D, TextureUnit.U0);
                program.setMatrix4("model", new Matrix4f().get(mat4f));
                planeVAO.bind().drawArray(DrawMode.Triangles, planeTriangleCount);

                window.swapBuffers().pollEvents();
            }
        }
    }

}
