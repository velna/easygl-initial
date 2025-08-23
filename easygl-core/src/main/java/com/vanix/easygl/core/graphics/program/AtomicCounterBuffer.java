package com.vanix.easygl.core.graphics.program;

import com.vanix.easygl.core.graphics.ProgramResource;

public interface AtomicCounterBuffer extends
        ProgramResource.BufferBinding<AtomicCounterBuffer>,
        ProgramResource.BufferDataSize<AtomicCounterBuffer>,
        ProgramResource.NumActiveVariables<AtomicCounterBuffer>,
        ProgramResource.ActiveVariables<AtomicCounterBuffer> {

}
