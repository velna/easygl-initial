package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.ShaderStorageBlock;
import com.vanix.easygl.core.graphics.program.ShaderStorageBlockInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlShaderStorageBlockInterface extends BaseInterface<ShaderStorageBlock> implements ShaderStorageBlockInterface {
    public GlShaderStorageBlockInterface(Program program) {
        super(program, GlProgramInterfaceType.ShaderStorageBlock);
    }

    @Override
    protected ShaderStorageBlock newResource(Program program, int index) {
        return new GlShaderStorageBlock(program, index);
    }
}
