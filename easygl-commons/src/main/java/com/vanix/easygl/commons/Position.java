package com.vanix.easygl.commons;

public interface Position {
	double getX();

	double getY();

	default boolean isArrived(double x, double y) {
		return ((int) getX()) == (int) x && ((int) getY()) == (int) y;
	}

	default boolean isArrived(Position dest) {
		return isArrived(dest.getX(), dest.getY());
	}

	default boolean isArrived(double x, double y, int offset) {
		return Math.abs(getX() - x) <= offset && Math.abs(getY() - y) <= offset;
	}

	default boolean isArrived(Position dest, int offset) {
		return isArrived(dest.getX(), dest.getY(), offset);
	}

	default double distance(Position to) {
		return distance(to.getX(), to.getY());
	}

	default double distance(double x, double y) {
		double ox = x - getX();
		double oy = y - getY();
		return Math.sqrt(ox * ox + oy * oy);
	}

	default boolean isInSight(Position dest, double facing, double sightAngle) {
		double angleStart = facing - sightAngle / 2;
		double angleEnd = angleStart + sightAngle;
		double angle = Math.atan((getY() - dest.getY()) / (getX() - dest.getX()));
		return angle >= angleStart && angle < angleEnd;
	}

	static Position of(double x, double y) {
		return new SimplePosition(x, y);
	}
}
