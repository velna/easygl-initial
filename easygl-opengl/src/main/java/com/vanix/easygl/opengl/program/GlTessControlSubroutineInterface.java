package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessControlSubroutineInterface extends BaseInterface<ProgramResource.TessControlSubroutine> implements ProgramInterface.TessControlSubroutine {
    public GlTessControlSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.TessControlSubroutine);
    }

    @Override
    protected ProgramResource.TessControlSubroutine newResource(Program program, int index) {
        return new GlTessControlSubroutine(program, index);
    }
}
