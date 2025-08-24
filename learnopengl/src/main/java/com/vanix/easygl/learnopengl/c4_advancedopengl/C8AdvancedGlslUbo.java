package com.vanix.easygl.learnopengl.c4_advancedopengl;

import com.vanix.easygl.core.g3d.ControllableCamera;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import lombok.Data;
import org.joml.Math;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public class C8AdvancedGlslUbo {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenglForwardCompat.set(true);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var programRed = Program.of();
             var programGreen = Program.of();
             var programBlue = Program.of();
             var programYellow = Program.of();
             var ubo = Buffer.of(DataType.Byte);
             var cubeVAO = VertexArray.of();
             var cubeVBO = Buffer.of(DataType.Float)) {

            window
                    .inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);

            graphics.depthTest().enable();

            programRed.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/8.advanced_glsl.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/8.red.fs")
                    .link();
            programGreen.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/8.advanced_glsl.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/8.green.fs")
                    .link();
            programBlue.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/8.advanced_glsl.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/8.blue.fs")
                    .link();
            programYellow.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/8.advanced_glsl.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/8.yellow.fs")
                    .link();

            cubeVBO.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, new float[]{
                    // positions
                    -0.5f, -0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f,
                    0.5f, 0.5f, -0.5f,
                    0.5f, 0.5f, -0.5f,
                    -0.5f, 0.5f, -0.5f,
                    -0.5f, -0.5f, -0.5f,

                    -0.5f, -0.5f, 0.5f,
                    0.5f, -0.5f, 0.5f,
                    0.5f, 0.5f, 0.5f,
                    0.5f, 0.5f, 0.5f,
                    -0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, 0.5f,

                    -0.5f, 0.5f, 0.5f,
                    -0.5f, 0.5f, -0.5f,
                    -0.5f, -0.5f, -0.5f,
                    -0.5f, -0.5f, -0.5f,
                    -0.5f, -0.5f, 0.5f,
                    -0.5f, 0.5f, 0.5f,

                    0.5f, 0.5f, 0.5f,
                    0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f,
                    0.5f, -0.5f, 0.5f,
                    0.5f, 0.5f, 0.5f,

                    -0.5f, -0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f,
                    0.5f, -0.5f, 0.5f,
                    0.5f, -0.5f, 0.5f,
                    -0.5f, -0.5f, 0.5f,
                    -0.5f, -0.5f, -0.5f,

                    -0.5f, 0.5f, -0.5f,
                    0.5f, 0.5f, -0.5f,
                    0.5f, 0.5f, 0.5f,
                    0.5f, 0.5f, 0.5f,
                    -0.5f, 0.5f, 0.5f,
                    -0.5f, 0.5f, -0.5f,
            });
            cubeVAO.bind().attributes(cubeVBO, 3);

            var matricesUniformBlock = programRed.getUniformBlock("Matrices");
            var bindingPoint = ubo.bind(Buffer.Target.Uniform)
                    .realloc(Buffer.DataUsage.StaticDraw, matricesUniformBlock.getBufferDataSize())
                    .bindAt(0);
            matricesUniformBlock.bind(bindingPoint);
            var matricesMapping = bindingPoint.createMapping(new Matrices(), matricesUniformBlock);
            programGreen.getUniformBlock("Matrices").bind(bindingPoint);
            programBlue.getUniformBlock("Matrices").bind(bindingPoint);

            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);

            while (!window.shouldClose()) {
                graphics.depthTest().enable().then()
                        .defaultFrameBuffer().setClearColor(0.1f, 0.1f, 0.1f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);
                var view = camera.update().view();
                matricesMapping.getBean().setProjection(projection);
                matricesMapping.getBean().setView(new Matrix4f(view));
                matricesMapping.flush();

                programRed.bind()
                        .setMatrix4("model", new Matrix4f().translate(-0.75f, 0.75f, 0.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeVBO);

                programGreen.bind()
                        .setMatrix4("model", new Matrix4f().translate(0.75f, 0.75f, 0.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeVBO);

                programBlue.bind()
                        .setMatrix4("model", new Matrix4f().translate(-0.75f, -0.75f, 0.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeVBO);

                programYellow.bind()
                        .setMatrix4("model", new Matrix4f().translate(0.75f, -0.75f, 0.0f).get(mat4f));
                cubeVAO.bind().drawArray(DrawMode.Triangles, cubeVBO);

                window.swapBuffers().pollEvents();
            }
            matricesMapping.close();
        }
    }

    @Data
    public static class Matrices {
        private Matrix4f projection;
        private Matrix4f view;
    }
}
