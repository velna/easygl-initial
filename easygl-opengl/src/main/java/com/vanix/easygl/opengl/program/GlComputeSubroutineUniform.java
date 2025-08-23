package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlComputeSubroutineUniform extends BaseResource<ProgramResource.ComputeSubroutineUniform> implements ProgramResource.ComputeSubroutineUniform {
    public GlComputeSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.ComputeSubroutineUniform, index);
    }

}
