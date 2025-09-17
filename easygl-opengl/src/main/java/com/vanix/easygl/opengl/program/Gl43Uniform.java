package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.Uniform;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class Gl43Uniform extends BaseResource<Uniform> implements GlUniform {
    public Gl43Uniform(Program program, int index) {
        super(program, GlProgramInterfaceType.Uniform, index);
    }

}
