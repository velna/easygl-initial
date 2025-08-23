package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlVertexSubroutineUniformInterface extends BaseInterface<ProgramResource.VertexSubroutineUniform> implements ProgramInterface.VertexSubroutineUniform {
    public GlVertexSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected ProgramResource.VertexSubroutineUniform newResource(Program program, int index) {
        return new GlVertexSubroutineUniform(program, index);
    }
}
