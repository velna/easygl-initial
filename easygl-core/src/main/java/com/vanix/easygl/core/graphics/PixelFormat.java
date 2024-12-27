package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;

public enum PixelFormat {

    COLOR_INDEX(GLC.GL_COLOR_INDEX),
    STENCIL_INDEX(GLC.GL_STENCIL_INDEX),
    DEPTH_COMPONENT(GLC.GL_DEPTH_COMPONENT),
    RED(GLC.GL_RED),
    GREEN(GLC.GL_GREEN),
    BLUE(GLC.GL_BLUE),
    ALPHA(GLC.GL_ALPHA),
    RGB(GLC.GL_RGB),
    RGBA(GLC.GL_RGBA),
    LUMINANCE(GLC.GL_LUMINANCE),
    LUMINANCE_ALPHA(GLC.GL_LUMINANCE_ALPHA);

    private final int value;

    private PixelFormat(int value) {
        this.value = value;
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
}
