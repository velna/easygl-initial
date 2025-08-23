package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlGeometrySubroutineInterface extends BaseInterface<ProgramResource.GeometrySubroutine> implements ProgramInterface.GeometrySubroutine {
    public GlGeometrySubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.GeometrySubroutine);
    }

    @Override
    protected ProgramResource.GeometrySubroutine newResource(Program program, int index) {
        return new GlGeometrySubroutine(program, index);
    }
}
