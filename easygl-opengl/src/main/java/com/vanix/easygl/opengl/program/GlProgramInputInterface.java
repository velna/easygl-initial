package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.ProgramInput;
import com.vanix.easygl.core.graphics.program.ProgramInputInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlProgramInputInterface extends BaseInterface<ProgramInput> implements ProgramInputInterface {
    public GlProgramInputInterface(Program program) {
        super(program, GlProgramInterfaceType.ProgramInput);
    }

    @Override
    protected ProgramInput newResource(Program program, int index) {
        return new GlProgramInput(program, index);
    }
}
