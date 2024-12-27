package com.vanix.easygl.core.chapter1;

import com.vanix.easygl.core.ClientApp;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.graphics.event.KeyboardListener;
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
        clientApp.window().inputCtlr().keyboard().onKey(Keyboard.KEY_M).subscribe(this);
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
                        #version 400 core
                        layout( location = 0 ) in vec4 vPosition;
                        void main() {
                            gl_Position = vPosition;
                        }
                        """).compile())
                .attach(Shader.fragment(ID).source("""
                        #version 400 core
                        out vec4 fColor;
                        void main() {
                            fColor = vec4( 0.0, 0.0, 1.0, 1.0 );
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
        vao.bind().draw(DrawMode.Triangles, vbo);
    }

    @Override
    public void keyboardOnKey(Keyboard keyboard, int key, int scancode, int action, int modifiers) {
        if (action == Keyboard.KEY_ACT_PRESS) {
            polygonMode = polygonMode == PolygonMode.Fill ? PolygonMode.Line : PolygonMode.Fill;
            clientApp.graphics().polygonMode(PolygonFace.FrontAndBack, polygonMode);
        }
    }

}
