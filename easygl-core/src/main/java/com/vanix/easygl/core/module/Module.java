package com.vanix.easygl.core.module;

import com.vanix.easygl.core.ClientApp;
import com.vanix.easygl.core.RenderContext;
import com.vanix.easygl.core.Renderer;
import com.vanix.easygl.commons.Identified;

public interface Module<A extends ClientApp, C extends RenderContext> extends Renderer<A, C>, Identified<String> {
}
