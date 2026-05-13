import javafx.animation.Transition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BarPane extends VBox {
    private final double maxBarHeight = 200;
    private Rectangle fillRectangle;

    private Transition heightTransition;

    private double currentPercentage = 1.0;	
    
    public BarPane(String text, Color fillColor){
        this.setAlignment(Pos.CENTER);

        // Top text
        Label label = new Label(text);
        label.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");

        // Bar container that holds black and custom color rectangles.
        StackPane barContainer = new StackPane();
        barContainer.setMaxWidth(60);
        barContainer.setStyle("-fx-border-color: black; -fx-background-color: black; -fx-padding: 10 0 10 0;");
        // We set it aligned to top because we want to decrease the filled rectangle from the bottom.
        barContainer.setAlignment(Pos.TOP_CENTER);

        // We need to use backgroundRectangle because barContainer matches itself vertically with fillRectangle otherwise.
        Rectangle backgroundRectangle = new Rectangle(40, maxBarHeight, Color.BLACK);
        fillRectangle = new Rectangle(40, maxBarHeight, fillColor);

        // Merge the bar
        barContainer.getChildren().addAll(backgroundRectangle, fillRectangle);

        // Merge bar and text
        this.getChildren().addAll(label, barContainer);
    }

    public void setFill(double percentage){
    	this.currentPercentage = Math.clamp(percentage, 0.0, 1.0);
    	fillRectangle.setHeight(maxBarHeight * this.currentPercentage);
    }
    
    public double getPercentage() {
    	return this.currentPercentage;
    }
}
