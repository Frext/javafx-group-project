import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HealthToken extends Token{
	private Circle circle;
	
	public HealthToken(double x , double y, Runnable healthEffect) {
		super(x , y, healthEffect);
	}
	
	public Group implementView() {
		
		circle = new Circle(0 , 0 , 12);
		circle.setFill(Color.YELLOW);
		Rectangle rec1 = new Rectangle(-3 , -8 , 6 , 16);
		rec1.setFill(Color.RED);
		Rectangle rec2 = new Rectangle(-8 , -3 , 16 , 6);
		rec2.setFill(Color.RED);
		
		Group group = new Group();
		group.getChildren().addAll(circle , rec1 , rec2);
		
		return group;
	}
}
