package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.VertexSubroutineUniform;
import com.vanix.easygl.core.graphics.program.VertexSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlVertexSubroutineUniformInterface extends BaseInterface<VertexSubroutineUniform> implements VertexSubroutineUniformInterface {
    public GlVertexSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected VertexSubroutineUniform newResource(Program program, int index) {
        return new GlVertexSubroutineUniform(program, index);
    }
}
