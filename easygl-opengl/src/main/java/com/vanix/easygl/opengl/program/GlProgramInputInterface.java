package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlProgramInputInterface extends BaseInterface<ProgramResource.ProgramInput> implements ProgramInterface.ProgramInput {
    public GlProgramInputInterface(Program program) {
        super(program, GlProgramInterfaceType.ProgramInput);
    }

    @Override
    protected ProgramResource.ProgramInput newResource(Program program, int index) {
        return new GlProgramInput(program, index);
    }
}
