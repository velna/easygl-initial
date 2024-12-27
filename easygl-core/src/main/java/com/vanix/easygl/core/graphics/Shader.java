package com.vanix.easygl.core.graphics;

import java.io.IOException;
import java.io.InputStream;

import com.vanix.easygl.core.graphics.gl.GLC;
import com.vanix.easygl.core.graphics.gl.GlShader;
import com.vanix.easygl.commons.Identified;

public interface Shader extends Handle, Identified<String> {

	enum Type {
		Vertex(GLC.GL_VERTEX_SHADER),
		Geometry(GLC.GL_GEOMETRY_SHADER),
		Fragment(GLC.GL_FRAGMENT_SHADER);

		private final int value;

		private Type(int value) {
			this.value = value;
		}

		public int value() {
			return value;
		}
	}

	Type type();

	Shader source(String source);

	Shader source(InputStream in) throws IOException;

	String source();

	Shader compile() throws GraphicsException;

	static Shader vertex(String id) {
		return new GlShader(id, Type.Vertex);
	}

	static Shader geometry(String id) {
		return new GlShader(id, Type.Geometry);
	}

	static Shader fragment(String id) {
		return new GlShader(id, Type.Fragment);
	}

	private static Shader of(String id, Type type, String resourceFile) {
		Shader shader = new GlShader(id, type);
		try {
			shader.source(Shader.class.getClassLoader().getResourceAsStream(resourceFile));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return shader;
	}

	static Shader vertex(String id, String resourceFile) {
		return of(id, Type.Vertex, resourceFile);
	}

	static Shader geometry(String id, String resourceFile) {
		return of(id, Type.Geometry, resourceFile);
	}

	static Shader fragment(String id, String resourceFile) {
		return of(id, Type.Fragment, resourceFile);
	}
}
