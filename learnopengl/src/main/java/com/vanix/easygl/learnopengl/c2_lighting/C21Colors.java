package com.vanix.easygl.learnopengl.c2_lighting;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.ControllableCamera;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class C21Colors {

    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var lightingProgram = Program.of("p1");
             var lightCubeProgram = Program.of("p1");
             var cubeVAO = VertexArray.of();
             var lightCubeVAO = VertexArray.of();
             var vbo = Buffer.ofArray(cubeVAO, DataType.Float)) {

            window.inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            graphics.depth().enable();

            lightingProgram.attach(Shader.Type.Vertex, """
                            #version 330 core
                            layout (location = 0) in vec3 aPos;
                                                        
                            uniform mat4 model;
                            uniform mat4 view;
                            uniform mat4 projection;
                                                        
                            void main()
                            {
                            	gl_Position = projection * view * model * vec4(aPos, 1.0);
                            }
                            """)
                    .attach(Shader.Type.Fragment, """
                            #version 330 core
                            out vec4 FragColor;
                                                        
                            uniform vec3 objectColor;
                            uniform vec3 lightColor;
                                                        
                            void main()
                            {
                                FragColor = vec4(lightColor * objectColor, 1.0);
                            }
                            """)
                    .link();

            lightCubeProgram.attach(Shader.Type.Vertex, """
                            #version 330 core
                            layout (location = 0) in vec3 aPos;
                                                        
                            uniform mat4 model;
                            uniform mat4 view;
                            uniform mat4 projection;
                                                        
                            void main()
                            {
                            	gl_Position = projection * view * model * vec4(aPos, 1.0);
                            }
                            """)
                    .attach(Shader.Type.Fragment, """
                            #version 330 core
                             out vec4 FragColor;
                             
                             void main()
                             {
                                 FragColor = vec4(1.0); // set all 4 vector values to 1.0
                             }
                            """)
                    .link();

            vbo.bind().realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
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
            cubeVAO.bind().attributes(vbo, 3);
            lightCubeVAO.bind().attributes(vbo, 3);


            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            var lightPos = new Vector3f(1.2f, 1.0f, 2.0f);
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);

            while (!window.shouldClose()) {
                graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(Graphics.BufferMask.Color, Graphics.BufferMask.Depth);

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);
                var view = camera.update().view();

                lightingProgram.bind()
                        .setVec3("objectColor", 1.0f, 0.5f, 0.31f)
                        .setVec3("lightColor", 1.0f, 1.0f, 1.0f)
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f))
                        .set("model", new Matrix4f());

                cubeVAO.bind().drawArray(DrawMode.Triangles, vbo);

                lightCubeProgram.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f))
                        .set("model", new Matrix4f().translate(lightPos).scale(0.2f));
                lightCubeVAO.bind().drawArray(DrawMode.Triangles, vbo);

                window.swapBuffers().pollEvents();
            }
        }
    }

}
