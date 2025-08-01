package com.vanix.easygl.opengl;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.Program;

public class GlProgram extends AbstractProgram<Program> implements Program {
    protected static final BindTarget.Default<Program> Target = new BindTarget.Default<>("Program", GlMetaService.ProgramMeta);

    protected GlProgram(Object... args) {
        this(GLX.glCreateProgram(), args);
    }

    protected GlProgram(int handle, Object... args) {
        super(handle, (String) args[0], Target);
    }

}
