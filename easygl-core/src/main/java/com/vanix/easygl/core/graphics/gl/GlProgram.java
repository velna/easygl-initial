package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.*;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;

public class GlProgram extends AbstractBindable<BindTarget.Default<Program>, Program> implements Program {

    private final String id;
    private final MutableIntObjectMap<Shader> shaders = new IntObjectHashMap<>();

    public GlProgram(String id) {
        super(GLC.glCreateProgram(), Target);
        this.id = id;
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
        shaders.getIfAbsentPut(shader.handle(), () -> {
            GLC.glAttachShader(handle(), shader.handle());
            GLC.checkError();
            return shader;
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
