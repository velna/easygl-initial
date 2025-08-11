package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class CullFace implements Feature<CullFace> {
    public enum Mode {
        Front(MetaSystem.Graphics.queryInt("FRONT")),
        Back(MetaSystem.Graphics.queryInt("BACK")),
        FrontAndBack(MetaSystem.Graphics.queryInt("FRONT_AND_BACK"));
        private final int value;

        Mode(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public abstract CullFace mode(Mode mode);

}
