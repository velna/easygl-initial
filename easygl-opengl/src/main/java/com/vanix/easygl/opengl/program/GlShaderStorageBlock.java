package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlShaderStorageBlock extends BaseResource<ProgramResource.ShaderStorageBlock> implements ProgramResource.ShaderStorageBlock {
    public GlShaderStorageBlock(Program program, int index) {
        super(program, GlProgramInterfaceType.ShaderStorageBlock, index);
    }

}
