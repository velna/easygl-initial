package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniformBlockInterface extends BaseInterface<ProgramResource.UniformBlock> implements ProgramInterface.UniformBlock {
    public GlUniformBlockInterface(Program program) {
        super(program, GlProgramInterfaceType.UniformBlock);
    }

    @Override
    protected ProgramResource.UniformBlock newResource(Program program, int index) {
        return new GlUniformBlock(program, index);
    }
}
