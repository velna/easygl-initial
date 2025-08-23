package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlComputeSubroutineUniformInterface extends BaseInterface<ProgramResource.ComputeSubroutineUniform> implements ProgramInterface.ComputeSubroutineUniform {
    public GlComputeSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.ComputeSubroutineUniform);
    }

    @Override
    protected ProgramResource.ComputeSubroutineUniform newResource(Program program, int index) {
        return new GlComputeSubroutineUniform(program, index);
    }
}
