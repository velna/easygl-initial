package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.GeometrySubroutine;
import com.vanix.easygl.core.graphics.program.GeometrySubroutineInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlGeometrySubroutineInterface extends BaseInterface<GeometrySubroutine> implements GeometrySubroutineInterface {
    public GlGeometrySubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.GeometrySubroutine);
    }

    @Override
    protected GeometrySubroutine newResource(Program program, int index) {
        return new GlGeometrySubroutine(program, index);
    }
}
