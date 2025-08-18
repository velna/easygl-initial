package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.*;

@Support(since = Version.GL30)
public interface VertexArray extends Bindable<BindTarget.Default<VertexArray>, VertexArray>, Handle {
    BindTarget.Default<VertexArray> Target = new BindTarget.Default<>("VertexArray", MetaHolder.VertexArray);

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
        return MetaHolder.VertexArray.create();
    }

    static HandleArray<VertexArray> of(int n) {
        return MetaHolder.VertexArray.createArray(n);
    }
}
