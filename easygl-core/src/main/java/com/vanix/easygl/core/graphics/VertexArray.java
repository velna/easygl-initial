package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.CloseableArray;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;

public interface VertexArray extends Bindable<BindTarget.Default<VertexArray>, VertexArray>, Handle {
    BindableMeta<BindTarget.Default<VertexArray>, VertexArray> Meta = MetaSystem.Graphics.of(VertexArray.class);
    BindTarget.Default<VertexArray> Target = new BindTarget.Default<>("VertexArray", Meta);

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
        return Meta.create();
    }

    static CloseableArray<VertexArray> of(int n) {
        return Meta.createArray(n);
    }
}
