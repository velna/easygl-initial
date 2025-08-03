package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.commons.util.TypeReference;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

public interface Program extends Bindable<BindTarget.Default<Program>, Program>, Identified<String> {

    BindableMeta<BindTarget.Default<Program>, Program> Meta = MetaSystem.Graphics.of(Program.class, new TypeReference<>() {
    });

    Program attach(Shader shader);

    default Program attach(Shader.Type type, String shaderSource) {
        try (var shader = Shader.of(id() + "-" + type.name(), type).source(shaderSource).compile()) {
            return attach(shader);
        }
    }

    default Program attach(Shader.Type type, InputStream inputStream) throws IOException {
        try (var shader = Shader.of(id() + "-" + type.name(), type).source(inputStream).compile()) {
            return attach(shader);
        }
    }

    Program detach(Shader shader);

    Program link() throws GraphicsException;

    @Override
    Program bind();

    Program set(String key, boolean value) throws GraphicsException;

    Program set(String key, int value) throws GraphicsException;

    Program set(String key, float value) throws GraphicsException;

    Program set(String key, float v1, float v2, float v3, float v4) throws GraphicsException;

    Program set(String key, float[] value) throws GraphicsException;

    Program set(String key, FloatBuffer buffer) throws GraphicsException;

    Program set(String key, Texture.Unit unit, Texture<?> texture) throws GraphicsException;

    static Program of(String id) {
        return Meta.create(id);
    }

}
