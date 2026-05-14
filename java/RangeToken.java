import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;

public class RangeToken extends Token{//Emir Aydın 150124001
	
	private Circle circle;

	//for constructor chaining
	public RangeToken(double x , double y, Runnable rangeEffect) {
		super(x , y, rangeEffect);
	}

	//to create the view of the token
	public Group implementView() {
		
		circle = new Circle(0 , 0 , 12);
		circle.setFill(Color.YELLOW);
		
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(-10.0 , 0.0 , 5.0 , -8.66 , 5.0 , 8.66);
		triangle.setFill(Color.BLACK);
		
		Group group = new Group();
		group.getChildren().addAll(circle , triangle);
		
		return group;
	}
}
