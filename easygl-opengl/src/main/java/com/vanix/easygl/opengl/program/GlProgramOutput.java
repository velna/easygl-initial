package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.ProgramOutput;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlProgramOutput extends BaseResource<ProgramOutput> implements ProgramOutput {
    public GlProgramOutput(Program program, int index) {
        super(program, GlProgramInterfaceType.ProgramOutput, index);
    }

}
