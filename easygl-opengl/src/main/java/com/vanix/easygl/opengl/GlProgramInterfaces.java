package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterface;
import com.vanix.easygl.core.graphics.ProgramInterfaces;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class GlProgramInterfaces extends ProgramInterfaces {
    public GlProgramInterfaces(Program program) {
        super(program);
    }

    @Override
    protected int getActiveResources(ProgramInterface programInterface) {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_ACTIVE_RESOURCES, programInterface.value());
    }

    @Override
    protected int getMaxNameLength(ProgramInterface programInterface) {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_MAX_NAME_LENGTH, programInterface.value());
    }

    @Override
    protected int getMaxNumActiveVariables(ProgramInterface programInterface) {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_MAX_NUM_ACTIVE_VARIABLES, programInterface.value());
    }

    @Override
    protected int getMaxNumCompatibleSubroutines(ProgramInterface programInterface) {
        return GLX.glGetProgramInterfacei(program.intHandle(), GLX.GL_MAX_NUM_COMPATIBLE_SUBROUTINES, programInterface.value());
    }

    @Override
    protected String getResourceName(ProgramInterface programInterface, int index) {
        return GLX.glGetProgramResourceName(program.intHandle(), programInterface.value(), index);
    }

    @Override
    protected int getResourceLocation(ProgramInterface programInterface, String name) {
        return GLX.glGetProgramResourceLocation(program.intHandle(), programInterface.value(), name);
    }

    @Override
    protected int getResourceIndex(ProgramInterface programInterface, String name) {
        return GLX.glGetProgramResourceIndex(program.intHandle(), programInterface.value(), name);
    }

    @Override
    protected int getResourceLocationIndex(ProgramInterface programInterface, String name) {
        return GLX.glGetProgramResourceLocationIndex(program.intHandle(), programInterface.value(), name);
    }

    @Override
    protected Map<Program.PropertyKey, Integer> preload(ProgramInterface programInterface, int index, List<Program.PropertyKey> propertyKeys) {
        int[][] data = new int[2][propertyKeys.size()];
        for (int i = 0; i < propertyKeys.size(); i++) {
            data[0][i] = propertyKeys.get(i).value();
        }
        GLX.glGetProgramResourceiv(program.intHandle(), programInterface.value(), index, data[0], null, data[1]);
        Map<Program.PropertyKey, Integer> map = new EnumMap<>(Program.PropertyKey.class);
        for (int i = 0; i < data[1].length; i++) {
            map.put(propertyKeys.get(i), data[1][i]);
        }
        return map;
    }

    @Override
    protected int queryInt(ProgramInterface programInterface, Program.PropertyKey key, int index) {
        int[][] data = new int[2][1];
        data[0][0] = key.value();
        GLX.glGetProgramResourceiv(program.intHandle(), programInterface.value(), index, data[0], null, data[1]);
        return data[1][0];
    }

    @Override
    protected boolean queryBoolean(ProgramInterface programInterface, Program.PropertyKey key, int index) {
        int[][] data = new int[2][1];
        data[0][0] = key.value();
        GLX.glGetProgramResourceiv(program.intHandle(), programInterface.value(), index, data[0], null, data[1]);
        return data[1][0] == GLX.GL_TRUE;
    }

    @Override
    protected int[] queryIntArray(ProgramInterface programInterface, Program.PropertyKey key, int index) {
        int activeResources = queryInt(programInterface, Program.PropertyKey.ActiveVariables, index);
        int[] props = new int[]{key.value()};
        int[] data = new int[activeResources];
        GLX.glGetProgramResourceiv(program.intHandle(), programInterface.value(), index, props, null, data);
        return data;
    }
}
