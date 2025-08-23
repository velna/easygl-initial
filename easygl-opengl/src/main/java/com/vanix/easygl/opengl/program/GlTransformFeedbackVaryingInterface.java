package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TransformFeedbackVarying;
import com.vanix.easygl.core.graphics.program.TransformFeedbackVaryingInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTransformFeedbackVaryingInterface extends BaseInterface<TransformFeedbackVarying> implements TransformFeedbackVaryingInterface {
    public GlTransformFeedbackVaryingInterface(Program program) {
        super(program, GlProgramInterfaceType.TransformFeedbackVarying);
    }

    @Override
    protected TransformFeedbackVarying newResource(Program program, int index) {
        return new GlTransformFeedbackVarying(program, index);
    }
}
