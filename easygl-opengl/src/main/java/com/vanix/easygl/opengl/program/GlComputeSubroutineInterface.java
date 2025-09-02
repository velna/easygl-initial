package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.ComputeSubroutine;
import com.vanix.easygl.core.graphics.program.ComputeSubroutineInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlComputeSubroutineInterface extends BaseInterface<ComputeSubroutine> implements ComputeSubroutineInterface {
    public GlComputeSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.ComputeSubroutine);
    }

    @Override
    protected ComputeSubroutine newResource(Program program, int index) {
        return new GlComputeSubroutine(program, index);
    }
}
