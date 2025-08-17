package com.vanix.easygl.core.graphics;


import com.vanix.easygl.commons.util.LazyList;
import com.vanix.easygl.core.meta.MetaSystem;

import java.util.ArrayList;
import java.util.List;

public final class DrawBuffer implements ColorBuffer.MultiSelectable {
    public static final int MAX = MetaSystem.Graphics.queryInt("GET.GL_MAX_DRAW_BUFFERS");
    private static final List<DrawBuffer> cache = LazyList.lazyList(new ArrayList<>(), DrawBuffer::new);
    private final int value;
    private final int index;

    private DrawBuffer(int index) {
        this.index = index;
        this.value = MetaSystem.Graphics.queryInt("DRAW_BUFFER" + index);
    }

    public int index() {
        return index;
    }

    @Override
    public int value() {
        return value;
    }

    public static DrawBuffer of(int i) {
        if (i >= MAX || i < 0) {
            throw new IllegalArgumentException("Color attachment out of range: " + i);
        }
        return cache.get(i);
    }
}
