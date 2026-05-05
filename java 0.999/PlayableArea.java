package myjavaFXprograms;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class PlayableArea extends Pane{
	private double minX , minY , maxX , maxY;
	private double width , height;
	// We'll create panes for playAreas for each level
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
		
		playableArea.setOnKeyPressed(e -> {
		if (e.getCode() == KeyCode.M) {
			playableArea.setStyle("-fx-border-color: red; -fx-border-width: 1px;");	
		}	
		});
		
		playableArea.setOnKeyReleased(e -> {
			playableArea.setStyle("");
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
