package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlProgramOutput extends BaseResource<ProgramResource.ProgramOutput> implements ProgramResource.ProgramOutput {
    public GlProgramOutput(Program program, int index) {
        super(program, GlProgramInterfaceType.ProgramOutput, index);
    }

}
