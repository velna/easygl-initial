package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TessControlSubroutine;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessControlSubroutine extends BaseResource<TessControlSubroutine> implements TessControlSubroutine {
    public GlTessControlSubroutine(Program program, int index) {
        super(program, GlProgramInterfaceType.TessControlSubroutine, index);
    }

}
