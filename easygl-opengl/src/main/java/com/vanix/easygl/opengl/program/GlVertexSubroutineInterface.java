package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.VertexSubroutine;
import com.vanix.easygl.core.graphics.program.VertexSubroutineInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlVertexSubroutineInterface extends BaseInterface<VertexSubroutine> implements VertexSubroutineInterface {
    public GlVertexSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutine);
    }

    @Override
    protected VertexSubroutine newResource(Program program, int index) {
        return new GlVertexSubroutine(program, index);
    }
}
