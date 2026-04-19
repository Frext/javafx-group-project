import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class playArea extends Pane{
	private double x;
	private double y;
	// We'll create panes for playAreas for each level
	public playArea(double width, double height) {
		x = width;
		y = height;
		Pane playArea = new Pane();
		playArea.setPrefWidth(width);
		playArea.setPrefHeight(height);
		playArea.setFocusTraversable(true); // This is keyboard listener
		playArea.setOnKeyPressed(e -> {
		if (e.getCode() == KeyCode.M) {
			playArea.setStyle("-fx-border-color: red; -fx-border-width: 3px;");	
		}	
		});
		playArea.setOnKeyReleased(e -> {
			playArea.setStyle("");
		});
		this.getChildren().add(playArea);
		
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
}
