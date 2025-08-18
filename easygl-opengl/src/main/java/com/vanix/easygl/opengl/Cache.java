package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.util.IntEnumCache;
import com.vanix.easygl.core.graphics.CompareFunction;
import com.vanix.easygl.core.graphics.StencilTest;

class Cache {
    static final IntEnumCache<CompareFunction> CompareFunction = new IntEnumCache<>(CompareFunction.class, 0xf);
    static final IntEnumCache<StencilTest.Op> StencilTestOp = new IntEnumCache<>(StencilTest.Op.class, 1, 0xf);
}
