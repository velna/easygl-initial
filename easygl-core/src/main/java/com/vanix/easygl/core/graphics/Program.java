package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.commons.util.TypeReference;

public interface Program extends IProgram<Program> {
    BindableMeta<BindTarget.Default<Program>, Program> Meta = MetaSystem.Graphics.of(Program.class, new TypeReference<>() {
    });

    static Program of(String id) {
        return Meta.create(id);
    }

    static <E extends Enum<E>> UniformProgram<E> uniform(String id, Class<E> enumClass) {
        return MetaSystem.Graphics.of(UniformProgram.class,
                        new TypeReference<BindableMeta<BindTarget.Default<UniformProgram<E>>, UniformProgram<E>>>() {
                        })
                .create(id, enumClass);
    }
}
