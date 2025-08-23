package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TessEvaluationSubroutine;
import com.vanix.easygl.core.graphics.program.TessEvaluationSubroutineInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessEvaluationSubroutineInterface extends BaseInterface<TessEvaluationSubroutine> implements TessEvaluationSubroutineInterface {
    public GlTessEvaluationSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutine);
    }

    @Override
    protected TessEvaluationSubroutine newResource(Program program, int index) {
        return new GlTessEvaluationSubroutine(program, index);
    }
}
