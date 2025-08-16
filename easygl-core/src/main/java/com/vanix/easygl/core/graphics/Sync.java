package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.HandleMeta;
import com.vanix.easygl.core.meta.Meta;
import com.vanix.easygl.core.meta.MetaSystem;

import java.util.concurrent.TimeUnit;

public interface Sync extends Handle {
    com.vanix.easygl.core.meta.Meta<Sync> Meta = MetaSystem.Graphics.of(Sync.class);

    Sync awaitServer();

    default Result awaitClient(int timeout, TimeUnit timeUnit) {
        return awaitClient(timeout, timeUnit, false);
    }

    Result awaitClient(int timeout, TimeUnit timeUnit, boolean flush);

    enum Result implements IntEnum {
        ALREADY_SIGNALED("ALREADY_SIGNALED"),
        TIMEOUT_EXPIRED("TIMEOUT_EXPIRED"),
        CONDITION_SATISFIED("CONDITION_SATISFIED"),
        WAIT_FAILED("WAIT_FAILED");
        private final int value;

        Result(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    static Sync of() {
        return Meta.create();
    }
}
