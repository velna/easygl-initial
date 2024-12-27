package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.App;
import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.commons.Ticket;
import com.vanix.easygl.commons.task.TimeoutTask;

public class Cooldown extends DefaultLimitedDoubleValue implements Identified<String> {

	private final String id;
	private TimeoutTask task;

	public Cooldown(String id, DoubleValue value) {
		super(value.get(), 0, Double.MAX_VALUE);
		this.id = String.format("Cooldown#%s", id);
		if (value instanceof LeveledDoubleValue leveledValue) {
			leveledValue.level().addInterceptor((oldValue, newValue) -> {
				set(leveledValue.get(newValue));
				return newValue;
			});
		}
	}

	@Override
	public double doSet(double v) {
		if (task != null) {
			double incr = v - get();
			if (incr != 0) {
				task.incr(Ticket.ofSeconds(incr));
			}
		}
		return super.doSet(v);
	}

	public boolean apply() {
		if (task != null) {
			return false;
		} else {
			task = new TimeoutTask(id, Ticket.ofSeconds(get()), () -> task = null);
			App.submit(task);
			return true;
		}
	}

	public double remains() {
		return null == task ? 0 : Ticket.toSeconds(Math.max(task.remains(), 0));
	}

	@Override
	public String id() {
		return id;
	}
}
