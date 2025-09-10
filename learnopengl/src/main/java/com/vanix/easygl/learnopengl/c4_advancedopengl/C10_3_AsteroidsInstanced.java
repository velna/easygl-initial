package com.vanix.easygl.learnopengl.c4_advancedopengl;

import com.vanix.easygl.commons.Random;
import com.vanix.easygl.core.g3d.ControllableCamera;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.media.Mesh;
import com.vanix.easygl.core.media.Model;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import lombok.extern.slf4j.Slf4j;
import org.joml.Math;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.List;

@Slf4j
public class C10_3_AsteroidsInstanced {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenglForwardCompat.enable();
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var asteroidProgram = Program.of();
             var planetProgram = Program.of();
             var buffer = Buffer.of(DataType.Float);
             var rock = Model.of("objects/rock/rock.obj");
             var planet = Model.of("objects/planet/planet.obj")) {

            window.inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.onFpsUpdate().subscribe(event -> log.info("FPS: {}", event.getFps()));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);
            graphics.depthTest().enable();

            asteroidProgram.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/10.3.asteroids.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/10.3.asteroids.fs")
                    .link();
            planetProgram.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/10.3.planet.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/10.3.planet.fs")
                    .link();


            // generate a large list of semi-random model transformation matrices
            // ------------------------------------------------------------------
            var modelMatrices = new Matrix4f[100000];
            float radius = 50.0f;
            float offset = 2.5f;
            for (int i = 0; i < modelMatrices.length; i++) {
                var model = new Matrix4f();
                // 1. translation: displace along circle with 'radius' in range [-offset, offset]
                float angle = i * 360.f / modelMatrices.length;
                float displacement = (Random.nextInt() % (int) (2 * offset * 100)) / 100.0f - offset;
                float x = Math.sin(angle) * radius + displacement;
                displacement = (Random.nextInt() % (int) (2 * offset * 100)) / 100.0f - offset;
                float y = displacement * 0.4f; // keep height of asteroid field smaller compared to width of x and z
                displacement = (Random.nextInt() % (int) (2 * offset * 100)) / 100.0f - offset;
                float z = Math.cos(angle) * radius + displacement;
                model.translate(x, y, z);

                // 2. scale: Scale between 0.05 and 0.25f
                float scale = (float) ((Random.nextInt() % 20) / 100.0 + 0.05);
                model.scale(scale);

                // 3. rotation: add random rotation around a (semi)randomly picked rotation axis vector
                float rotAngle = Random.nextInt() % 360;
                model.rotate(rotAngle, 0.4f, 0.6f, 0.8f);

                // 4. now add to list of matrices
                modelMatrices[i] = model;
            }
            buffer.bind(Buffer.Target.Array).realloc(Buffer.DataUsage.StaticDraw, modelMatrices);

            for (var mesh : rock.getMeshes()) {
                var attr3 = mesh.getVao().bind().attribute(3);
                attr3.enablePointers(4f, 4f, 4f, 4f);
                attr3.setDivisor(1)
                        .nextAttribute().setDivisor(1)
                        .nextAttribute().setDivisor(1)
                        .nextAttribute().setDivisor(1);
            }

            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            camera.position().set(0, 0, 55);
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);
            var rockMeshes = rock.getMeshes();
            var planetMeshes = planet.getMeshes();

            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 1000.0f);

                planetProgram.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", camera.update().view().get(mat4f))
                        .setMatrix4("model", new Matrix4f()
                                .translate(0.0f, -3.0f, 0.0f)
                                .scale(4.0f, 4.0f, 4.0f).get(mat4f));
                drawModel(planetProgram, planetMeshes);

                asteroidProgram.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", camera.update().view().get(mat4f))
                        .setInt("texture_diffuse1", 0);
                TextureUnit.U0.bind();
                rock.getTextures(Model.TextureType.Height).getFirst().getTexture().bind();
                for (var mesh : rockMeshes) {
                    mesh.getVao().bind().drawElementsInstanced(DrawMode.Triangles, mesh.getEbo(), modelMatrices.length);
                }

                window.swapBuffers().pollEvents();
            }
        }
    }

    private static void drawModel(Program program, List<Mesh> meshes) {
        for (Mesh mesh : meshes) {
            if (program.containsUniform("texture_diffuse1")) {
                var textures = mesh.getTextures(Model.TextureType.Diffuse);
                if (!textures.isEmpty()) {
                    textures.getFirst().getTexture().bind(TextureUnit.U0);
                    program.setInt("texture_diffuse1", 0);
                }
            }
            mesh.draw();
        }
    }

}
