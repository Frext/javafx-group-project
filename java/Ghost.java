
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ghost extends Entity implements Enemy{
	private double Vx = Randomizer.initialVelocity();
	private double Vy = Randomizer.initialVelocity();
	private int time;
	private ColorAdjust adjust = new ColorAdjust();
    public Ghost(double x, double y, boolean isVisible) {
        super(x, y, isVisible);
        
    }

    @Override
    public void applyScannerEffect(boolean isInsideTriangle) {
		if (isInsideTriangle) {
			time++;
			if (time % 20 < 10) {
				adjust.setBrightness(-1.0);
				view.setEffect(adjust);
				view.setOpacity(1.0);
			} else {
				view.setEffect(null);
			}
		} else {
			view.setEffect(null);
		}

	}
    // Restricted movements for NPC
    	Bounds bounds = view.getBoundsInParent();
    @Override
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
        Circle rightEye = new Circle(-6, -2, 3);
        Circle rightPupil = new Circle(-6, -2, 1);
        Circle leftEye = new Circle(6,-2,3);
        Circle leftPupil = new Circle(6,-2,1);
        Rectangle bottomRectangle = new Rectangle(-15,0, 30, 15);

        face.setFill(Color.WHITE);
        face.setOpacity(.5);
        rightEye.setFill(Color.WHITE);
        leftEye.setFill(Color.WHITE);
        rightPupil.setFill(Color.BLACK);
        leftPupil.setFill(Color.BLACK);
        bottomRectangle.setFill(Color.WHITE);
        bottomRectangle.setOpacity(.5);

        Group group = new Group();
        group.getChildren().addAll(bottomRectangle, face, rightEye, leftEye, rightPupil, leftPupil);

        return group;
    }
}
