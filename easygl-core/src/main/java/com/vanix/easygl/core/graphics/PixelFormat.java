package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

public enum PixelFormat {
    @Support(since = Version.GL44)
    STENCIL_INDEX("STENCIL_INDEX"),
    DEPTH_COMPONENT("DEPTH_COMPONENT", InternalPixelFormat.Base.DEPTH_COMPONENT),
    GL_DEPTH_STENCIL("DEPTH_STENCIL", InternalPixelFormat.Base.DEPTH_STENCIL),
    RED("RED", InternalPixelFormat.Base.RED),
    RG("RG", InternalPixelFormat.Base.RG),
    GREEN("GREEN"),
    BLUE("BLUE"),
    ALPHA("ALPHA"),
    RGB("RGB", InternalPixelFormat.Base.RGB),
    RGBA("RGBA", InternalPixelFormat.Base.RGBA),
    BGR("BGR", InternalPixelFormat.Base.RGB),
    BGRA("BGRA", InternalPixelFormat.Base.RGBA);

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
