package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.ComputeSubroutineUniform;
import com.vanix.easygl.core.graphics.program.ComputeSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlComputeSubroutineUniformInterface extends BaseInterface<ComputeSubroutineUniform> implements ComputeSubroutineUniformInterface {
    public GlComputeSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.ComputeSubroutineUniform);
    }

    @Override
    protected ComputeSubroutineUniform newResource(Program program, int index) {
        return new GlComputeSubroutineUniform(program, index);
    }
}
