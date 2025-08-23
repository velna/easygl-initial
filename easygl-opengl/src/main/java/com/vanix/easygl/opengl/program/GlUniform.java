package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniform extends BaseResource<ProgramResource.Uniform> implements ProgramResource.Uniform {
    public GlUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.Uniform, index);
    }

}
