package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTransformFeedbackBufferInterface extends BaseInterface<ProgramResource.TransformFeedbackBuffer> implements ProgramInterface.TransformFeedbackBuffer {
    public GlTransformFeedbackBufferInterface(Program program) {
        super(program, GlProgramInterfaceType.TransformFeedbackBuffer);
    }

    @Override
    protected ProgramResource.TransformFeedbackBuffer newResource(Program program, int index) {
        return new GlTransformFeedbackBuffer(program, index);
    }
}
