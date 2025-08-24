package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.program.UniformBlock;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniformBlock extends BaseResource<UniformBlock> implements UniformBlock {
    public GlUniformBlock(Program program, int index) {
        super(program, GlProgramInterfaceType.UniformBlock, index);
    }

    @Override
    public UniformBlock bind(Buffer.BindingPoint bindingPoint) {
        GLX.glUniformBlockBinding(program.intHandle(), index, bindingPoint.value());
        return this;
    }

}
