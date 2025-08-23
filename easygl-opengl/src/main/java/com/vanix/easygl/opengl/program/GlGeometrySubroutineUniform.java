package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlGeometrySubroutineUniform extends BaseResource<ProgramResource.GeometrySubroutineUniform> implements ProgramResource.GeometrySubroutineUniform {
    public GlGeometrySubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.GeometrySubroutineUniform, index);
    }

}
