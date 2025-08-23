package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.GeometrySubroutineUniform;
import com.vanix.easygl.core.graphics.program.GeometrySubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlGeometrySubroutineUniformInterface extends BaseInterface<GeometrySubroutineUniform> implements GeometrySubroutineUniformInterface {
    public GlGeometrySubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected GeometrySubroutineUniform newResource(Program program, int index) {
        return new GlGeometrySubroutineUniform(program, index);
    }
}
