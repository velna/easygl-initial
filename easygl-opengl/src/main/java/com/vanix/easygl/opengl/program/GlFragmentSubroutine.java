package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlFragmentSubroutine extends BaseResource<ProgramResource.FragmentSubroutine> implements ProgramResource.FragmentSubroutine {
    public GlFragmentSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.FragmentSubroutine, index);
    }

}
