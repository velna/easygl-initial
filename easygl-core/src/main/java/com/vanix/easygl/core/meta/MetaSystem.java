package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.util.TypeReference;

import java.util.ServiceLoader;

public enum MetaSystem implements MetaService {
    Graphics,
    Window;
    private final MetaService metaService;

    MetaSystem() {
        metaService = ServiceLoader.load(MetaService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .filter(ms -> name().equals(ms.system()))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String system() {
        return name();
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
    public int getError() {
        return metaService.getError();
    }

    @Override
    public void close() {
        metaService.close();
    }
}
