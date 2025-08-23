package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessEvaluationSubroutine extends BaseResource<ProgramResource.TessEvaluationSubroutine> implements ProgramResource.TessEvaluationSubroutine {
    public GlTessEvaluationSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutine, index);
    }

}
