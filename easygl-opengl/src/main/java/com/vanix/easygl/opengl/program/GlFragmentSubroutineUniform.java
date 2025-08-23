package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutineUniform extends BaseResource<ProgramResource.FragmentSubroutineUniform> implements ProgramResource.FragmentSubroutineUniform {
    public GlFragmentSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.FragmentSubroutineUniform, index);
    }

}
