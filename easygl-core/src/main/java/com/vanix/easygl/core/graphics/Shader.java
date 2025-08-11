package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.util.TypeReference;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.HandleMeta;
import com.vanix.easygl.core.meta.MetaSystem;

import java.io.IOException;
import java.io.InputStream;

public interface Shader extends Handle {
    HandleMeta<Shader> Meta = MetaSystem.Graphics.of(Shader.class, new TypeReference<>() {
    });

    enum Type {
        Vertex("VERTEX_SHADER"),
        Geometry("GEOMETRY_SHADER"),
        Fragment("FRAGMENT_SHADER");

        private final int value;

        private Type(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
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

    static Shader of(Type type) {
        return Meta.create(type);
    }

    static Shader vertex() {
        return of(Type.Vertex);
    }

    static Shader geometry() {
        return of(Type.Geometry);
    }

    static Shader fragment() {
        return of(Type.Fragment);
    }

    private static Shader of(Type type, String resourceFile) {
        Shader shader = of(type);
        try {
            shader.source(Shader.class.getClassLoader().getResourceAsStream(resourceFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shader;
    }

    static Shader vertex(String resourceFile) {
        return of(Type.Vertex, resourceFile);
    }

    static Shader geometry(String resourceFile) {
        return of(Type.Geometry, resourceFile);
    }

    static Shader fragment(String resourceFile) {
        return of(Type.Fragment, resourceFile);
    }
}
