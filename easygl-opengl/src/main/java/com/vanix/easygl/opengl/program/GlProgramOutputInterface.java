package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlProgramOutputInterface extends BaseInterface<ProgramResource.ProgramOutput> implements ProgramInterface.ProgramOutput {
    public GlProgramOutputInterface(Program program) {
        super(program, GlProgramInterfaceType.ProgramOutput);
    }

    @Override
    protected ProgramResource.ProgramOutput newResource(Program program, int index) {
        return new GlProgramOutput(program, index);
    }
}
