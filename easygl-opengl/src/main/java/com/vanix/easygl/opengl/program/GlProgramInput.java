package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlProgramInput extends BaseResource<ProgramResource.ProgramInput> implements ProgramResource.ProgramInput {
    public GlProgramInput(Program program, int index) {
        super(program, GlProgramInterfaceType.ProgramInput, index);
    }

}
