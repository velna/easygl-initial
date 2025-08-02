package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.util.TypeReference;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractMetaService implements MetaService {
    private final Map<Class<?>, Object> registry = new HashMap<>();

    protected void register(Class<?> type, Object meta) {
        if (registry.put(type, meta) != null) {
            throw new IllegalArgumentException(type.getName());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <T> T of(Class<?> key, TypeReference<T> returnType, Object... args) {
        return switch (registry.get(key)) {
            case Function function -> (T) function.apply(args);
            case Object obj -> (T) obj;
            case null -> throw new IllegalArgumentException("Unknown type: " + key.getName());
        };
    }

    protected Optional<Integer> queryStaticIntField(Class<?> type, String fieldPrefix, String id) {
        try {
            return Optional.of(type.getField(fieldPrefix + id).getInt(null));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return Optional.empty();
        }
    }
}
