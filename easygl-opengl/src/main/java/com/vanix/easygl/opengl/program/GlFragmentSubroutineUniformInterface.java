package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutineUniformInterface extends BaseInterface<ProgramResource.FragmentSubroutineUniform> implements ProgramInterface.FragmentSubroutineUniform {
    public GlFragmentSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.FragmentSubroutineUniform);
    }

    @Override
    protected ProgramResource.FragmentSubroutineUniform newResource(Program program, int index) {
        return new GlFragmentSubroutineUniform(program, index);
    }
}
