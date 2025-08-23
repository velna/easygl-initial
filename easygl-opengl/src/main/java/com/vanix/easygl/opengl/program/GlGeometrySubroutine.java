package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.GeometrySubroutine;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlGeometrySubroutine extends BaseResource<GeometrySubroutine> implements GeometrySubroutine {
    public GlGeometrySubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.GeometrySubroutine, index);
    }

}
