package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessControlSubroutineUniform extends BaseResource<ProgramResource.TessControlSubroutineUniform> implements ProgramResource.TessControlSubroutineUniform {
    public GlTessControlSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.TessControlSubroutineUniform, index);
    }

}
