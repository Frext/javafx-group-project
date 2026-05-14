import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class PlayableArea extends Pane{
	private double minX , minY , maxX , maxY;
	private double width , height;
	
	//this is where we add entities and token Emir Aydın 150124001
	public PlayableArea(double x , double y , double width, double height) {
		minX = x;
		minY = y;
		maxX = x+ width;
		maxY = y + height;
		
		Pane playableArea = new Pane();
		
		playableArea.setPrefWidth(width);
		playableArea.setPrefHeight(height);
		playableArea.setLayoutX(minX);
		playableArea.setLayoutY(minY);
		playableArea.setFocusTraversable(true); // This is keyboard listener
		
		// Abdullah Derviş Kombıçak 150124009 M button action which makes visible the playable area border and enemies.
		playableArea.setOnKeyPressed(e -> {
		if (e.getCode() == KeyCode.M) {
			playableArea.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			GameScreen.setIsEyeOn(true);
		}	
		});
		
		playableArea.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.M) {
				playableArea.setStyle("");
				GameScreen.setIsEyeOn(false);
			}
		});
		this.getChildren().add(playableArea);
		
	}
	public double getMinX() {
		return minX;
	}
	public double getMinY() {
		return minY;
	}
	
	public double getMaxX() {
		return maxX;
	}
	public double getMaxY() {
		return maxY;
	}
}
