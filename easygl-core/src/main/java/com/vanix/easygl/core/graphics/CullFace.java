package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class CullFace implements Feature<CullFace> {
    public enum Mode {
        Front("FRONT"),
        Back("BACK"),
        FrontAndBack("FRONT_AND_BACK");
        private final int value;

        Mode(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    public abstract CullFace mode(Mode mode);

}
