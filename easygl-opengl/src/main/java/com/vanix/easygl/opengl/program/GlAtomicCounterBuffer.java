package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlAtomicCounterBuffer extends BaseResource<ProgramResource.AtomicCounterBuffer> implements ProgramResource.AtomicCounterBuffer {
    public GlAtomicCounterBuffer(Program program, int index) {
        super(program, GlProgramInterfaceType.AtomicCounterBuffer, index);
    }

}
