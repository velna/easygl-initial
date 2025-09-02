package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.VertexSubroutineUniform;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlVertexSubroutineUniform extends BaseResource<VertexSubroutineUniform> implements VertexSubroutineUniform {
    public GlVertexSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform, index);
    }

}
