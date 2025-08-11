package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.MultiFeature;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class Depth implements MultiFeature<Depth.Capability, Depth> {

    public enum Capability {
        DepthTest("DEPTH_TEST"),
        DepthClamp("DEPTH_CLAMP");
        private final int value;

        Capability(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    public enum Functions {
        Never("NEVER"),
        LT("LESS"),
        EQ("EQUAL"),
        LE("LEQUAL"),
        GT("GREATER"),
        NE("NOTEQUAL"),
        GE("GEQUAL"),
        ALWAYS("ALWAYS");
        private final int value;

        Functions(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    public abstract Depth func(Functions function);

}
