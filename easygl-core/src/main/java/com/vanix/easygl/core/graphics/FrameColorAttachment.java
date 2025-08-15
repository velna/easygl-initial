package com.vanix.easygl.core.graphics;


import com.vanix.easygl.commons.util.LazyList;
import com.vanix.easygl.core.meta.MetaSystem;

import java.util.ArrayList;
import java.util.List;

public final class FrameColorAttachment implements FrameColorBuffer.MultiSelectable, FrameAttachment, FrameAttachment.Renderable {
    public static final FrameColorAttachment CA0 = of(0);
    public static final FrameColorAttachment CA1 = of(1);
    public static final FrameColorAttachment CA2 = of(2);
    public static final FrameColorAttachment CA3 = of(3);
    public static final FrameColorAttachment CA4 = of(4);
    public static final FrameColorAttachment CA5 = of(5);
    public static final FrameColorAttachment CA6 = of(6);
    public static final FrameColorAttachment CA7 = of(7);
    public static final int MAX = MetaSystem.Graphics.queryInt("GET.MAX_COLOR_ATTACHMENTS");
    private static final List<FrameColorAttachment> cache = LazyList.lazyList(new ArrayList<>(), FrameColorAttachment::new);
    private final int value;

    private FrameColorAttachment(int index) {
        this.value = MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT" + index);
    }

    @Override
    public int value() {
        return value;
    }

    public static FrameColorAttachment of(int i) {
        if (i >= MAX || i < 0) {
            throw new IllegalArgumentException("Color attachment out of range: " + i);
        }
        return cache.get(i);
    }
}
