import java.util.Random;

public class Randomizer {
	private static Random random = new Random();
	private static double[] velocities = { 1.0, -1.0 };
	private static double Vx = random.nextInt(velocities.length);
	private static double Vy = random.nextInt(velocities.length);

	public static double getX(PlayableArea area) {
		double x = ((Math.random() * (area.getMaxX() - area.getMinX())) + area.getMinX());
		return x;
	}

	public static double getY(PlayableArea area) {

		double y = ((Math.random() * (area.getMaxY() - area.getMinY())) + area.getMinY());
		return y;
	}
	public static void VxChanger() {
		Vx = -Vx;
	}
	public static void VyChanger() {
		Vy = -Vy;
	}

}
