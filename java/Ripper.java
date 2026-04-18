import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ripper extends Entity implements Enemy{
	
	private double Vx;
	private double Vy;
	
	public Ripper(double x, double y, boolean isVisible) {
        super(x, y, isVisible);
        this.Vx = Randomizer.setV();
        this.Vy = Randomizer.setV();
    }
	
    @Override
    public void move(double minX, double minY, double maxX, double maxY) {
    	
    	double tempX = this.x;
    	double tempY = this.y;
    	
    	tempX += Vx;
    	tempY += Vy;
    	
    	if(tempX < minX) {
    		tempX = minX;
    		Vx *= -1;
    	}
    	if(tempY < minY) {
    		tempY = minY;
    		Vy *= -1;
    	}
    	if(tempX > maxX) {
    		tempX = maxX;
    		Vx *= -1;
    	}
    	if(tempY > maxY) {
    		tempY = maxY;
    		Vy *= -1;
    	}
    	
    	this.x = tempX;
    	this.y = tempY;
    	
    	this.view.setLayoutX(this.x);
    	this.view.setLayoutY(this.y);
    }

    @Override
    public Group implementView() {
        Circle face = new Circle(0,0, 15);
        Circle rightEye = new Circle(-6, 0, 3);
        Circle leftEye = new Circle(6,0,3);

        face.setFill(Color.BLACK);
        leftEye.setFill(Color.RED);
        rightEye.setFill(Color.RED);

        Polygon purpleSpikes = new Polygon();

        // We have 12 inner and 12 outer spike points.
        for(int i = 0; i < 24; i++){
            double angleInRadians = Math.toRadians(i * 360.0 / 24);

            // Use outer radius and inner radius consecutively to create spikes
            double radius = (i % 2 == 0) ? 30 : 20;

            purpleSpikes.getPoints().addAll(radius * Math.cos(angleInRadians), radius * Math.sin(angleInRadians));
        }
        purpleSpikes.setFill(Color.PURPLE);

        Group group = new Group();
        // purpleSpikes is put first because addAll puts the first argument to the bottom and others on top of that
        group.getChildren().addAll(purpleSpikes, face, leftEye, rightEye);

        return group;
    }

    @Override
    public void applyScannerEffect(boolean isInsideTriangle) {
       
    }
}
