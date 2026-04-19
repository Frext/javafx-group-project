import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class EyeToken extends Token{
	
	private Circle circle;
	
	public EyeToken(double x , double y) {
		super(x , y);
	}
	
	public Group implementView() {
		
		circle = new Circle(0 , 0 , 12);
		circle.setFill(Color.YELLOW);
		
		Ellipse ell = new Ellipse(0 , 0 , 10 , 7);
		ell.setFill(Color.WHITE);
		ell.setStroke(Color.BLACK);
		
		Circle iris = new Circle(0 , 0 , 5);
		iris.setFill(Color.BLUE);
		
		Circle pupil = new Circle(0 , 0 , 3);
		pupil.setFill(Color.BLACK);
		
		Circle w = new Circle(1 , -2 , 1);
		w.setFill(Color.WHITE);
		
		Group group = new Group();
		group.getChildren().addAll(circle , ell , iris , pupil , w);
		
		return group;
	}
	
	public void giveEffect() {
		//TODO implement effect
		
		this.consume();
	}
}
