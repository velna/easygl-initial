package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessEvaluationSubroutineInterface extends BaseInterface<ProgramResource.TessEvaluationSubroutine> implements ProgramInterface.TessEvaluationSubroutine {
    public GlTessEvaluationSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.TessEvaluationSubroutine);
    }

    @Override
    protected ProgramResource.TessEvaluationSubroutine newResource(Program program, int index) {
        return new GlTessEvaluationSubroutine(program, index);
    }
}
