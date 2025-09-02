package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.UniformBlock;
import com.vanix.easygl.core.graphics.program.UniformBlockInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniformBlockInterface extends BaseInterface<UniformBlock> implements UniformBlockInterface {
    public GlUniformBlockInterface(Program program) {
        super(program, GlProgramInterfaceType.UniformBlock);
    }

    @Override
    protected UniformBlock newResource(Program program, int index) {
        return new GlUniformBlock(program, index);
    }
}
