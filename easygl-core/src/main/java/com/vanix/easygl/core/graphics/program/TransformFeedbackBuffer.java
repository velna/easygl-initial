package com.vanix.easygl.core.graphics.program;

import com.vanix.easygl.core.graphics.ProgramResource;

public interface TransformFeedbackBuffer extends
        ProgramResource.BufferBinding<TransformFeedbackBuffer>,
        ProgramResource.NumActiveVariables<TransformFeedbackBuffer>,
        ProgramResource.ActiveVariables<TransformFeedbackBuffer>,
        ProgramResource.TransformFeedbackBufferIndex<TransformFeedbackBuffer>,
        ProgramResource.TransformFeedbackBufferStride<TransformFeedbackBuffer> {

}
