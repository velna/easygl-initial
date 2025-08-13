package com.vanix.easygl.core.window;

import com.vanix.easygl.commons.attr.AttributesEnum;
import com.vanix.easygl.commons.attr.BooleanAttribute;
import com.vanix.easygl.commons.attr.UpdatableBooleanAttribute;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class WindowAttributes extends AttributesEnum<Window> {

    public final BooleanAttribute Focused = ofBoolean("FOCUSED");

    public final BooleanAttribute Iconified = ofBoolean("ICONIFIED");

    public final UpdatableBooleanAttribute<Window> Resizable = ofUpdatableBoolean("RESIZABLE");

    public final BooleanAttribute Visible = ofBoolean("VISIBLE");

    public final UpdatableBooleanAttribute<Window> Decorated = ofUpdatableBoolean("DECORATED");

    public final UpdatableBooleanAttribute<Window> AutoIconify = ofUpdatableBoolean("AUTO_ICONIFY");

    public final UpdatableBooleanAttribute<Window> Floating = ofUpdatableBoolean("FLOATING");

    public final BooleanAttribute Maximized = ofBoolean("MAXIMIZED");

    public final BooleanAttribute CenterCursor = ofBoolean("CENTER_CURSOR");

    public final BooleanAttribute TransparentFramebuffer = ofBoolean("TRANSPARENT_FRAMEBUFFER");

    public final BooleanAttribute Hovered = ofBoolean("HOVERED");

    public final UpdatableBooleanAttribute<Window> FocusOnShow = ofUpdatableBoolean("FOCUS_ON_SHOW");

    public final BooleanAttribute MousePassThrough = ofBoolean("MOUSE_PASSTHROUGH");

    public final BooleanAttribute PositionX = ofBoolean("POSITION_X");

    public final BooleanAttribute PositionY = ofBoolean("POSITION_Y");

    private BooleanAttribute ofBoolean(String id) {
        return ofBoolean(MetaSystem.Window.queryInt(id));
    }

    private UpdatableBooleanAttribute<Window> ofUpdatableBoolean(String id) {
        return ofUpdatableBoolean(MetaSystem.Window.queryInt(id));
    }

}
