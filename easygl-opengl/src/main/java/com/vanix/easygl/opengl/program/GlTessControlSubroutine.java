package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessControlSubroutine extends BaseResource<ProgramResource.TessControlSubroutine> implements ProgramResource.TessControlSubroutine {
    public GlTessControlSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.TessControlSubroutine, index);
    }

}
