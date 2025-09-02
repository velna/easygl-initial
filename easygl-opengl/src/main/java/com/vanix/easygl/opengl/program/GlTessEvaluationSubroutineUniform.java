package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TessEvaluationSubroutineUniform;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessEvaluationSubroutineUniform extends BaseResource<TessEvaluationSubroutineUniform> implements TessEvaluationSubroutineUniform {
    public GlTessEvaluationSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutineUniform, index);
    }

}
