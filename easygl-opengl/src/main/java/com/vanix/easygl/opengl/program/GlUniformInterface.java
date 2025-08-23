package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.Uniform;
import com.vanix.easygl.core.graphics.program.UniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniformInterface extends BaseInterface<Uniform> implements UniformInterface {
    public GlUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.Uniform);
    }

    @Override
    protected Uniform newResource(Program program, int index) {
        return new GlUniform(program, index);
    }
}
