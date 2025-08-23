package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlBufferVariable extends BaseResource<ProgramResource.BufferVariable> implements ProgramResource.BufferVariable {
    public GlBufferVariable(Program program, int index) {
        super(program, GlProgramInterfaceType.BufferVariable, index);
    }

}
