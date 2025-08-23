package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlShaderStorageBlockInterface extends BaseInterface<ProgramResource.ShaderStorageBlock> implements ProgramInterface.ShaderStorageBlock {
    public GlShaderStorageBlockInterface(Program program) {
        super(program, GlProgramInterfaceType.ShaderStorageBlock);
    }

    @Override
    protected ProgramResource.ShaderStorageBlock newResource(Program program, int index) {
        return new GlShaderStorageBlock(program, index);
    }
}
