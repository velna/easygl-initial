package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutineInterface extends BaseInterface<ProgramResource.FragmentSubroutine> implements ProgramInterface.FragmentSubroutine {
    public GlFragmentSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.FragmentSubroutine);
    }

    @Override
    protected ProgramResource.FragmentSubroutine newResource(Program program, int index) {
        return new GlFragmentSubroutine(program, index);
    }
}
