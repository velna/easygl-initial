package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessEvaluationSubroutineUniformInterface extends BaseInterface<ProgramResource.TessEvaluationSubroutineUniform> implements ProgramInterface.TessEvaluationSubroutineUniform {
    public GlTessEvaluationSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutineUniform);
    }

    @Override
    protected ProgramResource.TessEvaluationSubroutineUniform newResource(Program program, int index) {
        return new GlTessEvaluationSubroutineUniform(program, index);
    }
}
