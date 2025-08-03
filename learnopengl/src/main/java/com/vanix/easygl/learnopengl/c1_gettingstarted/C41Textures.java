package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.window.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import com.vanix.easygl.glfw.GlWindow;
import com.vanix.easygl.opengl.GlGraphics;

public class C41Textures {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = new GlGraphics();
             var program = Program.of("p1");
             var vao = VertexArray.of();
             var vbo = Buffer.ofArray(vao, DataType.Float);
             var ebo = Buffer.ofElementArray(vao, DataType.UnsignedInt);
             var texture = Texture.of2D("t1")) {
            window.bind().inputCtlr().keyboard().onKey(Keyboard.KEY_ESCAPE)
                    .subscribe((keyboard, key, scancode, action, modifiers) -> keyboard.getWindow().shouldClose(true));
            graphics.viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());

            program.attach(Shader.Type.Vertex, """
                            #version 330 core
                            layout (location = 0) in vec3 aPos;
                            layout (location = 1) in vec3 aColor;
                            layout (location = 2) in vec2 aTexCoord;

                            out vec3 ourColor;
                            out vec2 TexCoord;

                            void main()
                            {
                            	gl_Position = vec4(aPos, 1.0);
                            	ourColor = aColor;
                            	TexCoord = vec2(aTexCoord.x, aTexCoord.y);
                            }
                            """)
                    .attach(Shader.Type.Fragment, """
                            #version 330 core
                             out vec4 FragColor;
                             
                             in vec3 ourColor;
                             in vec2 TexCoord;
                             
                             // texture sampler
                             uniform sampler2D texture1;
                             
                             void main()
                             {
                             	FragColor = texture(texture1, TexCoord);
                             }
                            """)
                    .link();

            vao.bind().attributes(vbo.bind()
                    .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                            // positions          // colors           // texture coords
                            0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, // top right
                            0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, // bottom right
                            -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, // bottom left
                            -0.5f, 0.5f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f  // top left
                    }), 3, 3, 2);
            ebo.bind().realloc(Buffer.DataUsage.STATIC_DRAW, new int[]{
                    0, 1, 3, // first triangle
                    1, 2, 3  // second triangle
            });

            texture.bind()
                    .wrapS(Texture.Wrap.Repeat)
                    .wrapT(Texture.Wrap.Repeat)
                    .minFilter(Texture.MinFilter.LinearMipmapLinear)
                    .magFilter(Texture.MagFilter.Linear);
            try (var image = Image.load("textures/container.jpg")) {
                texture.load(image).generateMipmap();
            }

            while (!window.shouldClose()) {
                graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(Graphics.BufferMask.Color);

                texture.bind();
                program.bind();
                vao.drawElements(DrawMode.Triangles, vbo, ebo, 0);

                window.swapBuffers().pollEvents();
            }
        } finally {
            GlWindow.systemTerminate();
        }
    }

    private static void processInput(Keyboard keyboard, int key, int scancode, int action, int modifiers) {
        keyboard.getWindow().shouldClose(true);
    }
}
