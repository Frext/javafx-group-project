import javafx.geometry.Bounds;

public class Health {
	public static int damage(int health, Hunter hunter, Ghost ghost, Ripper ripper, Wisp wisp, PlayableArea area) {
		if (area.getChildren().contains(ghost.getView())) {
			Bounds ghostBounds = ghost.getView().localToScene(ghost.getView().getBoundsInLocal());
			if (hunter.hunterBounds().intersects(ghostBounds)) {
				health -= 0.0001;
			}
		}
		if (area.getChildren().contains(ripper.getView())) {
			Bounds ripperBounds = ripper.getView().localToScene(ripper.getView().getBoundsInLocal());
			if (hunter.hunterBounds().intersects(ripperBounds)) {
				health -= 0.0002;
			}
		}
		if (area.getChildren().contains(wisp.getView())) {
			Bounds wispBounds = wisp.getView().localToScene(wisp.getView().getBoundsInLocal());
			if (hunter.hunterBounds().intersects(wispBounds)) {
				health -= 0.0003;
			}
		}
		return health;
	}
	public static double token(double health) {
		return health;
	}
}
