package com.vanix.easygl.opengl.program;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.opengl.GLX;
import com.vanix.easygl.opengl.GlProgramInterfaceType;

import java.util.*;

public abstract class BaseInterface<T extends ProgramResource<T>> implements
        ProgramInterface.Named<T>,
        ProgramInterface.Variable<T>,
        ProgramInterface.SubroutineUniform<T> {
    private final Program program;
    private final GlProgramInterfaceType type;
    private List<T> resources;
    private Map<String, T> resourceMap;

    public BaseInterface(Program program, GlProgramInterfaceType type) {
        this.program = program;
        this.type = type;
    }

    protected abstract T newResource(Program program, int index);

    @Override
    public List<T> getResources() {
        if (resources != null) {
            return resources;
        }
        int n = getActiveResources();
        if (n <= 0) {
            resources = Collections.emptyList();
        } else {
            List<T> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(newResource(program, i));
            }
            resources = Collections.unmodifiableList(list);
        }
        return resources;
    }

    @Override
    public T getResource(String name) {
        if (resourceMap == null) {
            resourceMap = new HashMap<>();
            for (var resource : getResources()) {
                if (resource instanceof ProgramResource.Named<?> named) {
                    resourceMap.put(named.getName(), resource);
                }
            }
        }
        return resourceMap.get(name);
    }

    @Override
    public int getActiveResources() {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_ACTIVE_RESOURCES, type.value());
    }

    @Override
    public int getMaxNumActiveVariables() {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_MAX_NUM_ACTIVE_VARIABLES, type.value());
    }

    @Override
    public int getMaxNumCompatibleSubroutines() {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_MAX_NUM_COMPATIBLE_SUBROUTINES, type.value());
    }
}
