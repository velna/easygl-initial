package com.vanix.easygl.learnopengl.c2_lighting;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.g3d.ControllableCamera;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public class C31Materials {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var lightingProgram = Program.of();
             var lightCubeProgram = Program.of();
             var cubeVAO = VertexArray.of();
             var lightCubeVAO = VertexArray.of();
             var vbo = Buffer.of(DataType.Float)) {

            window.inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            graphics.depthTest().enable();

            lightingProgram.attachResource(Shader.Type.Vertex, "shaders/2_lighting/3.1.materials.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/2_lighting/3.1.materials.fs")
                    .link();

            lightCubeProgram.attachResource(Shader.Type.Vertex, "shaders/2_lighting/3.1.light_cube.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/2_lighting/3.1.light_cube.fs")
                    .link();

            vbo.bind(Buffer.Type.Array).realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                    -0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                    0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                    0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                    0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                    -0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                    -0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f,

                    -0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                    0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                    0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                    0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                    -0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                    -0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f,

                    -0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f,
                    -0.5f, 0.5f, -0.5f, -1.0f, 0.0f, 0.0f,
                    -0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f,
                    -0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f,
                    -0.5f, -0.5f, 0.5f, -1.0f, 0.0f, 0.0f,
                    -0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f,

                    0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f,
                    0.5f, 0.5f, -0.5f, 1.0f, 0.0f, 0.0f,
                    0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f,
                    0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f,
                    0.5f, -0.5f, 0.5f, 1.0f, 0.0f, 0.0f,
                    0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f,

                    -0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f,
                    0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f,
                    0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f,
                    0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f,
                    -0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f,
                    -0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f,

                    -0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f,
                    0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f,
                    0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
                    0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
                    -0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
                    -0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f
            });
            cubeVAO.bind().attributes(vbo, 3, 3);
            lightCubeVAO.bind().attributes(vbo, 3, -3);


            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            var lightPos = new Vector3f(1.2f, 1.0f, 2.0f);
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);

            long start = System.currentTimeMillis();
            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                float time = (System.currentTimeMillis() - start) / 1000.0f;

                lightPos.x = 1.0f + Math.sin(time) * 2.0f;
                lightPos.y = Math.sin(time / 2.0f);

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);
                var view = camera.update().view();

                // light properties
                float sin = Math.sin(time);
                Vector3f lightColor = new Vector3f(sin * 2.0f, sin * 0.7f, sin * 1.3f);
                Vector3f diffuseColor = lightColor.mul(new Vector3f(0.5f), new Vector3f()); // decrease the influence
                Vector3f ambientColor = diffuseColor.mul(new Vector3f(0.2f), new Vector3f()); // low influence

                lightingProgram.bind()
                        // light properties
                        .set("light.position", lightPos)
                        .set("light.ambient", ambientColor)
                        .set("light.diffuse", diffuseColor)
                        .setVec3("light.specular", 1.0f, 1.0f, 1.0f)
                        // material properties
                        .setVec3("material.ambient", 1.0f, 0.5f, 0.31f)
                        .setVec3("material.diffuse", 1.0f, 0.5f, 0.31f)
                        .setVec3("material.specular", 0.5f, 0.5f, 0.5f)// specular lighting doesn't have full effect on this object's material
                        .set("material.shininess", 32.0f)
                        .set("viewPos", camera.position())
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f))
                        .set("model", new Matrix4f());

                cubeVAO.bind().drawArray(DrawMode.Triangles, vbo);

                lightCubeProgram.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .set("lightColor", lightColor)
                        .setMatrix4("view", view.get(mat4f))
                        .set("model", new Matrix4f().translate(lightPos).scale(0.2f));
                lightCubeVAO.bind().drawArray(DrawMode.Triangles, vbo);

                window.swapBuffers().pollEvents();
            }
        }
    }

}
