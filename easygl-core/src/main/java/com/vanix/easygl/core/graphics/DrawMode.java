package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum DrawMode {
    Points(MetaSystem.Graphics.queryInt("POINTS")),
    LineStrip(MetaSystem.Graphics.queryInt("LINE_STRIP")),
    LineLoop(MetaSystem.Graphics.queryInt("LINE_LOOP")),
    Lines(MetaSystem.Graphics.queryInt("LINES")),
    LineStripAdjacency(MetaSystem.Graphics.queryInt("LINE_STRIP_ADJACENCY")),
    LinesAdjacency(MetaSystem.Graphics.queryInt("LINES_ADJACENCY")),
    TriangleStrip(MetaSystem.Graphics.queryInt("TRIANGLE_STRIP")),
    TriangleFan(MetaSystem.Graphics.queryInt("TRIANGLE_FAN")),
    Triangles(MetaSystem.Graphics.queryInt("TRIANGLES")),
    TriangleStripAdjacency(MetaSystem.Graphics.queryInt("TRIANGLE_STRIP_ADJACENCY")),
    TrianglesAdjacency(MetaSystem.Graphics.queryInt("TRIANGLES_ADJACENCY")),
    Patches(MetaSystem.Graphics.queryInt("PATCHES"));
    private final int value;

    DrawMode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
