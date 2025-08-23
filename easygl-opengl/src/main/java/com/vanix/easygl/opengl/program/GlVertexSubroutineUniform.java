package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlVertexSubroutineUniform extends BaseResource<ProgramResource.VertexSubroutineUniform> implements ProgramResource.VertexSubroutineUniform {
    public GlVertexSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform, index);
    }

}
