package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.ProgramOutput;
import com.vanix.easygl.core.graphics.program.ProgramOutputInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlProgramOutputInterface extends BaseInterface<ProgramOutput> implements ProgramOutputInterface {
    public GlProgramOutputInterface(Program program) {
        super(program, GlProgramInterfaceType.ProgramOutput);
    }

    @Override
    protected ProgramOutput newResource(int index) {
        return new GlProgramOutput(program, index);
    }
}
