package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.VertexSubroutine;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlVertexSubroutine extends BaseResource<VertexSubroutine> implements VertexSubroutine {
    public GlVertexSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.VertexSubroutine, index);
    }

}
