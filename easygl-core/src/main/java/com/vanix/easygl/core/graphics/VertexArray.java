package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.*;
import com.vanix.easygl.core.meta.MetaSystem;

import javax.annotation.Nullable;

@Support(since = Version.GL30)
public interface VertexArray extends Bindable<BindTarget.Default<VertexArray>, VertexArray>, Handle {
    int MAX_BINDING_POINTS = MetaSystem.Graphics.queryInt("GET.MAX_VERTEX_ATTRIB_BINDINGS");
    int MAX_ATTRIBUTES = MetaSystem.Graphics.queryInt("GET.MAX_VERTEX_ATTRIBS");

    BindTarget.Default<VertexArray> Target = new BindTarget.Default<>("VertexArray", MetaHolder.VertexArray);

    default VertexAttribute enableAttributePointers(Number... layouts) {
        return attribute(0).enablePointers(layouts);
    }

    default VertexAttribute enableAttributeFormats(VertexArray.BindingPoint bindingPoint, Number... layouts) {
        return attribute(0).enableFormats(bindingPoint, layouts);
    }

    VertexAttribute attribute(int index);

    BindingPoint bidingPoint(int bindingIndex);

    default void drawArray(DrawMode mode, int count) {
        drawArray(mode, 0, count);
    }

    void drawArray(DrawMode mode, int first, int count);

    void drawArrayInstanced(DrawMode mode, int first, int count, int instanceCount);

    default void drawElements(DrawMode mode, Buffer ebo) {
        drawElements(mode, ebo, 0);
    }

    void drawElements(DrawMode mode, Buffer ebo, int indices);

    default void drawElementsInstanced(DrawMode mode, Buffer ebo, int instanceCount) {
        drawElementsInstanced(mode, ebo, 0, instanceCount);
    }

    void drawElementsInstanced(DrawMode mode, Buffer ebo, int indices, int instanceCount);

    static VertexArray of() {
        return MetaHolder.VertexArray.create();
    }

    static HandleArray<VertexArray> of(int n) {
        return MetaHolder.VertexArray.createArray(n);
    }

    @Support(since = Version.GL43)
    interface BindingPoint extends IntEnum {

        BindingPoint bind(Buffer buffer, int offset, int stride);

        BindingPoint setDivisor(int divisor);

        default int countOfStride() {
            var buffer = getBuffer();
            return buffer == null ? 0 : (int) (getBuffer().size() / getStride());
        }

        int getDivisor();

        int getOffset();

        int getStride();

        @Nullable
        Buffer getBuffer();

    }
}
