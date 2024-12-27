package com.vanix.easygl.commons;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class SimplePosition implements Position {

	private double x;
	private double y;

	public SimplePosition(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

}
