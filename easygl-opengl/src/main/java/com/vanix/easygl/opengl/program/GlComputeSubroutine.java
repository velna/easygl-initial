package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.ComputeSubroutine;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlComputeSubroutine extends BaseResource<ComputeSubroutine> implements ComputeSubroutine {
    public GlComputeSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.ComputeSubroutine, index);
    }

}
