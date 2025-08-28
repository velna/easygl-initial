package com.vanix.easygl.core.chapter1;

import com.vanix.easygl.core.ClientApp;
import com.vanix.easygl.core.RenderContext;
import com.vanix.easygl.core.Renderer;
import com.vanix.easygl.core.graphics.*;
import org.joml.Vector4f;

public class Triangles implements Renderer<ClientApp, RenderContext> {
    private static final String ID = "triangle";
    private Buffer vbo;
    private VertexArray vao;
    private Program program;

    @Override
    public void init(ClientApp clientApp) throws GraphicsException {
        vbo = Buffer.of(DataType.Float)
                .bind(Buffer.Target.Array)
                .realloc(Buffer.DataUsage.StaticDraw, new float[]{
                        // Triangle 1
                        -0.90f, -0.90f,
                        0.85f, -0.90f,
                        -0.90f, 0.85f,
                        // Triangle 2
                        0.90f, -0.85f,
                        0.90f, 0.90f,
                        -0.85f, 0.90f
                });
        program = Program.of()
                .attach(Shader.vertex().source("""
                        #version 410 core
                        layout (location = 0) in vec4 vPosition;
                        void main(){
                            gl_Position = vPosition;
                        }
                        """).compile())
                .attach(Shader.fragment().source("""
                        #version 410 core
                        layout (location = 0) out vec4 fColor;
                        void main(){
                            fColor = vec4(0.5, 0.4, 0.8, 1.0);
                        }
                        """).compile())
                .link()
                .bind();
        vao = VertexArray.of().bind().enableAttributes(vbo, 2f);
    }

    @Override
    public void render(RenderContext context) throws GraphicsException {
        context.graphics().defaultFrameBuffer().clearColorBuffer(FrameInnerBuffer.DrawBuffer.of(0), new Vector4f(0.0f));
        program.bind();
        vao.bind().drawArray(DrawMode.Triangles, vbo);
    }

}
