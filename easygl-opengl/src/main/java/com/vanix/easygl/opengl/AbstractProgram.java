package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.*;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

public abstract class AbstractProgram<T extends IProgram<T>> extends AbstractBindable<BindTarget.Default<T>, T> implements IProgram<T> {

    private final String id;
    private final MutableIntObjectMap<Shader> shaders = new IntObjectHashMap<>();

    protected AbstractProgram(int handle, String id, BindTarget.Default<T> target) {
        super(handle, target, GLX::glDeleteProgram);
        this.id = id;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public T attach(Shader shader) {
        shaders.getIfAbsentPut(shader.intHandle(), () -> {
            GLX.glAttachShader(intHandle(), shader.intHandle());
            GLX.checkError();
            return shader;
        });
        return self();
    }

    @Override
    public T detach(Shader shader) {
        if (shaders.remove(shader.intHandle()) != null) {
            GLX.glDetachShader(intHandle(), shader.intHandle());
            GLX.checkError();
        } else {
            throw new IllegalStateException("Shader never attached.");
        }
        return self();
    }

    @Override
    public T link() throws GraphicsException {
        int program = intHandle();
        GLX.glLinkProgram(program);
        IntBuffer success = MemoryStack.stackMallocInt(1);
        GL20.glGetProgramiv(program, GL20.GL_LINK_STATUS, success);
        if (success.get() == 0) {
            String infoLog = GL20.glGetProgramInfoLog(program);
            throw new GraphicsException("error link program: " + infoLog);
        }
        return self();
    }

    @Override
    public void close() {
        super.close();
        shaders.forEach(Shader::close);
        shaders.clear();
    }
}