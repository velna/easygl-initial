package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTransformFeedbackVaryingInterface extends BaseInterface<ProgramResource.TransformFeedbackVarying> implements ProgramInterface.TransformFeedbackVarying {
    public GlTransformFeedbackVaryingInterface(Program program) {
        super(program, GlProgramInterfaceType.TransformFeedbackVarying);
    }

    @Override
    protected ProgramResource.TransformFeedbackVarying newResource(Program program, int index) {
        return new GlTransformFeedbackVarying(program, index);
    }
}
