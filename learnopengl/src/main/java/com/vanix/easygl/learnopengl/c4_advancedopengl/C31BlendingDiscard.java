package com.vanix.easygl.learnopengl.c4_advancedopengl;

import com.vanix.easygl.core.g3d.ControllableCamera;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.List;

public class C31BlendingDiscard {
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
             var transparentVAO = VertexArray.of();
             var transparentVBO = Buffer.of(DataType.Float);
             var cubeTexture = Texture.of2D();
             var floorTexture = Texture.of2D();
             var transparentTexture = Texture.of2D()) {

            window
                    .attributes().Resizable.disable()
                    .inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);

            graphics.depthTest().enable();

            program.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/3.1.blending.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/3.1.blending.fs")
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
                    5.0f, -0.5f, 5.0f, 2.0f, 0.0f,
                    -5.0f, -0.5f, 5.0f, 0.0f, 0.0f,
                    -5.0f, -0.5f, -5.0f, 0.0f, 2.0f,

                    5.0f, -0.5f, 5.0f, 2.0f, 0.0f,
                    -5.0f, -0.5f, -5.0f, 0.0f, 2.0f,
                    5.0f, -0.5f, -5.0f, 2.0f, 2.0f
            });
            var planeTriangleCount = planeVAO.bind().enableAttributes(3f, 2f).countOfStride();


            transparentVBO.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, new float[]{
                    // positions         // texture Coords (swapped y coordinates because texture is flipped upside down)
                    0.0f, 0.5f, 0.0f, 0.0f, 0.0f,
                    0.0f, -0.5f, 0.0f, 0.0f, 1.0f,
                    1.0f, -0.5f, 0.0f, 1.0f, 1.0f,

                    0.0f, 0.5f, 0.0f, 0.0f, 0.0f,
                    1.0f, -0.5f, 0.0f, 1.0f, 1.0f,
                    1.0f, 0.5f, 0.0f, 1.0f, 0.0f
            });
            var transparentTriangleCount = transparentVAO.bind().enableAttributes(3f, 2f).countOfStride();

            cubeTexture.bind(Texture.Target.T2D)
                    .minFilter(MinFilter.LinearMipmapLinear)
                    .load("textures/marble.jpg")
                    .generateMipmap();
            floorTexture.bind(Texture.Target.T2D)
                    .minFilter(MinFilter.LinearMipmapLinear)
                    .load("textures/metal.png")
                    .generateMipmap();
            transparentTexture.bind(Texture.Target.T2D)
                    .wrapS(Texture.Wrap.ClampToEdge)
                    .wrapT(Texture.Wrap.ClampToEdge)
                    .minFilter(MinFilter.LinearMipmapLinear)
                    .load("textures/grass.png")
                    .generateMipmap();

            var vegetation = List.of(
                    new Vector3f(-1.5f, 0.0f, -0.48f),
                    new Vector3f(1.5f, 0.0f, 0.51f),
                    new Vector3f(0.0f, 0.0f, 0.7f),
                    new Vector3f(-0.3f, 0.0f, -2.3f),
                    new Vector3f(0.5f, 0.0f, -0.6f));

            program.bind().setInt("texture1", 0);

            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);

            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.1f, 0.1f, 0.1f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);
                var view = camera.update().view();

                program.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f));

                // cubes
                cubeVAO.bind();
                cubeTexture.bind(Texture.Target.T2D);
                program.setMatrix4("model", new Matrix4f().translate(-1.0f, 0.0f, -1.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeTriangleCount);
                program.setMatrix4("model", new Matrix4f().translate(2.0f, 0.0f, 0.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeTriangleCount);

                //floor
                floorTexture.bind(Texture.Target.T2D);
                program.setMatrix4("model", new Matrix4f().get(mat4f));
                planeVAO.bind().drawArray(DrawMode.Triangles, planeTriangleCount);

                //
                transparentVAO.bind();
                transparentTexture.bind(Texture.Target.T2D);
                for (var vec : vegetation) {
                    program.setMatrix4("model", new Matrix4f().translate(vec).get(mat4f));
                    transparentVAO.bind().drawArray(DrawMode.Triangles, transparentTriangleCount);
                }

                window.swapBuffers().pollEvents();
            }
        }
    }

}
