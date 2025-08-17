package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.meta.MetaSystem;

public interface StencilTest extends Feature<StencilTest, Graphics> {
    StencilTest setFunction(CompareFunction function, int ref, int mask);

    StencilTest setFunctionSeparate(Face face, CompareFunction function, int ref, int mask);

    CompareFunction getCompareFunction(Face face);

    int getRef(Face face);

    int getMask(Face face);

    StencilTest setOps(Op stencilTestFailOp, Op depthTestFailOp, Op passOp);

    StencilTest setOpsSeparate(Face face, Op stencilTestFailOp, Op depthTestFailOp, Op passOp);

    Op getOpAt(ActionPoint actionPoint);

    enum ActionPoint implements IntEnum {
        StencilFail("STENCIL_FAIL"),
        StencilPassDepthPass("STENCIL_PASS_DEPTH_PASS"),
        StencilPassDepthFail("STENCIL_PASS_DEPTH_FAIL"),
        StencilBackFail("STENCIL_BACK_FAIL"),
        StencilBackPassDepthPass("STENCIL_BACK_PASS_DEPTH_PASS"),
        StencilBackPassDepthFail("STENCIL_BACK_PASS_DEPTH_FAIL");

        private final int value;

        ActionPoint(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum Op implements IntEnum {
        ZERO("ZERO"),
        KEEP("KEEP"),
        REPLACE("REPLACE"),
        INCR("INCR"),
        DECR("DECR"),
        INCR_WRAP("INCR_WRAP"),
        DECR_WRAP("DECR_WRAP"),
        INVERT("INVERT");
        private final int value;

        Op(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
