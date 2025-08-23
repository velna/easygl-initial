package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.GeometrySubroutineUniform;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlGeometrySubroutineUniform extends BaseResource<GeometrySubroutineUniform> implements GeometrySubroutineUniform {
    public GlGeometrySubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.GeometrySubroutineUniform, index);
    }

}
