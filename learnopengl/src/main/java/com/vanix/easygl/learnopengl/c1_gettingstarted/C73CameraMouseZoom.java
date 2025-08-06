package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import com.vanix.easygl.core.input.event.MouseMoveEvent;
import com.vanix.easygl.core.input.event.MouseScrollEvent;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class C73CameraMouseZoom {

    // camera
    private static Vector3f cameraPos = new Vector3f(0.0f, 0.0f, 3.0f);
    private static Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
    private static Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);

    private static boolean firstMouse = true;
    private static float yaw = -90.0f;    // yaw is initialized to -90.0 degrees since a yaw of 0.0 results in a direction vector pointing to the right so we initially rotate a bit to the left.
    private static float pitch = 0.0f;
    private static float lastX = 800.0f / 2;
    private static float lastY = 600.0f / 2;
    private static float fov = 45.0f;

    // timing
    private static float deltaTime = 0.0f;    // time between current frame and last frame
    private static float lastFrame = 0.0f;

    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of("p1");
             var vao = VertexArray.of();
             var vbo = Buffer.ofArray(vao, DataType.Float);
             var texture1 = Texture.of2D("t1");
             var texture2 = Texture.of2D("t2")) {
            window.inputCtlr().mouse().onMove().subscribe(C73CameraMouseZoom::onMouseMove);
            window.inputCtlr().mouse().onScroll().subscribe(C73CameraMouseZoom::onMouseScroll);

            graphics.depth().enable();

            program.attach(Shader.Type.Vertex, """
                            #version 330 core
                            layout (location = 0) in vec3 aPos;
                            layout (location = 1) in vec2 aTexCoord;
                                                        
                            out vec2 TexCoord;
                                                        
                            uniform mat4 model;
                            uniform mat4 view;
                            uniform mat4 projection;
                                                        
                            void main()
                            {
                            	gl_Position = projection * view * model * vec4(aPos, 1.0f);
                            	TexCoord = vec2(aTexCoord.x, aTexCoord.y);
                            }
                            """)
                    .attach(Shader.Type.Fragment, """
                            #version 330 core
                            out vec4 FragColor;
                                                        
                            in vec2 TexCoord;
                                                        
                            // texture samplers
                            uniform sampler2D texture1;
                            uniform sampler2D texture2;
                                                        
                            void main()
                            {
                            	// linearly interpolate between both textures (80% container, 20% awesomeface)
                            	FragColor = mix(texture(texture1, TexCoord), texture(texture2, TexCoord), 0.2);
                            }
                            """)
                    .link();

            vao.bind().attributes(vbo.bind()
                    .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
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
                    }), 3, 2);

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

            texture1.bind()
                    .wrapS(Texture.Wrap.Repeat)
                    .wrapT(Texture.Wrap.Repeat)
                    .minFilter(Texture.MinFilter.Linear)
                    .magFilter(Texture.MagFilter.Linear);
            try (var image = Image.load("textures/container.jpg")) {
                texture1.load(image).generateMipmap();
            }

            texture2.bind()
                    .wrapS(Texture.Wrap.Repeat)
                    .wrapT(Texture.Wrap.Repeat)
                    .minFilter(Texture.MinFilter.Linear)
                    .magFilter(Texture.MagFilter.Linear);
            try (var image = Image.load("textures/awesomeface.png")) {
                texture2.load(image, 0, InternalPixelFormat.BaseFormat.RGB).generateMipmap();
            }

            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);
            program.bind()
                    .set("texture1", 0)
                    .set("texture2", 1);

            long start = System.currentTimeMillis();

            while (!window.shouldClose()) {
                float currentFrame = (System.currentTimeMillis() - start) / 1000.0f;
                deltaTime = currentFrame - lastFrame;
                lastFrame = currentFrame;

                processInput(window.inputCtlr().keyboard());

                graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(Graphics.BufferMask.Color, Graphics.BufferMask.Depth);

                Texture.Unit.U0.bind();
                texture1.bind();
                Texture.Unit.U1.bind();
                texture2.bind();

                program.bind()
                        .setMatrix4("projection", new Matrix4f()
                                .perspective(Math.toRadians(fov), window.width() * 1.0f / window.height(), 0.1f, 100.0f)
                                .get(mat4f))
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

    static void onMouseMove(MouseMoveEvent event) {
        float xpos = (float) event.x();
        float ypos = (float) event.y();

        if (firstMouse) {
            lastX = xpos;
            lastY = ypos;
            firstMouse = false;
        }

        float xoffset = xpos - lastX;
        float yoffset = lastY - ypos; // reversed since y-coordinates go from bottom to top
        lastX = xpos;
        lastY = ypos;

        float sensitivity = 0.1f; // change this value to your liking
        xoffset *= sensitivity;
        yoffset *= sensitivity;

        yaw += xoffset;
        pitch += yoffset;

        // make sure that when pitch is out of bounds, screen doesn't get flipped
        if (pitch > 89.0f) {
            pitch = 89.0f;
        }
        if (pitch < -89.0f) {
            pitch = -89.0f;
        }

        cameraFront = new Vector3f(
                Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)),
                Math.sin(Math.toRadians(pitch)),
                Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)))
                .normalize();
    }

    static void onMouseScroll(MouseScrollEvent event) {
        fov -= (float) event.yOffset();
        if (fov < 1.0f) {
            fov = 1.0f;
        }
        if (fov > 45.0f) {
            fov = 45.0f;
        }
    }
}
