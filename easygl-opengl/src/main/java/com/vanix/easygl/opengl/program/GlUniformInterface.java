package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniformInterface extends BaseInterface<ProgramResource.Uniform> implements ProgramInterface.Uniform {
    public GlUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.Uniform);
    }

    @Override
    protected ProgramResource.Uniform newResource(Program program, int index) {
        return new GlUniform(program, index);
    }
}
