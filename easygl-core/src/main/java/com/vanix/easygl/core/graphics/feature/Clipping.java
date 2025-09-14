package com.vanix.easygl.core.graphics.feature;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.IndexedFeature;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.graphics.Origin;
import com.vanix.easygl.core.graphics.Version;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Clipping extends IndexedFeature<Clipping, Graphics>, GraphicsFeature<Clipping> {
    int MaxClipDistances = MetaSystem.Graphics.queryInt("GET.MAX_CLIP_DISTANCES");

    @Support(since = Version.GL45)
    Clipping control(Origin origin, DepthMode depthMode);

    enum DepthMode implements IntEnum {
        NegativeOneToOne("NEGATIVE_ONE_TO_ONE"),
        ZeroToOne("ZERO_TO_ONE");
        private final int value;

        DepthMode(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
