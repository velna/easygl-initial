package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.meta.MetaSystem;

public interface DepthTest extends Feature<DepthTest> {

    enum Function {
        Never("NEVER"),
        LT("LESS"),
        EQ("EQUAL"),
        LE("LEQUAL"),
        GT("GREATER"),
        NE("NOTEQUAL"),
        GE("GEQUAL"),
        ALWAYS("ALWAYS");
        private final int value;

        Function(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    DepthTest function(Function function);

    DepthTest range(double near, double far);

    DepthTest range(float near, float far);

    float[] rangeFloat();

    double[] rangeDouble();

    int[] rangeIntMapped();
}
