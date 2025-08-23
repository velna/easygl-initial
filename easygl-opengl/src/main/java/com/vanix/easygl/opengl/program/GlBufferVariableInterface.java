package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlBufferVariableInterface extends BaseInterface<ProgramResource.BufferVariable> implements ProgramInterface.BufferVariable {
    public GlBufferVariableInterface(Program program) {
        super(program, GlProgramInterfaceType.BufferVariable);
    }

    @Override
    protected ProgramResource.BufferVariable newResource(Program program, int index) {
        return new GlBufferVariable(program, index);
    }
}
