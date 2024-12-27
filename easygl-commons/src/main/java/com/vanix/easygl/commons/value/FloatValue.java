package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.App;
import com.vanix.easygl.commons.Ticket;
import com.vanix.easygl.commons.task.Task;

public interface FloatValue extends Value<FloatInterceptor> {
	float get();

	float set(float v);

	default float incr() {
		return incr(1);
	}

	default float incr(float amount) {
		float v = get();
		return set(v + amount) - v;
	}

	default float incr(float amount, long keep) {
		float ret = incr(amount);
		App.submit(Task.timeout("IncrKeep", keep, () -> incr(-amount)));
		return ret;
	}

	default float incr(float amount, double keepSeconds) {
		return incr(amount, Ticket.ofSeconds(keepSeconds));
	}

	default float multiply(float factor) {
		float v = get();
		return set(v * factor) - v;
	}
}