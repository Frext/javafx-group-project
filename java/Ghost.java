import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/** Furkan USUL 150125042
 * This class implements the enemy entity Ghost.
 */
public class Ghost extends Entity implements Enemy{
	private double Vx = Randomizer.initialVelocity(); //Abdullah Derviş Kombıçak 150124009
	private double Vy = Randomizer.initialVelocity();
	private int time;
	private ColorAdjust adjust = new ColorAdjust();

    /** Furkan USUL 150125042
     * Initialize the enemy entity Ghost via superclass constructor.
     */
    public Ghost(double x, double y, boolean isVisible) {
        super(x, y, isVisible);
    }
    // Abdullah Derviş Kombıçak 150124009 enemy collision animation
    @Override
    public void applyScannerEffect(boolean isInsideTriangle) {
		if (isInsideTriangle) {
            view.setVisible(true);

			time++;
			if (time % 20 < 10) {
				adjust.setBrightness(-1.0);
				view.setEffect(adjust);
				view.setOpacity(1.0);
			} else {
				view.setEffect(null);
			}
		} else {
            view.setVisible(false);
			view.setEffect(null);
		}

	}
    // Abdullah Derviş Kombıçak 150124009 Restricted movements for NPC
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

    /** Furkan USUL 150125042
     * This method implements the visual of the Ghost enemy entity.
     */
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
