package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTransformFeedbackBuffer extends BaseResource<ProgramResource.TransformFeedbackBuffer> implements ProgramResource.TransformFeedbackBuffer {
    public GlTransformFeedbackBuffer(Program program, int index) {
        super(program, GlProgramInterfaceType.TransformFeedbackBuffer, index);
    }

}
