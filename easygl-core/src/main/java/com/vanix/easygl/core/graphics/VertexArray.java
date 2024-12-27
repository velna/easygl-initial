package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GlVertexArray;

public interface VertexArray extends Bindable<VertexArray>, Handle {

    BindingState State = new BindingState("VertexArray");

    VertexArray attributes(Buffer buffer, int... layouts);

    void draw(DrawMode mode, Buffer vbo);

    void draw(DrawMode mode, Buffer vbo, Buffer ebo);

    static VertexArray of() {
        return new GlVertexArray();
    }

}
