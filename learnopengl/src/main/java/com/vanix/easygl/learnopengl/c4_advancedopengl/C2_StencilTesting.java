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

public class C2_StencilTesting {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of();
             var singleColorProgram = Program.of();
             var cubeVAO = VertexArray.of();
             var cubeVBO = Buffer.of(DataType.Float);
             var planeVAO = VertexArray.of();
             var planeVBO = Buffer.of(DataType.Float);
             var cubeTexture = Texture.of2D();
             var floorTexture = Texture.of2D()) {

            window
                    .attributes().Resizable.disable()
                    .inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);

            var depthTest = graphics.depthTest().enable().setFunction(CompareFunction.LessThan);
            var stencilTest = graphics.stencilTest().enable()
                    .setFunction(CompareFunction.NotEqual, 1, 0xff)
                    .setOps(StencilTest.Op.Keep, StencilTest.Op.Keep, StencilTest.Op.Replace);
            var frameBuffer = graphics.defaultFrameBuffer();

            program.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/2.stencil_testing.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/2.stencil_testing.fs")
                    .link();
            singleColorProgram.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/2.stencil_testing.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/2.stencil_single_color.fs")
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
                    // positions          // texture Coords
                    // (note we set these higher than 1 (together with GL_REPEAT as texture wrapping mode).
                    // this will cause the floor texture to repeat)
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

            program.bind().setInt("texture1", 0);

            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);

            while (!window.shouldClose()) {
                frameBuffer.setClearColor(0.1f, 0.1f, 0.1f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.All);

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);
                var view = camera.update().view();

                singleColorProgram.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f));

                program.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f));

                // draw floor as normal, but don't write the floor to the stencil buffer,
                // we only care about the containers.
                // We set its mask to 0x00 to not write to the stencil buffer.
                frameBuffer.setStencilMask(0x00);

                //floor
                planeVAO.bind();
                floorTexture.bind(Texture.Target.T2D);
                program.setMatrix4("model", new Matrix4f().get(mat4f));
                planeVAO.drawArray(DrawMode.Triangles, planeTriangleCount);

                // 1st. render pass, draw objects as normal, writing to the stencil buffer
                // --------------------------------------------------------------------
                stencilTest.setFunction(CompareFunction.Always, 1, 0xff);
                frameBuffer.setStencilMask(0xff);

                // cubes
                cubeVAO.bind();
                cubeTexture.bind(Texture.Target.T2D, Texture.Unit.U0);
                program.setMatrix4("model", new Matrix4f().translate(-1.0f, 0.0f, -1.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeTriangleCount);
                program.setMatrix4("model", new Matrix4f().translate(2.0f, 0.0f, 0.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeTriangleCount);

                // 2nd. render pass: now draw slightly scaled versions of the objects, this time disabling stencil writing.
                // Because the stencil buffer is now filled with several 1s.
                // The parts of the buffer that are 1 are not drawn, thus only drawing the objects' size differences,
                // making it look like borders.
                stencilTest.setFunction(CompareFunction.NotEqual, 1, 0xff);
                frameBuffer.setStencilMask(0x00);
                depthTest.disable();
                singleColorProgram.bind();
                float scale = 1.1f;
                // cubes
                cubeVAO.bind();
                cubeTexture.bind(Texture.Target.T2D);
                singleColorProgram.setMatrix4("model", new Matrix4f()
                        .translate(-1.0f, 0.0f, -1.0f)
                        .scale(scale)
                        .get(mat4f));
                cubeVAO.drawArray(DrawMode.Triangles, cubeTriangleCount);
                singleColorProgram.setMatrix4("model", new Matrix4f()
                        .translate(2.0f, 0.0f, 0.0f)
                        .scale(scale)
                        .get(mat4f));
                cubeVAO.drawArray(DrawMode.Triangles, cubeTriangleCount);

                frameBuffer.setStencilMask(0xff);
                stencilTest.setFunction(CompareFunction.Always, 0, 0xff);
                depthTest.enable();

                window.swapBuffers().pollEvents();
            }
        }
    }

}
