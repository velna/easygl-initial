package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.AtomicCounterBuffer;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlAtomicCounterBuffer extends BaseResource<AtomicCounterBuffer> implements AtomicCounterBuffer {
    public GlAtomicCounterBuffer(Program program, int index) {
        super(program, GlProgramInterfaceType.AtomicCounterBuffer, index);
    }

}
