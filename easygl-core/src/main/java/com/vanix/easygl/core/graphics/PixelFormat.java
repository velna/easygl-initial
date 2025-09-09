package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

public enum PixelFormat {
    @Support(since = Version.GL44)
    STENCIL_INDEX("STENCIL_INDEX"),
    DEPTH_COMPONENT("DEPTH_COMPONENT", InternalPixelFormat.Base.DEPTH_COMPONENT),
    GL_DEPTH_STENCIL("DEPTH_STENCIL", InternalPixelFormat.Base.DEPTH_STENCIL),
    RED("RED", InternalPixelFormat.Base.RED),
    GREEN("GREEN"),
    BLUE("BLUE"),
    ALPHA("ALPHA"),
    RG("RG", InternalPixelFormat.Base.RG),
    RGB("RGB", InternalPixelFormat.Base.RGB),
    RGBA("RGBA", InternalPixelFormat.Base.RGBA),
    BGR("BGR", InternalPixelFormat.Base.RGB),
    BGRA("BGRA", InternalPixelFormat.Base.RGBA),
    RED_INTEGER("RED_INTEGER", InternalPixelFormat.Base.RED),
    GREEN_INTEGER("GREEN_INTEGER"),
    BLUE_INTEGER("BLUE_INTEGER"),
    RG_INTEGER("RG_INTEGER", InternalPixelFormat.Base.RG),
    RGB_INTEGER("RGB_INTEGER", InternalPixelFormat.Base.RGB),
    RGBA_INTEGER("RGBA_INTEGER", InternalPixelFormat.Base.RGBA),
    BGR_INTEGER("BGR_INTEGER", InternalPixelFormat.Base.RGB),
    BGRA_INTEGER("BGRA_INTEGER", InternalPixelFormat.Base.RGBA);

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
