package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TessEvaluationSubroutine;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessEvaluationSubroutine extends BaseResource<TessEvaluationSubroutine> implements TessEvaluationSubroutine {
    public GlTessEvaluationSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutine, index);
    }

}
