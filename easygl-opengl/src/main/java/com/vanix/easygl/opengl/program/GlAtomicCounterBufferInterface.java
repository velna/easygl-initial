package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.AtomicCounterBuffer;
import com.vanix.easygl.core.graphics.program.AtomicCounterBufferInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlAtomicCounterBufferInterface extends BaseInterface<AtomicCounterBuffer> implements AtomicCounterBufferInterface {
    public GlAtomicCounterBufferInterface(Program program) {
        super(program, GlProgramInterfaceType.AtomicCounterBuffer);
    }

    @Override
    protected AtomicCounterBuffer newResource(int index) {
        return new GlAtomicCounterBuffer(program, index);
    }
}
