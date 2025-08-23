package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TransformFeedbackBuffer;
import com.vanix.easygl.core.graphics.program.TransformFeedbackBufferInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTransformFeedbackBufferInterface extends BaseInterface<TransformFeedbackBuffer> implements TransformFeedbackBufferInterface {
    public GlTransformFeedbackBufferInterface(Program program) {
        super(program, GlProgramInterfaceType.TransformFeedbackBuffer);
    }

    @Override
    protected TransformFeedbackBuffer newResource(Program program, int index) {
        return new GlTransformFeedbackBuffer(program, index);
    }
}
