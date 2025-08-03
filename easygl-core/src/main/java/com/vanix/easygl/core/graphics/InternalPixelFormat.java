package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public sealed interface InternalPixelFormat permits
        InternalPixelFormat.BaseFormat,
        InternalPixelFormat.SizedFormat,
        InternalPixelFormat.CompressedFormat {

    int value();

    enum BaseFormat implements InternalPixelFormat {
        DEPTH_COMPONENT(MetaSystem.Graphics.queryInt("DEPTH_COMPONENT")),
        DEPTH_STENCIL(MetaSystem.Graphics.queryInt("DEPTH_STENCIL")),
        RED(MetaSystem.Graphics.queryInt("RED")),
        RG(MetaSystem.Graphics.queryInt("RG")),
        RGB(MetaSystem.Graphics.queryInt("RGB")),
        RGBA(MetaSystem.Graphics.queryInt("RGBA"));
        private final int value;

        BaseFormat(int value) {
            this.value = value;
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum SizedFormat implements InternalPixelFormat {
        R8(MetaSystem.Graphics.queryInt("R8"), BaseFormat.RED),
        R8_SNORM(MetaSystem.Graphics.queryInt("R8_SNORM"), BaseFormat.RED),
        R16(MetaSystem.Graphics.queryInt("R16"), BaseFormat.RED),
        R16_SNORM(MetaSystem.Graphics.queryInt("R16_SNORM"), BaseFormat.RED),
        RG8(MetaSystem.Graphics.queryInt("RG8"), BaseFormat.RG),
        RG8_SNORM(MetaSystem.Graphics.queryInt("RG8_SNORM"), BaseFormat.RG),
        RG16(MetaSystem.Graphics.queryInt("RG16"), BaseFormat.RG),
        RG16_SNORM(MetaSystem.Graphics.queryInt("RG16_SNORM"), BaseFormat.RG),
        R3_G3_B2(MetaSystem.Graphics.queryInt("R3_G3_B2"), BaseFormat.RGB),
        RGB4(MetaSystem.Graphics.queryInt("RGB4"), BaseFormat.RGB),
        RGB5(MetaSystem.Graphics.queryInt("RGB5"), BaseFormat.RGB),
        RGB8(MetaSystem.Graphics.queryInt("RGB8"), BaseFormat.RGB),
        RGB8_SNORM(MetaSystem.Graphics.queryInt("RGB8_SNORM"), BaseFormat.RGB),
        RGB10(MetaSystem.Graphics.queryInt("RGB10"), BaseFormat.RGB),
        RGB12(MetaSystem.Graphics.queryInt("RGB12"), BaseFormat.RGB),
        RGB16_SNORM(MetaSystem.Graphics.queryInt("RGB16_SNORM"), BaseFormat.RGB),
        RGBA2(MetaSystem.Graphics.queryInt("RGBA2"), BaseFormat.RGB),
        RGBA4(MetaSystem.Graphics.queryInt("RGBA4"), BaseFormat.RGB),
        RGB5_A1(MetaSystem.Graphics.queryInt("RGB5_A1"), BaseFormat.RGBA),
        RGBA8(MetaSystem.Graphics.queryInt("RGBA8"), BaseFormat.RGBA),
        RGBA8_SNORM(MetaSystem.Graphics.queryInt("RGBA8_SNORM"), BaseFormat.RGBA),
        RGB10_A2(MetaSystem.Graphics.queryInt("RGB10_A2"), BaseFormat.RGBA),
        RGB10_A2UI(MetaSystem.Graphics.queryInt("RGB10_A2UI"), BaseFormat.RGBA),
        RGBA12(MetaSystem.Graphics.queryInt("RGBA12"), BaseFormat.RGBA),
        RGBA16(MetaSystem.Graphics.queryInt("RGBA16"), BaseFormat.RGBA),
        SRGB8(MetaSystem.Graphics.queryInt("SRGB8"), BaseFormat.RGB),
        SRGB8_ALPHA8(MetaSystem.Graphics.queryInt("SRGB8_ALPHA8"), BaseFormat.RGBA),
        R16F(MetaSystem.Graphics.queryInt("R16F"), BaseFormat.RED),
        RG16F(MetaSystem.Graphics.queryInt("RG16F"), BaseFormat.RG),
        RGB16F(MetaSystem.Graphics.queryInt("RGB16F"), BaseFormat.RGB),
        RGBA16F(MetaSystem.Graphics.queryInt("RGBA16F"), BaseFormat.RGBA),
        R32F(MetaSystem.Graphics.queryInt("R32F"), BaseFormat.RED),
        RG32F(MetaSystem.Graphics.queryInt("RG32F"), BaseFormat.RG),
        RGB32F(MetaSystem.Graphics.queryInt("RGB32F"), BaseFormat.RGB),
        RGBA32F(MetaSystem.Graphics.queryInt("RGBA32F"), BaseFormat.RGBA),
        R11F_G11F_B10F(MetaSystem.Graphics.queryInt("R11F_G11F_B10F"), BaseFormat.RGB),
        RGB9_E5(MetaSystem.Graphics.queryInt("RGB9_E5"), BaseFormat.RGB),
        R8I(MetaSystem.Graphics.queryInt("R8I"), BaseFormat.RED),
        R8UI(MetaSystem.Graphics.queryInt("R8UI"), BaseFormat.RED),
        R16I(MetaSystem.Graphics.queryInt("R16I"), BaseFormat.RED),
        R16UI(MetaSystem.Graphics.queryInt("R16UI"), BaseFormat.RED),
        R32I(MetaSystem.Graphics.queryInt("R32I"), BaseFormat.RED),
        R32UI(MetaSystem.Graphics.queryInt("R32UI"), BaseFormat.RED),
        RG8I(MetaSystem.Graphics.queryInt("RG8I"), BaseFormat.RG),
        RG8UI(MetaSystem.Graphics.queryInt("RG8UI"), BaseFormat.RG),
        RG16I(MetaSystem.Graphics.queryInt("RG16I"), BaseFormat.RG),
        RG16UI(MetaSystem.Graphics.queryInt("RG16UI"), BaseFormat.RG),
        RG32I(MetaSystem.Graphics.queryInt("RG32I"), BaseFormat.RG),
        RG32UI(MetaSystem.Graphics.queryInt("RG32UI"), BaseFormat.RG),
        RGB8I(MetaSystem.Graphics.queryInt("RGB8I"), BaseFormat.RGB),
        RGB8UI(MetaSystem.Graphics.queryInt("RGB8UI"), BaseFormat.RGB),
        RGB16I(MetaSystem.Graphics.queryInt("RGB16I"), BaseFormat.RGB),
        RGB16UI(MetaSystem.Graphics.queryInt("RGB16UI"), BaseFormat.RGB),
        RGB32I(MetaSystem.Graphics.queryInt("RGB32I"), BaseFormat.RGB),
        RGB32UI(MetaSystem.Graphics.queryInt("RGB32UI"), BaseFormat.RGB),
        RGBA8I(MetaSystem.Graphics.queryInt("RGBA8I"), BaseFormat.RGBA),
        RGBA8UI(MetaSystem.Graphics.queryInt("RGBA8UI"), BaseFormat.RGBA),
        RGBA16I(MetaSystem.Graphics.queryInt("RGBA16I"), BaseFormat.RGBA),
        RGBA16UI(MetaSystem.Graphics.queryInt("RGBA16UI"), BaseFormat.RGBA),
        RGBA32I(MetaSystem.Graphics.queryInt("RGBA32I"), BaseFormat.RGBA),
        RGBA32UI(MetaSystem.Graphics.queryInt("RGBA32UI"), BaseFormat.RGBA);
        private final int value;
        private final BaseFormat baseFormat;

        SizedFormat(int value, BaseFormat baseFormat) {
            this.value = value;
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
        RED(MetaSystem.Graphics.queryInt("RED"), BaseFormat.RED),
        RG(MetaSystem.Graphics.queryInt("RG"), BaseFormat.RG),
        RGB(MetaSystem.Graphics.queryInt("RGB"), BaseFormat.RGB),
        RGBA(MetaSystem.Graphics.queryInt("RGBA"), BaseFormat.RGBA),
        SRGB(MetaSystem.Graphics.queryInt("SRGB"), BaseFormat.RGB),
        SRGB_ALPHA(MetaSystem.Graphics.queryInt("SRGB_ALPHA"), BaseFormat.RGBA),
        RED_RGTC1(MetaSystem.Graphics.queryInt("RED_RGTC1"), BaseFormat.RED),
        SIGNED_RED_RGTC1(MetaSystem.Graphics.queryInt("SIGNED_RED_RGTC1"), BaseFormat.RED),
        RG_RGTC2(MetaSystem.Graphics.queryInt("RG_RGTC2"), BaseFormat.RG),
        SIGNED_RG_RGTC2(MetaSystem.Graphics.queryInt("SIGNED_RG_RGTC2"), BaseFormat.RG),
        RGBA_BPTC_UNORM(MetaSystem.Graphics.queryInt("RGBA_BPTC_UNORM"), BaseFormat.RGBA),
        SRGB_ALPHA_BPTC_UNORM(MetaSystem.Graphics.queryInt("SRGB_ALPHA_BPTC_UNORM"), BaseFormat.RGBA),
        RGB_BPTC_SIGNED_FLOAT(MetaSystem.Graphics.queryInt("RGB_BPTC_SIGNED_FLOAT"), BaseFormat.RGB),
        RGB_BPTC_UNSIGNED_FLOAT(MetaSystem.Graphics.queryInt("RGB_BPTC_UNSIGNED_FLOAT"), BaseFormat.RGB);

        private final int value;
        private final BaseFormat baseFormat;

        CompressedFormat(int value, BaseFormat baseFormat) {
            this.value = value;
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
