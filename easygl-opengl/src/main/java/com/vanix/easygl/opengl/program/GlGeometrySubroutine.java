package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlGeometrySubroutine extends BaseResource<ProgramResource.GeometrySubroutine> implements ProgramResource.GeometrySubroutine {
    public GlGeometrySubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.GeometrySubroutine, index);
    }

}
