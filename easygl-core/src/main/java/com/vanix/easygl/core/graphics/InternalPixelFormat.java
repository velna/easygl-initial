package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public sealed interface InternalPixelFormat permits
        InternalPixelFormat.BaseFormat,
        InternalPixelFormat.SizedFormat,
        InternalPixelFormat.CompressedFormat {

    int value();

    enum BaseFormat implements InternalPixelFormat {
        DEPTH_COMPONENT("DEPTH_COMPONENT"),
        DEPTH_STENCIL("DEPTH_STENCIL"),
        DEPTH24_STENCIL8("DEPTH24_STENCIL8"),
        RED("RED"),
        RG("RG"),
        RGB("RGB"),
        RGBA("RGBA");
        private final int value;

        BaseFormat(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum SizedFormat implements InternalPixelFormat {
        R8("R8", BaseFormat.RED),
        R8_SNORM("R8_SNORM", BaseFormat.RED),
        R16("R16", BaseFormat.RED),
        R16_SNORM("R16_SNORM", BaseFormat.RED),
        RG8("RG8", BaseFormat.RG),
        RG8_SNORM("RG8_SNORM", BaseFormat.RG),
        RG16("RG16", BaseFormat.RG),
        RG16_SNORM("RG16_SNORM", BaseFormat.RG),
        R3_G3_B2("R3_G3_B2", BaseFormat.RGB),
        RGB4("RGB4", BaseFormat.RGB),
        RGB5("RGB5", BaseFormat.RGB),
        RGB8("RGB8", BaseFormat.RGB),
        RGB8_SNORM("RGB8_SNORM", BaseFormat.RGB),
        RGB10("RGB10", BaseFormat.RGB),
        RGB12("RGB12", BaseFormat.RGB),
        RGB16_SNORM("RGB16_SNORM", BaseFormat.RGB),
        RGBA2("RGBA2", BaseFormat.RGB),
        RGBA4("RGBA4", BaseFormat.RGB),
        RGB5_A1("RGB5_A1", BaseFormat.RGBA),
        RGBA8("RGBA8", BaseFormat.RGBA),
        RGBA8_SNORM("RGBA8_SNORM", BaseFormat.RGBA),
        RGB10_A2("RGB10_A2", BaseFormat.RGBA),
        RGB10_A2UI("RGB10_A2UI", BaseFormat.RGBA),
        RGBA12("RGBA12", BaseFormat.RGBA),
        RGBA16("RGBA16", BaseFormat.RGBA),
        SRGB8("SRGB8", BaseFormat.RGB),
        SRGB8_ALPHA8("SRGB8_ALPHA8", BaseFormat.RGBA),
        R16F("R16F", BaseFormat.RED),
        RG16F("RG16F", BaseFormat.RG),
        RGB16F("RGB16F", BaseFormat.RGB),
        RGBA16F("RGBA16F", BaseFormat.RGBA),
        R32F("R32F", BaseFormat.RED),
        RG32F("RG32F", BaseFormat.RG),
        RGB32F("RGB32F", BaseFormat.RGB),
        RGBA32F("RGBA32F", BaseFormat.RGBA),
        R11F_G11F_B10F("R11F_G11F_B10F", BaseFormat.RGB),
        RGB9_E5("RGB9_E5", BaseFormat.RGB),
        R8I("R8I", BaseFormat.RED),
        R8UI("R8UI", BaseFormat.RED),
        R16I("R16I", BaseFormat.RED),
        R16UI("R16UI", BaseFormat.RED),
        R32I("R32I", BaseFormat.RED),
        R32UI("R32UI", BaseFormat.RED),
        RG8I("RG8I", BaseFormat.RG),
        RG8UI("RG8UI", BaseFormat.RG),
        RG16I("RG16I", BaseFormat.RG),
        RG16UI("RG16UI", BaseFormat.RG),
        RG32I("RG32I", BaseFormat.RG),
        RG32UI("RG32UI", BaseFormat.RG),
        RGB8I("RGB8I", BaseFormat.RGB),
        RGB8UI("RGB8UI", BaseFormat.RGB),
        RGB16I("RGB16I", BaseFormat.RGB),
        RGB16UI("RGB16UI", BaseFormat.RGB),
        RGB32I("RGB32I", BaseFormat.RGB),
        RGB32UI("RGB32UI", BaseFormat.RGB),
        RGBA8I("RGBA8I", BaseFormat.RGBA),
        RGBA8UI("RGBA8UI", BaseFormat.RGBA),
        RGBA16I("RGBA16I", BaseFormat.RGBA),
        RGBA16UI("RGBA16UI", BaseFormat.RGBA),
        RGBA32I("RGBA32I", BaseFormat.RGBA),
        RGBA32UI("RGBA32UI", BaseFormat.RGBA);
        private final int value;
        private final BaseFormat baseFormat;

        SizedFormat(String id, BaseFormat baseFormat) {
            this.value = MetaSystem.Graphics.queryInt(id);
            this.baseFormat = baseFormat;
        }

        @Override
        public int value() {
            return value;
        }

        public BaseFormat baseFormat() {
            return baseFormat;
        }
    }

    enum CompressedFormat implements InternalPixelFormat {
        RED("RED", BaseFormat.RED),
        RG("RG", BaseFormat.RG),
        RGB("RGB", BaseFormat.RGB),
        RGBA("RGBA", BaseFormat.RGBA),
        SRGB("SRGB", BaseFormat.RGB),
        SRGB_ALPHA("SRGB_ALPHA", BaseFormat.RGBA),
        RED_RGTC1("RED_RGTC1", BaseFormat.RED),
        SIGNED_RED_RGTC1("SIGNED_RED_RGTC1", BaseFormat.RED),
        RG_RGTC2("RG_RGTC2", BaseFormat.RG),
        SIGNED_RG_RGTC2("SIGNED_RG_RGTC2", BaseFormat.RG),
        RGBA_BPTC_UNORM("RGBA_BPTC_UNORM", BaseFormat.RGBA),
        SRGB_ALPHA_BPTC_UNORM("SRGB_ALPHA_BPTC_UNORM", BaseFormat.RGBA),
        RGB_BPTC_SIGNED_FLOAT("RGB_BPTC_SIGNED_FLOAT", BaseFormat.RGB),
        RGB_BPTC_UNSIGNED_FLOAT("RGB_BPTC_UNSIGNED_FLOAT", BaseFormat.RGB);

        private final int value;
        private final BaseFormat baseFormat;

        CompressedFormat(String id, BaseFormat baseFormat) {
            this.value = MetaSystem.Graphics.queryInt(id);
            this.baseFormat = baseFormat;
        }

        @Override
        public int value() {
            return value;
        }

        public BaseFormat baseFormat() {
            return baseFormat;
        }
    }

}
