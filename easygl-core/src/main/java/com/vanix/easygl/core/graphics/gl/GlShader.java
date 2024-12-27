package com.vanix.easygl.core.graphics.gl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

import org.lwjgl.system.MemoryStack;

import com.vanix.easygl.core.graphics.AbstractHandle;
import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.Shader;

public class GlShader extends AbstractHandle implements Shader {

	private final String id;
	private final Shader.Type type;
	private String source;

	public GlShader(String id, Shader.Type type) {
		super(GLC.glCreateShader(type.value()));
		this.id = id;
		this.type = type;
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	protected void close(int handle) {
		GLC.glDeleteShader(handle);
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public Shader source(String source) {
		GLC.glShaderSource(handle(), source);
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
		int shader = handle();
		GLC.glCompileShader(shader);
		IntBuffer success = MemoryStack.stackMallocInt(1);
		GLC.glGetShaderiv(shader, GLC.GL_COMPILE_STATUS, success);
		if (success.get() == 0) {
			String infoLog = GLC.glGetShaderInfoLog(shader);
			throw new GraphicsException(String.format("error compile source %s: %s", source, infoLog));
		}
		return this;
	}

}
