package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.App;
import com.vanix.easygl.commons.Ticket;
import com.vanix.easygl.commons.task.Task;

public interface IntValue extends Value<IntInterceptor> {
	int get();

	int set(int v);

	default int incr() {
		return incr(1);
	}

	default int incr(int amount) {
		int v = get();
		return set(v + amount) - v;
	}

	default int incr(int amount, long keep) {
		int ret = incr(amount);
		App.submit(Task.timeout("IncrKeep", keep, () -> incr(-amount)));
		return ret;
	}

	default int incr(int amount, double keepSeconds) {
		return incr(amount, Ticket.ofSeconds(keepSeconds));
	}

	default int multiply(int factor) {
		int v = get();
		return set(v * factor) - v;
	}
}