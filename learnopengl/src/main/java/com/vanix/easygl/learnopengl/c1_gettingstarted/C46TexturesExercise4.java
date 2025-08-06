package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.window.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHints;
import com.vanix.easygl.core.window.event.KeyboardEvent;

public class C46TexturesExercise4 {

    static float mixValue = 0.2f;

    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL");
             var graphics = Graphics.of(window);
             var program = Program.of("p1");
             var vao = VertexArray.of();
             var vbo = Buffer.ofArray(vao, DataType.Float);
             var ebo = Buffer.ofElementArray(vao, DataType.UnsignedInt);
             var texture1 = Texture.of2D("t1");
             var texture2 = Texture.of2D("t2")) {
            window.bind().inputCtlr().keyboard().onKey()
                    .subscribe(C46TexturesExercise4::processInput);

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
                                                        
                            uniform float mixValue;
                                                        
                            // texture samplers
                            uniform sampler2D texture1;
                            uniform sampler2D texture2;
                                                        
                            void main()
                            {
                            	// linearly interpolate between both textures
                            	FragColor = mix(texture(texture1, TexCoord), texture(texture2, TexCoord), mixValue);
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

            program.bind()
                    .set("texture1", 0)
                    .set("texture2", 1);

            while (!window.shouldClose()) {
                graphics.clearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(Graphics.BufferMask.Color);

                Texture.Unit.U0.bind();
                texture1.bind();
                Texture.Unit.U1.bind();
                texture2.bind();

                program.bind().set("mixValue", mixValue);
                vao.drawElements(DrawMode.Triangles, vbo, ebo, 0);

                window.swapBuffers().pollEvents();
            }
        }
    }

    static void processInput(KeyboardEvent event) {
        if (event.key()== Keyboard.FunctionKey.ESCAPE) {
            event.source().window().shouldClose(true);
        }
        if (event.source().isKeyPressed(Keyboard.FunctionKey.UP)) {
            mixValue = Math.min(mixValue + 0.001f, 1.0f);
        }
        if (event.source().isKeyPressed(Keyboard.FunctionKey.DOWN)) {
            mixValue = Math.max(mixValue - 0.001f, 0.0f);
        }
    }
}
