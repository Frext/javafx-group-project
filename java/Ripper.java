import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ripper extends Entity implements Enemy{
	
	private double Vx = Randomizer.initialVelocity();
	private double Vy = Randomizer.initialVelocity();
	
	public Ripper(double x, double y, boolean isVisible) {
        super(x, y, isVisible);
    }
    // Restricted movements for NPC
    
	public void move(double minX, double minY, double maxX, double maxY) {
    	view.setLayoutX(view.getLayoutX() + Vx);
        view.setLayoutY(view.getLayoutY() + Vy);
        Bounds bounds = view.getBoundsInParent();
    	if (bounds.getMinX() <= minX || bounds.getMaxX() >= maxX) {
    		Vx = -Vx;
          }
          if (bounds.getMinY() <= minY || bounds.getMaxY() >= maxY) {
    		Vy = -Vy;
          }
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
