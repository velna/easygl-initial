package com.vanix.easygl.core.media;

import com.vanix.easygl.commons.bufferio.BufferField;
import com.vanix.easygl.commons.bufferio.BufferStruct;
import com.vanix.easygl.commons.util.TypeReferenceBean;
import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.graphics.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.collections.api.list.primitive.IntList;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Mesh implements Closeable {
    private final List<Vertex> vertices;
    private final IntList indices;
    private final Map<Model.TextureType, List<TextureInfo>> textures;
    private final VertexArray vao;
    private final Buffer vbo;
    private final Buffer ebo;

    public Mesh(List<Vertex> vertices, IntList indices, Map<Model.TextureType, List<TextureInfo>> textures) {
        this.vertices = vertices;
        this.indices = indices;
        this.textures = textures;
        vao = VertexArray.of();

        vbo = Buffer.of(DataType.Float).bind(Buffer.Target.Array);
        var vboMapping = vbo.createMapping(new TypeReferenceBean<>(vertices) {
        }, 0);
        vbo.realloc(Buffer.DataUsage.StaticDraw, vboMapping.size());
        vboMapping.flush();

        ebo = Buffer.of(DataType.UnsignedInt).bind(Buffer.Target.ElementArray);
        var eboMapping = ebo.createMapping(this.indices, 0);
        ebo.realloc(Buffer.DataUsage.StaticDraw, eboMapping.size());
        eboMapping.flush();

        vao.bind().attributes(3f, 3f, 2f, 3f, 3f, 14, 4f);
        vboMapping.close();
        eboMapping.close();
    }

    public void draw() {
        vao.bind().drawElements(DrawMode.Triangles, vbo, ebo);
        vao.unbind();
    }

    public List<TextureInfo> getTextures(Model.TextureType textureType) {
        var list = textures.get(textureType);
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    @Override
    public void close() {
        textures.values().forEach(list -> list.forEach(textureInfo -> textureInfo.texture.close()));
        vao.close();
        vbo.close();
        ebo.close();
    }

    @Data
    @NoArgsConstructor
    public static class Vertex {
        private Vector3f position;
        private Vector3f normal;
        private Vector2f texCoords;
        private Vector3f tangent;
        private Vector3f bitangent;
        private int[] boneIds;
        private float[] weights;

        @BufferStruct
        public Vertex(Vector3f position, Vector3f normal, Vector2f texCoords, Vector3f tangent, Vector3f bitangent,
                      @BufferField(count = 4) int[] boneIds, @BufferField(count = 4) float[] weights) {
            this.position = position;
            this.normal = normal;
            this.texCoords = texCoords;
            this.tangent = tangent;
            this.bitangent = bitangent;
            this.boneIds = boneIds;
            this.weights = weights;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextureInfo {
        private Texture2D texture;
        private Model.TextureType type;
    }
}
