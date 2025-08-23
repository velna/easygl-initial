package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlAtomicCounterBufferInterface extends BaseInterface<ProgramResource.AtomicCounterBuffer> implements ProgramInterface.AtomicCounterBuffer {
    public GlAtomicCounterBufferInterface(Program program) {
        super(program, GlProgramInterfaceType.AtomicCounterBuffer);
    }

    @Override
    protected ProgramResource.AtomicCounterBuffer newResource(Program program, int index) {
        return new GlAtomicCounterBuffer(program, index);
    }
}
