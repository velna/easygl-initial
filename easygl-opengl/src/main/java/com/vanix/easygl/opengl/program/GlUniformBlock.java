package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniformBlock extends BaseResource<ProgramResource.UniformBlock> implements ProgramResource.UniformBlock {
    public GlUniformBlock(Program program, int index) {
        super(program, GlProgramInterfaceType.UniformBlock, index);
    }

}
