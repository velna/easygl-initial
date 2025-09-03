package com.vanix.easygl.learnopengl.c3_modelloading;

import com.vanix.easygl.core.g3d.ControllableCamera;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.Mouse;
import com.vanix.easygl.core.media.Model;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import org.joml.Math;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public class ModelLoading {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenglForwardCompat.enable();
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of();
             var model = Model.of("objects/backpack/backpack.obj")) {

            window.inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE).subscribe(event -> window.shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);
            graphics.depthTest().enable();

            program.attachResource(Shader.Type.Vertex, "shaders/3_model_loading/1.model_loading.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/3_model_loading/1.model_loading.fs")
                    .link();

            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);
            var meshes = model.getMeshes();

            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);
                var view = camera.update().view();

                program.bind()
                        .setMatrix4("projection", projection.get(mat4f))
                        .setMatrix4("view", view.get(mat4f))
                        .setMatrix4("model", new Matrix4f()
                                .translate(0.0f, 0.0f, 0.0f)
                                .scale(1.0f, 1.0f, 1.0f)
                                .get(mat4f));

                for (com.vanix.easygl.core.media.Mesh mesh : meshes) {
                    var textures = mesh.getTextures(Model.TextureType.Diffuse);
                    if (!textures.isEmpty()) {
                        mesh.getTextures(Model.TextureType.Diffuse).getFirst().getTexture().bind(Texture.Target.T2D, TextureUnit.U0);
                        program.setInt("texture_diffuse1", 0);
                    }
                    mesh.draw();
                }

                window.swapBuffers().pollEvents();
            }
        }
    }


}
