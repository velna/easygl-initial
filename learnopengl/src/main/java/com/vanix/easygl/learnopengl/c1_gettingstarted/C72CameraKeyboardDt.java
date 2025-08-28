package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public class C72CameraKeyboardDt {

    // camera
    private static Vector3f cameraPos = new Vector3f(0.0f, 0.0f, 3.0f);
    private static Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
    private static Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
    // timing
    private static float deltaTime = 0.0f;    // time between current frame and last frame
    private static float lastFrame = 0.0f;

    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of();
             var vao = VertexArray.of();
             var vbo = Buffer.of(DataType.Float);
             var texture1 = Texture.of2D();
             var texture2 = Texture.of2D()) {

            graphics.depthTest().enable();

            program.attachResource(Shader.Type.Vertex, "shaders/1_getting_started/7.2.camera.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/1_getting_started/7.2.camera.fs")
                    .link();

            vao.bind().enableAttributes(vbo.bind(Buffer.Target.Array)
                    .realloc(Buffer.DataUsage.StaticDraw, new float[]{
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
                    }), 3f, 2f);

            var cubePositions = new Vector3f[]{
                    new Vector3f(0.0f, 0.0f, 0.0f),
                    new Vector3f(2.0f, 5.0f, -15.0f),
                    new Vector3f(-1.5f, -2.2f, -2.5f),
                    new Vector3f(-3.8f, -2.0f, -12.3f),
                    new Vector3f(2.4f, -0.4f, -3.5f),
                    new Vector3f(-1.7f, 3.0f, -7.5f),
                    new Vector3f(1.3f, -2.0f, -2.5f),
                    new Vector3f(1.5f, 2.0f, -2.5f),
                    new Vector3f(1.5f, 0.2f, -1.5f),
                    new Vector3f(-1.3f, 1.0f, -1.5f)};

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

            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);
            program.bind()
                    .setInt("texture1", 0)
                    .setInt("texture2", 1)
                    .setMatrix4("projection", new Matrix4f()
                            .perspective(Math.toRadians(45.0f), window.getAspect(), 0.1f, 100.0f)
                            .get(mat4f));

            long start = System.currentTimeMillis();

            while (!window.shouldClose()) {
                float currentFrame = (System.currentTimeMillis() - start) / 1000.0f;
                deltaTime = currentFrame - lastFrame;
                lastFrame = currentFrame;

                processInput(window.inputs().keyboard());

                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                Texture.Unit.U0.bind();
                texture1.bind(Texture.Target.T2D);
                Texture.Unit.U1.bind();
                texture2.bind(Texture.Target.T2D);

                program.bind()
                        .setMatrix4("view", new Matrix4f()
                                .lookAt(cameraPos, cameraPos.add(cameraFront, new Vector3f()), cameraUp)
                                .get(mat4f));
                for (var i = 0; i < cubePositions.length; i++) {
                    program.setMatrix4("model", new Matrix4f()
                            .translate(cubePositions[i])
                            .rotate(Math.toRadians(20.0f * i), new Vector3f(1.0f, 0.3f, 0.5f))
                            .get(mat4f));
                    vao.drawArray(DrawMode.Triangles, vbo);
                }

                window.swapBuffers().pollEvents();
            }
        }
    }

    static void processInput(Keyboard keyboard) {
        if (keyboard.isPressed(Keyboard.FunctionKey.ESCAPE)) {
            keyboard.window().shouldClose(true);
        }

        float cameraSpeed = 2.5f * deltaTime;
        if (keyboard.isPressed(Keyboard.PrintableKey.W)) {
            cameraPos.add(cameraFront.mul(cameraSpeed, new Vector3f()));
        }
        if (keyboard.isPressed(Keyboard.PrintableKey.S)) {
            cameraPos.sub(cameraFront.mul(cameraSpeed, new Vector3f()));
        }
        if (keyboard.isPressed(Keyboard.PrintableKey.A)) {
            cameraPos.sub(cameraFront.cross(cameraUp, new Vector3f()).normalize().mul(cameraSpeed));
        }
        if (keyboard.isPressed(Keyboard.PrintableKey.D)) {
            cameraPos.add(cameraFront.cross(cameraUp, new Vector3f()).normalize().mul(cameraSpeed));
        }
    }
}
