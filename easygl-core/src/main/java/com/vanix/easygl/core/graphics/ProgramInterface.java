package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Support;

import java.util.List;

@Support(since = Version.GL43)
public interface ProgramInterface<T extends ProgramResource<T>> {

    int getActiveResources();

    List<T> getResources();

    //region Base interfaces
    interface Named<T extends ProgramResource<T>> extends ProgramInterface<T> {
        T getResource(String name);
    }

    interface Variable<T extends ProgramResource<T>> extends ProgramInterface<T> {
        int getMaxNumActiveVariables();
    }

    interface SubroutineUniform<T extends ProgramResource<T>> extends Named<T> {
        int getMaxNumCompatibleSubroutines();
    }
    //endregion

}
