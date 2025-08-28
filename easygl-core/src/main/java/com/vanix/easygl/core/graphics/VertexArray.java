package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.*;

@Support(since = Version.GL30)
public interface VertexArray extends Bindable<BindTarget.Default<VertexArray>, VertexArray>, Handle {
    BindTarget.Default<VertexArray> Target = new BindTarget.Default<>("VertexArray", MetaHolder.VertexArray);

    default VertexArray enableAttributes(Buffer buffer, Number... layouts) {
        buffer.bind(Buffer.Target.Array);
        return enableAttributes(layouts);
    }

    /**
     * 目前支持Byte, UnsignedByte, Short, UnsignedShort, Int, UnsignedInt, Float, HalfFloat, Double。<br>
     * 不同的类型传入对应的Java类型Byte、Short、Integer、Float、Double。<br>
     * layouts值大于0的，属性会被启动，小于0的不会被启用。<br>
     * layouts绝对值小于10的，为Unsigned类型，大于10的为Signed类型。 <br>
     * layouts绝对值为5时代表BGRA。<br>
     * normalized默认为false，BGRA为true。
     *
     * @param layouts layouts
     * @return stride size of layouts
     */
    VertexArray enableAttributes(Number... layouts);

    VertexAttribute attribute(int index);

    @Support(since = Version.GL43)
    VertexArray bind(int bindingPoint, Buffer buffer, long offset, int stride);

    void drawArray(DrawMode mode, int first, int count);

    @Deprecated
    default void drawArray(DrawMode mode, Buffer vbo) {
        drawArray(mode, vbo, 0);
    }

    @Deprecated
    void drawArray(DrawMode mode, Buffer vbo, int first);

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
