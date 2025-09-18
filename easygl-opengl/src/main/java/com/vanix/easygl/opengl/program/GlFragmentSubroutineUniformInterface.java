package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.FragmentSubroutineUniform;
import com.vanix.easygl.core.graphics.program.FragmentSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutineUniformInterface extends BaseInterface<FragmentSubroutineUniform> implements FragmentSubroutineUniformInterface {
    public GlFragmentSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.FragmentSubroutineUniform);
    }

    @Override
    protected FragmentSubroutineUniform newResource(int index) {
        return new GlFragmentSubroutineUniform(program, index);
    }
}
