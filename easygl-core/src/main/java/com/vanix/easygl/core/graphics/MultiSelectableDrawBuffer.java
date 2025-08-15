package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

final class MultiSelectableDrawBuffer extends SimpleIntEnum implements DrawBuffer.MultiSelectable {
    public MultiSelectableDrawBuffer(String id) {
        super(MetaSystem.Graphics.queryInt(id));
    }
}
