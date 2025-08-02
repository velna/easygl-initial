package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.HandleMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.commons.util.TypeReference;

import java.io.IOException;
import java.io.InputStream;

public interface Shader extends Handle, Identified<String> {
    HandleMeta<Shader> Meta = MetaSystem.Graphics.of(Shader.class, new TypeReference<>() {
    });

    enum Type {
        Vertex(MetaSystem.Graphics.queryInt("VERTEX_SHADER")),
        Geometry(MetaSystem.Graphics.queryInt("GEOMETRY_SHADER")),
        Fragment(MetaSystem.Graphics.queryInt("FRAGMENT_SHADER"));

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
        return Meta.create(id, Type.Vertex);
    }

    static Shader geometry(String id) {
        return Meta.create(id, Type.Geometry);
    }

    static Shader fragment(String id) {
        return Meta.create(id, Type.Fragment);
    }

    private static Shader of(String id, Type type, String resourceFile) {
        Shader shader = Meta.create(id, type);
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
