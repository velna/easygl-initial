package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;
import com.vanix.easygl.core.graphics.gl.GlVertexArray;

public interface VertexArray extends Bindable<BindTarget.Default<VertexArray>, VertexArray>, Handle {

    BindTarget.Default<VertexArray> Target = new BindTarget.Default<>(
            BindingState.<BindTarget.Default<VertexArray>, VertexArray>ofInt("VertexArray", GLC::glBindVertexArray));

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
