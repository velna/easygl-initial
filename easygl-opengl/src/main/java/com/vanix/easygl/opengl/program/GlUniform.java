package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.Uniform;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniform extends BaseResource<Uniform> implements Uniform {
    public GlUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.Uniform, index);
    }

}
