package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlComputeSubroutineInterface extends BaseInterface<ProgramResource.ComputeSubroutine> implements ProgramInterface.ComputeSubroutine {
    public GlComputeSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.ComputeSubroutine);
    }

    @Override
    protected ProgramResource.ComputeSubroutine newResource(Program program, int index) {
        return new GlComputeSubroutine(program, index);
    }
}
