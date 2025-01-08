package com.vanix.easygl.core.chapter1;

import com.vanix.easygl.core.ClientApp;
import com.vanix.easygl.core.graphics.*;
import org.joml.Vector4f;

public class Triangles implements Renderer<ClientApp, RenderContext> {
    private static final String ID = "triangle";
    private Buffer vbo;
    private VertexArray vao;
    private Program program;

    @Override
    public void init(ClientApp clientApp) throws GraphicsException {
        vbo = Buffer.of(Buffer.Type.Array, DataType.Float)
                .bind()
                .realloc(Buffer.DataUsage.STATIC_DRAW, new float[]{
                        // Triangle 1
                        -0.90f, -0.90f,
                        0.85f, -0.90f,
                        -0.90f, 0.85f,
                        // Triangle 2
                        0.90f, -0.85f,
                        0.90f, 0.90f,
                        -0.85f, 0.90f
                });
        program = Program.of(ID)
                .attach(Shader.vertex(ID).source("""
                        #version 410 core
                        layout (location = 0) in vec4 vPosition;
                        void main(){
                            gl_Position = vPosition;
                        }
                        """).compile())
                .attach(Shader.fragment(ID).source("""
                        #version 410 core
                        layout (location = 0) out vec4 fColor;
                        void main(){
                            fColor = vec4(0.5, 0.4, 0.8, 1.0);
                        }
                        """).compile())
                .link()
                .bind();
        vao = VertexArray.of().bind().attributes(vbo, 2);
    }

    @Override
    public void render(RenderContext context) throws GraphicsException {
        FrameBuffer.defaultBuffer().clearColor(DrawBufferIndex.Index0, new Vector4f(0.0f));
        program.bind();
        vao.bind().drawArray(DrawMode.Triangles, vbo);
    }

}
