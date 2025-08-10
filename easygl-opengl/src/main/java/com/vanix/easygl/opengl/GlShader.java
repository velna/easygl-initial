package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.Shader;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

public class GlShader extends AbstractHandle implements Shader {

    private final Shader.Type type;
    private String source;

    public GlShader(Shader.Type type) {
        this(GLX.glCreateShader(type.value()), type);
    }

    public GlShader(int handle, Shader.Type type) {
        super(handle, GLX::glDeleteShader);
        this.type = type;
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public Shader source(String source) {
        GLX.glShaderSource(intHandle(), source);
        this.source = source;
        return this;
    }

    @Override
    public Shader source(InputStream in) throws IOException {
        StringWriter out = new StringWriter();
        try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            reader.transferTo(out);
        }
        return source(out.toString());
    }

    @Override
    public String source() {
        return source;
    }

    @Override
    public Shader compile() throws GraphicsException {
        int shader = intHandle();
        GLX.glCompileShader(shader);
        IntBuffer success = MemoryStack.stackMallocInt(1);
        GLX.glGetShaderiv(shader, GLX.GL_COMPILE_STATUS, success);
        if (success.get() == 0) {
            String infoLog = GLX.glGetShaderInfoLog(shader);
            throw new GraphicsException(String.format("error compile source %s: %s", source, infoLog));
        }
        return this;
    }

}
