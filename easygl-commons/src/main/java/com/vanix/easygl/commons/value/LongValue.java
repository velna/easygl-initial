package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.App;
import com.vanix.easygl.commons.Ticket;
import com.vanix.easygl.commons.task.Task;

public interface LongValue extends Value<LongInterceptor> {
	long get();

	long set(long v);

	default long incr() {
		return incr(1);
	}

	default long incr(long amount) {
		long v = get();
		return set(v + amount) - v;
	}

	default long incr(long amount, long keep) {
		long ret = incr(amount);
		App.submit(Task.timeout("IncrKeep", keep, () -> incr(-amount)));
		return ret;
	}

	default long incr(long amount, double keepSeconds) {
		return incr(amount, Ticket.ofSeconds(keepSeconds));
	}

	default long multiply(long factor) {
		long v = get();
		return set(v * factor) - v;
	}
}