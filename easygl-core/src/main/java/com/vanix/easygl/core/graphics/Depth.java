package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.MultiFeature;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class Depth implements MultiFeature<Depth.Capability, Depth> {

    public enum Capability {
        DepthTest(MetaSystem.Graphics.queryInt("DEPTH_TEST")),
        DepthClamp(MetaSystem.Graphics.queryInt("DEPTH_CLAMP"));
        private final int value;

        Capability(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public enum Functions {
        Never(MetaSystem.Graphics.queryInt("NEVER")),
        LT(MetaSystem.Graphics.queryInt("LESS")),
        EQ(MetaSystem.Graphics.queryInt("EQUAL")),
        LE(MetaSystem.Graphics.queryInt("LEQUAL")),
        GT(MetaSystem.Graphics.queryInt("GREATER")),
        NE(MetaSystem.Graphics.queryInt("NOTEQUAL")),
        GE(MetaSystem.Graphics.queryInt("GEQUAL")),
        ALWAYS(MetaSystem.Graphics.queryInt("ALWAYS"));
        private final int value;

        Functions(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public abstract Depth func(Functions function);

}
