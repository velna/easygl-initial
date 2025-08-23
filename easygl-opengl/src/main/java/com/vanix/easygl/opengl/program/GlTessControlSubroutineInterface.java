package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TessControlSubroutine;
import com.vanix.easygl.core.graphics.program.TessControlSubroutineInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessControlSubroutineInterface extends BaseInterface<TessControlSubroutine> implements TessControlSubroutineInterface {
    public GlTessControlSubroutineInterface(Program program) {
        super(program, GlProgramInterfaceType.TessControlSubroutine);
    }

    @Override
    protected TessControlSubroutine newResource(Program program, int index) {
        return new GlTessControlSubroutine(program, index);
    }
}
