package com.vanix.easygl.core.graphics.gl;

import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgramiv;

import java.nio.IntBuffer;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.lwjgl.system.MemoryStack;

import com.vanix.easygl.core.graphics.AbstractBindableHandle;
import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.Shader;

public class GlProgram extends AbstractBindableHandle<Program> implements Program {

    private final String id;
    private final MutableIntObjectMap<Shader> shaders = new IntObjectHashMap<>();

    public GlProgram(String id) {
        super(GLC.glCreateProgram(), State);
        this.id = id;
    }

    @Override
    protected void bind(int handle) {
        GLC.glUseProgram(handle);
    }

    @Override
    protected void close(int handle) {
        GLC.glDeleteProgram(handle);
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Program attach(Shader shader) {
        shaders.updateValue(shader.handle(), () -> {
            GLC.glAttachShader(handle(), shader.handle());
            GLC.checkError();
            return shader;
        }, value -> {
            throw new IllegalStateException("Shader already attached.");
        });
        return this;
    }

    @Override
    public Program detach(Shader shader) {
        if (shaders.remove(shader.handle()) != null) {
            GLC.glDetachShader(handle(), shader.handle());
            GLC.checkError();
        } else {
            throw new IllegalStateException("Shader never attached.");
        }
        return this;
    }

    @Override
    public Program link() throws GraphicsException {
        int program = handle();
        GLC.glLinkProgram(program);
        IntBuffer success = MemoryStack.stackMallocInt(1);
        glGetProgramiv(program, GL_LINK_STATUS, success);
        if (success.get() == 0) {
            String infoLog = glGetProgramInfoLog(program);
            throw new GraphicsException("error link program: " + infoLog);
        }
        return this;
    }

    @Override
    public void close() {
        super.close();
        shaders.forEach(Shader::close);
        shaders.clear();
    }
}
