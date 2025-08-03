package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum PixelFormat {

    COLOR_INDEX(MetaSystem.Graphics.queryInt("COLOR_INDEX")),
    STENCIL_INDEX(MetaSystem.Graphics.queryInt("STENCIL_INDEX")),
    DEPTH_COMPONENT(MetaSystem.Graphics.queryInt("DEPTH_COMPONENT"), InternalPixelFormat.BaseFormat.DEPTH_COMPONENT),
    RED(MetaSystem.Graphics.queryInt("RED"), InternalPixelFormat.BaseFormat.RED),
    GREEN(MetaSystem.Graphics.queryInt("GREEN")),
    BLUE(MetaSystem.Graphics.queryInt("BLUE")),
    ALPHA(MetaSystem.Graphics.queryInt("ALPHA")),
    RGB(MetaSystem.Graphics.queryInt("RGB"), InternalPixelFormat.BaseFormat.RGB),
    RGBA(MetaSystem.Graphics.queryInt("RGBA"), InternalPixelFormat.BaseFormat.RGBA),
    LUMINANCE(MetaSystem.Graphics.queryInt("LUMINANCE")),
    LUMINANCE_ALPHA(MetaSystem.Graphics.queryInt("LUMINANCE_ALPHA"));

    private final int value;
    private final InternalPixelFormat internalPixelFormat;

    private PixelFormat(int value) {
        this(value, null);
    }

    private PixelFormat(int value, InternalPixelFormat internalPixelFormat) {
        this.value = value;
        this.internalPixelFormat = internalPixelFormat;
    }

    public int value() {
        return value;
    }

    public static PixelFormat ofChannels(int n) {
        return switch (n) {
            case 1 -> LUMINANCE;
            case 2 -> LUMINANCE_ALPHA;
            case 3 -> RGB;
            case 4 -> RGBA;
            default -> throw new IllegalArgumentException("Invalid channel number: " + n);
        };
    }

    public InternalPixelFormat internalPixelFormat() {
        return internalPixelFormat;
    }
}
