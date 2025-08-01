package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;

public interface IProgram<T extends IProgram<T>> extends Bindable<BindTarget.Default<T>, T>, Identified<String> {

    T attach(Shader shader);

    T detach(Shader shader);

    T link() throws GraphicsException;
}
