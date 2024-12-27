package com.vanix.easygl.core.module;

import com.vanix.easygl.core.ClientApp;
import com.vanix.easygl.core.graphics.RenderContext;
import com.vanix.easygl.core.graphics.Renderer;
import com.vanix.easygl.commons.Identified;

public interface Module<A extends ClientApp, C extends RenderContext> extends Renderer<A, C>, Identified<String> {
}
