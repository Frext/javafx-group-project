package myjavaFXprograms;

import java.util.Random;

public class Randomizer {
	private static Random random = new Random();

	public static double getX(PlayableArea area) {
		double x = ((Math.random() * (area.getMaxX() - area.getMinX())) + area.getMinX());
		return x;
	}
	public static double initialVelocity() {
		return random.nextBoolean() ? 1.0 : -1.0;
	}
	public static double getY(PlayableArea area) {

		double y = ((Math.random() * (area.getMaxY() - area.getMinY())) + area.getMinY());
		return y;
	}

}
