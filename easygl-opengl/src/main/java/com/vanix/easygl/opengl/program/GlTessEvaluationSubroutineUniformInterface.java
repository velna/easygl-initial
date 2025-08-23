package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TessEvaluationSubroutineUniform;
import com.vanix.easygl.core.graphics.program.TessEvaluationSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessEvaluationSubroutineUniformInterface extends BaseInterface<TessEvaluationSubroutineUniform> implements TessEvaluationSubroutineUniformInterface {
    public GlTessEvaluationSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutineUniform);
    }

    @Override
    protected TessEvaluationSubroutineUniform newResource(Program program, int index) {
        return new GlTessEvaluationSubroutineUniform(program, index);
    }
}
