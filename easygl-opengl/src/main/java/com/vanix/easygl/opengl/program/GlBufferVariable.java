package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.BufferVariable;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlBufferVariable extends BaseResource<BufferVariable> implements BufferVariable {
    public GlBufferVariable(Program program, int index) {
        super(program, GlProgramInterfaceType.BufferVariable, index);
    }

}
