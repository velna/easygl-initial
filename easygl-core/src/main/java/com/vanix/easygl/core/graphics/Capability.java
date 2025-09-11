package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

public enum Capability implements IntEnum {
    RasterizerDiscard("RASTERIZER_DISCARD"),
    Multisample("MULTISAMPLE"),
    SampleShading("SAMPLE_SHADING"),
    SampleMask("SAMPLE_MASK"),
    SampleCoverage("SAMPLE_COVERAGE"),
    SampleAlphaToCoverage("SAMPLE_ALPHA_TO_COVERAGE"),
    SampleAlphaToOne("SAMPLE_ALPHA_TO_ONE"),
    ProgramPointSize("PROGRAM_POINT_SIZE"),
    LineSmooth("LINE_SMOOTH"),
    PolygonSmooth("POLYGON_SMOOTH"),
    CullFace("CULL_FACE"),
    DepthClamp("DEPTH_CLAMP"),
    Dither("DITHER"),
    @Support(since = Version.GL43)
    PrimitiveRestartFixedIndex("PRIMITIVE_RESTART_FIXED_INDEX"),
    FramebufferSRGB("FRAMEBUFFER_SRGB"),
    PolygonOffsetPoint("POLYGON_OFFSET_POINT"),
    PolygonOffsetLine("POLYGON_OFFSET_LINE"),
    PolygonOffsetFill("POLYGON_OFFSET_FILL"),
    @Support(since = Version.GL32)
    TextureCubeMapSeamless("TEXTURE_CUBE_MAP_SEAMLESS");
    private final int value;

    Capability(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
