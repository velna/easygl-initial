package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GlVertexArray;

public interface VertexArray extends Bindable<VertexArray>, Handle {

    BindingState State = new BindingState("VertexArray");

    VertexArray attributes(Buffer buffer, int... layouts);

    default void drawArray(DrawMode mode, Buffer vbo) {
        drawArray(mode, vbo, 0);
    }

    void drawArray(DrawMode mode, Buffer vbo, int first);

    default void drawElements(DrawMode mode, Buffer vbo, Buffer ebo) {
        drawElements(mode, vbo, ebo, 0);
    }

    void drawElements(DrawMode mode, Buffer vbo, Buffer ebo, int indices);

    static VertexArray of() {
        return new GlVertexArray();
    }

}
