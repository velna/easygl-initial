package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum PixelFormat {

    STENCIL_INDEX("STENCIL_INDEX"),
    DEPTH_COMPONENT("DEPTH_COMPONENT", InternalPixelFormat.BaseFormat.DEPTH_COMPONENT),
    RED("RED", InternalPixelFormat.BaseFormat.RED),
    GREEN("GREEN"),
    BLUE("BLUE"),
    ALPHA("ALPHA"),
    RGB("RGB", InternalPixelFormat.BaseFormat.RGB),
    RGBA("RGBA", InternalPixelFormat.BaseFormat.RGBA),
    BGR("BGR", InternalPixelFormat.BaseFormat.RGB),
    BGRA("BGRA", InternalPixelFormat.BaseFormat.RGBA);

    private final int value;
    private final InternalPixelFormat internalPixelFormat;

    PixelFormat(String id) {
        this(id, null);
    }

    PixelFormat(String id, InternalPixelFormat internalPixelFormat) {
        this.value = MetaSystem.Graphics.queryInt(id);
        this.internalPixelFormat = internalPixelFormat;
    }

    public int value() {
        return value;
    }

    public static PixelFormat ofChannels(int n) {
        return switch (n) {
            case 1 -> RED;
            case 3 -> RGB;
            case 4 -> RGBA;
            default -> throw new IllegalArgumentException("Invalid channel number: " + n);
        };
    }

    public InternalPixelFormat internalPixelFormat() {
        return internalPixelFormat;
    }
}
