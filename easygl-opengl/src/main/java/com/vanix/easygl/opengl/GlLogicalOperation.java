package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.util.IntEnumCache;
import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.graphics.LogicalOperation;

public class GlLogicalOperation extends GlFeature<LogicalOperation> implements LogicalOperation {

    private static final IntEnumCache<Op> OpCache = new IntEnumCache<Op>(Op.class, 0xf);

    public GlLogicalOperation(Graphics graphics) {
        super(GLX.GL_COLOR_LOGIC_OP, graphics);
    }

    @Override
    public LogicalOperation setOp(Op op) {
        GLX.glLogicOp(op.value());
        return this;
    }

    @Override
    public Op getOp() {
        return OpCache.valueOf(GLX.glGetInteger(GLX.GL_LOGIC_OP_MODE));
    }
}
