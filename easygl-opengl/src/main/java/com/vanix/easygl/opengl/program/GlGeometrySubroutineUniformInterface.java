package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlGeometrySubroutineUniformInterface extends BaseInterface<ProgramResource.GeometrySubroutineUniform> implements ProgramInterface.GeometrySubroutineUniform {
    public GlGeometrySubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected ProgramResource.GeometrySubroutineUniform newResource(Program program, int index) {
        return new GlGeometrySubroutineUniform(program, index);
    }
}
