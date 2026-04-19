import javafx.geometry.Bounds;
import javafx.scene.control.Label;

public class Collision {
	public static int handleVacuum(Hunter hunter, Ghost ghost, Ripper ripper, Wisp wisp, PlayableArea area, int score) {
		if (area.getChildren().contains(ghost.getView())) {
			Bounds ghostBounds = ghost.getView().localToScene(ghost.getView().getBoundsInLocal());
			if (hunter.vacuumArea().intersects(ghostBounds) && !hunter.hunterBounds().intersects(ghostBounds)) {
				ghost.applyScannerEffect(true);
				ghost.getView().setScaleX(ghost.getView().getScaleX() - 0.01);
				ghost.getView().setScaleY(ghost.getView().getScaleY() - 0.01);
				if (ghost.getView().getScaleX() < 0.2) {
					area.getChildren().remove(ghost.getView());
					score += 10;
				}
			} 
			else {
				ghost.applyScannerEffect(false);
			}
		}
		if (area.getChildren().contains(ripper.getView())) {
			Bounds ripperBounds = ripper.getView().localToScene(ripper.getView().getBoundsInLocal());
			if (hunter.vacuumArea().intersects(ripperBounds) && !hunter.hunterBounds().intersects(ripperBounds)) {
				ripper.applyScannerEffect(true);
				ripper.getView().setScaleX(ripper.getView().getScaleX() - 0.01);
				ripper.getView().setScaleY(ripper.getView().getScaleY() - 0.01);
				if (ripper.getView().getScaleX() < 0.2) {
					area.getChildren().remove(ripper.getView());
					score += 20;
				}
			}
			else {
				ripper.applyScannerEffect(false);
			}
		}
		if (area.getChildren().contains(wisp.getView())) {
			Bounds wispBounds = wisp.getView().localToScene(wisp.getView().getBoundsInLocal());
			if (hunter.vacuumArea().intersects(wispBounds) && !hunter.hunterBounds().intersects(wispBounds)) {
				wisp.applyScannerEffect(true);
				wisp.getView().setScaleX(wisp.getView().getScaleX() - 0.01);
				wisp.getView().setScaleY(wisp.getView().getScaleY() - 0.01);
				if (wisp.getView().getScaleX() < 0.2) {
					area.getChildren().remove(wisp.getView());
					score += 30;
				}
			}
			else {
				wisp.applyScannerEffect(false);
			}
		}
		return score;
	}
}
