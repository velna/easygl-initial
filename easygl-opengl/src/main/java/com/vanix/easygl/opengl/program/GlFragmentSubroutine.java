package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.FragmentSubroutine;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutine extends BaseResource<FragmentSubroutine> implements FragmentSubroutine {
    public GlFragmentSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.FragmentSubroutine, index);
    }

}
