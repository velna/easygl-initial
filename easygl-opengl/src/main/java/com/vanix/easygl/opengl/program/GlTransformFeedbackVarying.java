package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TransformFeedbackVarying;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTransformFeedbackVarying extends BaseResource<TransformFeedbackVarying> implements TransformFeedbackVarying {
    public GlTransformFeedbackVarying(Program program, int index) {
        super(program, GlProgramInterfaceType.TransformFeedbackVarying, index);
    }

}
