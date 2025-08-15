package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.meta.MetaSystem;

public interface CullFace extends Feature<CullFace> {
    enum Mode {
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

    CullFace mode(Mode mode);

    CullFace frontClockwise();

    CullFace frontCounterclockwise();

    boolean isFrontClockwise();

    boolean isFrontCounterclockwise();
}
