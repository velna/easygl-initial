package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessEvaluationSubroutineUniform extends BaseResource<ProgramResource.TessEvaluationSubroutineUniform> implements ProgramResource.TessEvaluationSubroutineUniform {
    public GlTessEvaluationSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutineUniform, index);
    }

}
