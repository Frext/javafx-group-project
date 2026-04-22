package com.group1.groupproject;

import javafx.geometry.Bounds;

public class Health {
	public static double damage(double health, Hunter hunter, Ghost ghost, Ripper ripper, Wisp wisp,
			PlayableArea area) {
		boolean isDamaged = false;

		if (area.getChildren().contains(ghost.getView())) {
			Bounds ghostBounds = ghost.getView().localToScene(ghost.getView().getBoundsInLocal());
			if (hunter.hunterBounds().intersects(ghostBounds)) {
				isDamaged = true;
				health -= 0.01;
				if (health < 0)
					health = 0;
			}
		}
		if (area.getChildren().contains(ripper.getView())) {
			Bounds ripperBounds = ripper.getView().localToScene(ripper.getView().getBoundsInLocal());
			if (hunter.hunterBounds().intersects(ripperBounds)) {
				isDamaged = true;
				health -= 0.02;
				if (health < 0)
					health = 0;
			}
		}
		if (area.getChildren().contains(wisp.getView())) {
			Bounds wispBounds = wisp.getView().localToScene(wisp.getView().getBoundsInLocal());
			if (hunter.hunterBounds().intersects(wispBounds)) {
				isDamaged = true;
				health -= 0.03;
				if (health < 0)
					health = 0;
			}
		}

		hunter.applyDamageEffect(isDamaged);
		return health;
	}

	public static double token(double health) {
		return health;
	}
}
