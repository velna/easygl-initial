package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.ShaderStorageBlock;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlShaderStorageBlock extends BaseResource<ShaderStorageBlock> implements ShaderStorageBlock {
    public GlShaderStorageBlock(Program program, int index) {
        super(program, GlProgramInterfaceType.ShaderStorageBlock, index);
    }

}
