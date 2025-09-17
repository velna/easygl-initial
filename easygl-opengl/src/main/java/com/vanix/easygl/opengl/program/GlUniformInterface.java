package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramResource.PropertyKey;
import com.vanix.easygl.core.graphics.program.Uniform;
import com.vanix.easygl.core.graphics.program.UniformInterface;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

public class GlUniformInterface extends BaseInterface<Uniform> implements UniformInterface {
    private static final PropertyKey[] PRELOAD_KEYS = new PropertyKey[]{PropertyKey.Type, PropertyKey.ArraySize, PropertyKey.Location};

    public GlUniformInterface(Program program) {
        super(program, GlProgramInterfaceType.Uniform);
    }

    @Override
    protected Uniform newResource(int index) {
        return new Gl43Uniform(program, index).preLoad(PRELOAD_KEYS);
    }
}
