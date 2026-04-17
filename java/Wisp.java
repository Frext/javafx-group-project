

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Wisp extends Entity implements Enemy{
    public Wisp(double x, double y, boolean isVisible) {
        super(x, y, isVisible);
    }
    @Override
    public void applyScannerEffect(boolean isInsideTriangle) {
        // TODO
    } 
    // Restricted movements for NPC
    Bounds bounds = view.getBoundsInParent();
    @Override
    public void move(double minX, double minY, double maxX, double maxY) {
    	if (bounds.getMinX() <= minX || bounds.getMaxX() >= maxX) {
    		Randomizer.VxChanger();;
          }
          if (bounds.getMinY() <= minY || bounds.getMaxY() >= maxY) {
    		Randomizer.VyChanger();
          }
    }

    @Override
    public Group implementView() {
        Circle face = new Circle(0,0, 15);
        Circle rightEye = new Circle(-6, -3, 2);
        Circle leftEye = new Circle(6,-3,2);

        face.setFill(Color.RED);
        rightEye.setFill(Color.YELLOW);
        leftEye.setFill(Color.YELLOW);

        Group spikes = new Group();

        for(int i= 0; i < 6; i++){
            // 360 / 6 = 60, we place spikes with 60 degrees interval
            double angleInRadians = Math.toRadians(i * 60);

            Rectangle spike = new Rectangle(-1, -5, 2, 8);
            spike.setFill(Color.RED);
            spike.setTranslateX(Math.cos(angleInRadians) * 30);
            spike.setTranslateY(Math.sin(angleInRadians) * 30);
            // Make the spike diagonal
            spike.setRotate(i * 60 + 90);

            spikes.getChildren().add(spike);
        }

        // This transparent circle both acts as a hitbox and avoids jittering of the center circle when the spikes spin.
        Circle transparentCircle = new Circle(40,Color.TRANSPARENT);
        spikes.getChildren().add(transparentCircle);

        // Only rotate the spikes
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), spikes);
        rotateTransition.setByAngle(360);
        // Avoid ease-in-out effect when spinning
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();


        Group group = new Group();
        group.getChildren().addAll(spikes, face, leftEye, rightEye);

        return group;
    }
}
