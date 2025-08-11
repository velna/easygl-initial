package com.vanix.easygl.core.meta;

import com.vanix.easygl.commons.util.TypeReference;

import java.util.ServiceLoader;

public enum MetaSystem implements MetaService {
    Window,
    Graphics,
    Media;
    private final MetaService metaService;

    MetaSystem() {
        metaService = ServiceLoader.load(MetaService.class)
                .stream()
                .filter(provider -> provider.type().getAnnotation(SystemName.class).value().equals(name()))
                .findFirst()
                .map(ServiceLoader.Provider::get)
                .orElseThrow();
    }

    @Override
    public <T> T of(Class<?> key, TypeReference<T> returnType, Object... args) {
        return metaService.of(key, returnType, args);
    }

    @Override
    public int queryInt(String id) {
        return metaService.queryInt(id);
    }

    @Override
    public Object[] queryArray(String id) {
        return metaService.queryArray(id);
    }

    @Override
    public int getError() {
        return metaService.getError();
    }

    @Override
    public void close() {
        metaService.close();
    }

    public static void closeAll() {
        for (var system : values()) {
            system.close();
        }
    }

}
