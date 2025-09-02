package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.TessControlSubroutineUniform;
import com.vanix.easygl.core.graphics.program.TessControlSubroutineUniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlTessControlSubroutineUniformInterface extends BaseInterface<TessControlSubroutineUniform> implements TessControlSubroutineUniformInterface {
    public GlTessControlSubroutineUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    protected TessControlSubroutineUniform newResource(Program program, int index) {
        return new GlTessControlSubroutineUniform(program, index);
    }
}
