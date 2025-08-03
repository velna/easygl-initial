package com.vanix.easygl.core.graphics;


import com.vanix.easygl.commons.util.TypeReference;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.FloatBuffer;

public interface UniformProgram extends IProgram<UniformProgram> {

    @Override
    UniformProgram bind();

    UniformProgram set(String key, boolean value) throws GraphicsException;

    UniformProgram set(String key, int value) throws GraphicsException;

    UniformProgram set(String key, float value) throws GraphicsException;

    UniformProgram set(String key, float v1, float v2, float v3, float v4) throws GraphicsException;

    UniformProgram set(String key, float[] value) throws GraphicsException;

    UniformProgram set(String key, FloatBuffer buffer) throws GraphicsException;

    UniformProgram set(String key, Texture.Unit unit, Texture<?> texture) throws GraphicsException;

    static UniformProgram of(String id) {
        return MetaSystem.Graphics.of(UniformProgram.class,
                        new TypeReference<BindableMeta<BindTarget.Default<UniformProgram>, UniformProgram>>() {
                        })
                .create(id);
    }
}
