package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;

public enum DrawMode {
    Points(GLC.GL_POINTS),
    LineStrip(GLC.GL_LINE_STRIP),
    LineLoop(GLC.GL_LINE_LOOP),
    Lines(GLC.GL_LINES),
    LineStripAdjacency(GLC.GL_LINE_STRIP_ADJACENCY),
    LinesAdjacency(GLC.GL_LINES_ADJACENCY),
    TriangleStrip(GLC.GL_TRIANGLE_STRIP),
    TriangleFan(GLC.GL_TRIANGLE_FAN),
    Triangles(GLC.GL_TRIANGLES),
    TriangleStripAdjacency(GLC.GL_TRIANGLE_STRIP_ADJACENCY),
    TrianglesAdjacency(GLC.GL_TRIANGLES_ADJACENCY),
    Patches(GLC.GL_PATCHES);
    private final int value;

    DrawMode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
