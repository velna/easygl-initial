package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.FragmentSubroutineUniform;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutineUniform extends BaseResource<FragmentSubroutineUniform> implements FragmentSubroutineUniform {
    public GlFragmentSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.FragmentSubroutineUniform, index);
    }

}
