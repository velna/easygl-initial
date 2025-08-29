package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.*;

@Support(since = Version.GL30)
public interface VertexArray extends Bindable<BindTarget.Default<VertexArray>, VertexArray>, Handle {
    BindTarget.Default<VertexArray> Target = new BindTarget.Default<>("VertexArray", MetaHolder.VertexArray);

    default VertexAttribute enableAttributes(Number... layouts) {
        return attribute(0).enableAttributes(layouts);
    }

    VertexAttribute attribute(int index);

    @Support(since = Version.GL43)
    VertexArray bind(int bindingPoint, Buffer buffer, long offset, int stride);

    default void drawArray(DrawMode mode, int count) {
        drawArray(mode, 0, count);
    }

    void drawArray(DrawMode mode, int first, int count);

    void drawArrayInstanced(DrawMode mode, int first, int count, int instanceCount);

    default void drawElements(DrawMode mode, Buffer ebo) {
        drawElements(mode, ebo, 0);
    }

    void drawElements(DrawMode mode, Buffer ebo, int indices);

    static VertexArray of() {
        return MetaHolder.VertexArray.create();
    }

    static HandleArray<VertexArray> of(int n) {
        return MetaHolder.VertexArray.createArray(n);
    }
}
