package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.FragmentSubroutine;
import com.vanix.easygl.core.graphics.program.FragmentSubroutineInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutineInterface extends BaseInterface<FragmentSubroutine> implements FragmentSubroutineInterface {
    public GlFragmentSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.FragmentSubroutine);
    }

    @Override
    protected FragmentSubroutine newResource(Program program, int index) {
        return new GlFragmentSubroutine(program, index);
    }
}
