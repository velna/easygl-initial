package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessControlSubroutineUniformInterface extends BaseInterface<ProgramResource.TessControlSubroutineUniform> implements ProgramInterface.TessControlSubroutineUniform {
    public GlTessControlSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected ProgramResource.TessControlSubroutineUniform newResource(Program program, int index) {
        return new GlTessControlSubroutineUniform(program, index);
    }
}
