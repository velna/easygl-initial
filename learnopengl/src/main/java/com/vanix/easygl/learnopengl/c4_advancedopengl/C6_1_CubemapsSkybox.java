package com.vanix.easygl.learnopengl.c4_advancedopengl;

import com.vanix.easygl.core.g3d.ControllableCamera;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Math;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public class C6_1_CubemapsSkybox {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenglForwardCompat.set(true);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of();
             var skyboxProgram = Program.of();
             var cubeVAO = VertexArray.of();
             var cubeVBO = Buffer.of(DataType.Float);
             var skyboxVAO = VertexArray.of();
             var skyboxVBO = Buffer.of(DataType.Float);
             var cubeTexture = Texture2D.of();
             var cubemapTexture = TextureCubeMap.of()) {

            window
                    .inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);

            graphics.depthTest().enable();

            program.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/6.1.cubemaps.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/6.1.cubemaps.fs")
                    .link();
            skyboxProgram.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/6.1.skybox.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/6.1.skybox.fs")
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

            skyboxVBO.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, new float[]{
                    // positions
                    -1.0f, 1.0f, -1.0f,
                    -1.0f, -1.0f, -1.0f,
                    1.0f, -1.0f, -1.0f,
                    1.0f, -1.0f, -1.0f,
                    1.0f, 1.0f, -1.0f,
                    -1.0f, 1.0f, -1.0f,

                    -1.0f, -1.0f, 1.0f,
                    -1.0f, -1.0f, -1.0f,
                    -1.0f, 1.0f, -1.0f,
                    -1.0f, 1.0f, -1.0f,
                    -1.0f, 1.0f, 1.0f,
                    -1.0f, -1.0f, 1.0f,

                    1.0f, -1.0f, -1.0f,
                    1.0f, -1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, -1.0f,
                    1.0f, -1.0f, -1.0f,

                    -1.0f, -1.0f, 1.0f,
                    -1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, -1.0f, 1.0f,
                    -1.0f, -1.0f, 1.0f,

                    -1.0f, 1.0f, -1.0f,
                    1.0f, 1.0f, -1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    -1.0f, 1.0f, 1.0f,
                    -1.0f, 1.0f, -1.0f,

                    -1.0f, -1.0f, -1.0f,
                    -1.0f, -1.0f, 1.0f,
                    1.0f, -1.0f, -1.0f,
                    1.0f, -1.0f, -1.0f,
                    -1.0f, -1.0f, 1.0f,
                    1.0f, -1.0f, 1.0f
            });
            var skyBoxTriangleCount = skyboxVAO.bind().enableAttributes(3f).countOfStride();

            cubeTexture.bind()
                    .minFilter(MinFilter.LinearMipmapLinear)
                    .load("textures/container.jpg")
                    .generateMipmap();

            cubemapTexture.bind()
                    .minFilter(MinFilter.Linear)
                    .magFilter(MagFilter.Linear)
                    .wrapS(Texture.Wrap.ClampToEdge)
                    .wrapT(Texture.Wrap.ClampToEdge)
                    .wrapR(Texture.Wrap.ClampToEdge)
                    .load(
                            "textures/skybox/right.jpg",
                            "textures/skybox/left.jpg",
                            "textures/skybox/top.jpg",
                            "textures/skybox/bottom.jpg",
                            "textures/skybox/front.jpg",
                            "textures/skybox/back.jpg");

            program.bind().setInt("texture1", 0);
            skyboxProgram.bind().setInt("skybox", 0);

            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);

            while (!window.shouldClose()) {
                graphics.depthTest().enable().then()
                        .defaultFrameBuffer().setClearColor(0.1f, 0.1f, 0.1f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);
                var view = camera.update().view();

                program.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f));
                cubeTexture.bind();
                program.setMatrix4("model", new Matrix4f().get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeTriangleCount);

                graphics.depthTest().setFunction(CompareFunction.LessEqual);
                skyboxProgram.bind()
                        .setMatrix4("view", new Matrix4f(view.get3x3(new Matrix3f())).get(mat4f))
                        .setMatrix4("projection", projection.get(mat4f));
                cubemapTexture.bind();
                skyboxVAO.bind().drawArray(DrawMode.Triangles, skyBoxTriangleCount);
                graphics.depthTest().setFunction(CompareFunction.LessThan);

                window.swapBuffers().pollEvents();
            }
        }
    }

}
