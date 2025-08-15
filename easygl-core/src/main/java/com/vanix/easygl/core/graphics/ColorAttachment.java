package com.vanix.easygl.core.graphics;


import com.vanix.easygl.commons.util.LazyList;
import com.vanix.easygl.core.meta.MetaSystem;

import java.util.ArrayList;
import java.util.List;

public final class ColorAttachment implements DrawBuffer.MultiSelectable, Attachment {
    public static final ColorAttachment CA0 = of(0);
    public static final ColorAttachment CA1 = of(1);
    public static final ColorAttachment CA2 = of(2);
    public static final ColorAttachment CA3 = of(3);
    public static final ColorAttachment CA4 = of(4);
    public static final ColorAttachment CA5 = of(5);
    public static final ColorAttachment CA6 = of(6);
    public static final ColorAttachment CA7 = of(7);
    public static final int MAX = MetaSystem.Graphics.queryInt("GET.GL_MAX_COLOR_ATTACHMENTS");
    private static final List<ColorAttachment> cache = LazyList.lazyList(new ArrayList<>(), ColorAttachment::new);
    private final int value;

    private ColorAttachment(int index) {
        this.value = MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT" + index);
    }

    @Override
    public int value() {
        return value;
    }

    public static ColorAttachment of(int i) {
        if (i >= MAX || i < 0) {
            throw new IllegalArgumentException("Color attachment out of range: " + i);
        }
        return cache.get(i);
    }
}
