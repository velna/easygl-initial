package com.vanix.easygl.core.chapter1;

import com.vanix.easygl.core.ClientApp;
import com.vanix.easygl.core.RenderContext;
import com.vanix.easygl.core.Renderer;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.input.event.KeyboardEvent;
import com.vanix.easygl.core.input.event.KeyboardListener;
import org.joml.Vector4f;

public class KeyPress implements Renderer<ClientApp, RenderContext>, KeyboardListener {
    private static final String ID = "keyPress";
    private ClientApp clientApp;
    private Buffer vbo;
    private VertexArray vao;
    private Program program;
    private PolygonMode polygonMode = PolygonMode.Fill;

    @Override
    public void init(ClientApp clientApp) throws GraphicsException {
        this.clientApp = clientApp;
        clientApp.window().inputs().keyboard().onKey(Keyboard.PrintableKey.M).subscribe(this);
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
                        #version 400 core
                        layout( location = 0 ) in vec4 vPosition;
                        void main() {
                            gl_Position = vPosition;
                        }
                        """).compile())
                .attach(Shader.fragment().source("""
                        #version 400 core
                        out vec4 fColor;
                        void main() {
                            fColor = vec4( 0.0, 0.0, 1.0, 1.0 );
                        }
                        """).compile())
                .link()
                .bind();
        vao = VertexArray.of().bind().attributes(vbo, 2f);
    }

    @Override
    public void render(RenderContext context) throws GraphicsException {
        context.graphics().defaultFrameBuffer().clearColorBuffer(FrameInnerBuffer.DrawBuffer.of(0), new Vector4f(0.0f));
        program.bind();
        vao.bind().drawArray(DrawMode.Triangles, vbo);
    }

    @Override
    public void keyboardOnKey(KeyboardEvent event) {
        if (event.isActionPress()) {
            polygonMode = polygonMode == PolygonMode.Fill ? PolygonMode.Line : PolygonMode.Fill;
            clientApp.graphics().setPolygonMode(Face.FrontAndBack, polygonMode);
        }
    }

}
