package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.HandleArray;

import java.nio.ByteBuffer;
import java.util.List;

public abstract class ShaderArray extends HandleArray<Shader> {
    public ShaderArray(List<Shader> list) {
        super(list);
    }

    public ShaderArray(Shader... shaders) {
        super(List.of(shaders));
    }

    public abstract ShaderArray binary(int format, ByteBuffer data);
}
