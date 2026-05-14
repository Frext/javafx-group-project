import java.util.Random;

public class Randomizer { // Abdullah Derviş Kombıçak 150124009 && Emir Aydın 150124001.
	private static Random random = new Random();

	//takes playable area as a parameter because in that we have method for corner coordinates so we dont have to enter them one by one each time Emir Aydın 150124001

	//return a random X coordinate regarding the given area
	public static double getX(PlayableArea area) {
		double x = ((Math.random() * (area.getMaxX() - area.getMinX())) + area.getMinX());
		return x;
	}

	//to spawn enemies random locations and random velocities Abdullah Derviş Kombıçak 150124009
	public static double initialVelocity() {
		return random.nextBoolean() ? 1.0 : -1.0;
	}

	//return a random Y coordinate regarding the given area
	public static double getY(PlayableArea area) {

		double y = ((Math.random() * (area.getMaxY() - area.getMinY())) + area.getMinY());
		return y;
	}

}
