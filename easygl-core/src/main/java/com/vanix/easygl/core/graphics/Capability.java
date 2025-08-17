package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum Capability implements IntEnum {
    RasterizerDiscard("RASTERIZER_DISCARD"),
    Multisample("MULTISAMPLE"),
    SampleShading("SAMPLE_SHADING"),
    ProgramPointSize("PROGRAM_POINT_SIZE"),
    LineSmooth("LINE_SMOOTH"),
    PolygonSmooth("POLYGON_SMOOTH"),
    CullFace("CULL_FACE"),
    PolygonOffsetPoint("POLYGON_OFFSET_POINT"),
    PolygonOffsetLine("POLYGON_OFFSET_LINE"),
    PolygonOffsetFill("POLYGON_OFFSET_FILL");
    private final int value;

    Capability(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
