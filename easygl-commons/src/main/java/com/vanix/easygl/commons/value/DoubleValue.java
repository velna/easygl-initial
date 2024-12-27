package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.App;
import com.vanix.easygl.commons.Ticket;
import com.vanix.easygl.commons.task.Task;

public interface DoubleValue extends Value<DoubleInterceptor> {
	double get();

	double set(double v);

	default double incr() {
		return incr(1);
	}

	default double incr(double amount) {
		double v = get();
		return set(v + amount) - v;
	}

	default double incr(double amount, long keep) {
		double ret = incr(amount);
		App.submit(Task.timeout("IncrKeep", keep, () -> incr(-amount)));
		return ret;
	}

	default double incr(double amount, double keepSeconds) {
		return incr(amount, Ticket.ofSeconds(keepSeconds));
	}

	default double multiply(double factor) {
		double v = get();
		return set(v * factor) - v;
	}
}