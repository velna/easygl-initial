package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.util.TypeReference;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Program extends IProgram<Program> {
    BindableMeta<BindTarget.Default<Program>, Program> Meta = MetaSystem.Graphics.of(Program.class, new TypeReference<>() {
    });

    static Program of(String id) {
        return Meta.create(id);
    }

}
