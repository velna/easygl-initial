package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TessControlSubroutineUniform;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessControlSubroutineUniform extends BaseResource<TessControlSubroutineUniform> implements TessControlSubroutineUniform {
    public GlTessControlSubroutineUniform(Program program, int index) {
        super(program, GlProgramInterfaceType.TessControlSubroutineUniform, index);
    }

}
