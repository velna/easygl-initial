package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlVertexSubroutineInterface extends BaseInterface<ProgramResource.VertexSubroutine> implements ProgramInterface.VertexSubroutine {
    public GlVertexSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutine);
    }

    @Override
    protected ProgramResource.VertexSubroutine newResource(Program program, int index) {
        return new GlVertexSubroutine(program, index);
    }
}
