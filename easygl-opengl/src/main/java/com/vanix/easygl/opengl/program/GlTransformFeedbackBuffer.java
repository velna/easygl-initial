package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TransformFeedbackBuffer;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTransformFeedbackBuffer extends BaseResource<TransformFeedbackBuffer> implements TransformFeedbackBuffer {
    public GlTransformFeedbackBuffer(Program program, int index) {
        super(program, GlProgramInterfaceType.TransformFeedbackBuffer, index);
    }

}
