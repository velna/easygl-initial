package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;

import java.io.IOException;
import java.io.InputStream;

public interface IProgram<T extends IProgram<T>> extends Bindable<BindTarget.Default<T>, T>, Identified<String> {

    T attach(Shader shader);

    default T attach(Shader.Type type, String shaderSource) {
        try (var shader = Shader.of(id() + "-" + type.name(), type).source(shaderSource).compile()) {
            return attach(shader);
        }
    }

    default T attach(Shader.Type type, InputStream inputStream) throws IOException {
        try (var shader = Shader.of(id() + "-" + type.name(), type).source(inputStream).compile()) {
            return attach(shader);
        }
    }

    T detach(Shader shader);

    T link() throws GraphicsException;
}
