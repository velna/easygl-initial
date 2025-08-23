package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlVertexSubroutine extends BaseResource<ProgramResource.VertexSubroutine> implements ProgramResource.VertexSubroutine {
    public GlVertexSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.VertexSubroutine, index);
    }

}
