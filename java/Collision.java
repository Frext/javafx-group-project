import java.util.ArrayList;

import javafx.geometry.Bounds;

public class Collision {
	
	public static int handleVacuum(ArrayList<Entity> enemies, Hunter hunter, PlayableArea area, int score) {
		
		var iterator = enemies.iterator();
		
		while(iterator.hasNext()) {
			Entity entity = iterator.next();
			
			Bounds entityBounds = entity.getView().localToScene(entity.getView().getBoundsInLocal());
			
			if (hunter.vacuumArea().intersects(entityBounds) && !hunter.hunterBounds().intersects(entityBounds)) {
				
				((Enemy)entity).applyScannerEffect(true);
				
				entity.getView().setScaleX(entity.getView().getScaleX() - 0.01);
				entity.getView().setScaleY(entity.getView().getScaleY() - 0.01);
				
				if (entity.getView().getScaleX() < 0.2) {
					//remove from scene
					area.getChildren().remove(entity.getView());
					
					//remove from the arrayList
					iterator.remove();
					
					//increment the score
					if (entity instanceof Ghost) {
						score += 10;
					} else if (entity instanceof Ripper) {
						score += 20;
					} else if (entity instanceof Wisp) {
						score += 30;
					}
				}
			}
			
			else {
				((Enemy)entity).applyScannerEffect(false);
			}
		}
		
		return score;
	}
}
