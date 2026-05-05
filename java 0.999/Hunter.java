package myjavaFXprograms;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
public class Hunter extends Entity {
	
	private boolean isWPressed;
	private boolean isAPressed;
	private boolean isSPressed;
	private boolean isDPressed;

	private boolean isSpacePressed;
	private double speed = 1.5;
	private Polygon triangle;
	private Rotate scannerRotate;
	private Circle circle;
	private int time;
	private ColorAdjust adjust = new ColorAdjust();

	//to create the hunter view
    public Group implementView(){
        Group group = new Group();
        circle = new Circle(15);
        circle.setFill(Color.ORANGE);

        triangle = new Polygon();

        // Triangle points right which is 0 degree
        triangle.getPoints().addAll(0.0 ,0.0 , 100.0, -30.0, 100.0, 30.0);
        triangle.setVisible(false);
		triangle.setFill(Color.RED);
        triangle.setOpacity(.5);
        // Instead of rotating the whole group , to just rotated the triangle.
        scannerRotate = new Rotate(0 , 0 , 0);
        triangle.getTransforms().add(scannerRotate);
        
        group.getChildren().addAll(triangle, circle);

        return group;
    }

    public Hunter(double x, double y , Scene scene) {
        super(x, y, true);

        setupMovementControl(scene);     
    }
    
    //to determine which key is pressed
    private void setupMovementControl(Scene scene) {
    	scene.setOnKeyPressed(e -> {
    		switch(e.getCode()) {
    			case KeyCode.W: isWPressed = true;isSPressed = false; break;
    			case KeyCode.A: isAPressed = true;isDPressed = false; break;
    			case KeyCode.S: isSPressed = true;isWPressed = false; break;
    			case KeyCode.D: isDPressed = true;isAPressed = false; break;
    			case KeyCode.SPACE: isSpacePressed = true; break;
    		}
    	});
    	
    	scene.setOnKeyReleased(e -> {
    		switch(e.getCode()) {
    			case KeyCode.W: isWPressed = false; break;
    			case KeyCode.A: isAPressed = false; break;
    			case KeyCode.S: isSPressed = false; break;
    			case KeyCode.D: isDPressed = false; break;
    			case KeyCode.SPACE: isSpacePressed = false; break;
    		}
    	});
    	
    	scene.setOnMouseMoved(e -> {
    		double mouseX = e.getSceneX();
    		double mouseY = e.getSceneY();
    		
    		double Dx = mouseX - this.x;
    		double Dy = mouseY - this.y;
    		//we used atan2 instead of atan , because to avoid Arithmetic problem (dividing by 0) and to calculate angle properly (negative / negative = positive cause the wrong angle)  
    		double angle = Math.toDegrees(Math.atan2(Dy, Dx));
    		scannerRotate.setAngle(angle);
    	});
    }
    
    @Override
    public void move(double minX, double minY, double maxX, double maxY) {
        
    	double tempX = this.x;
    	double tempY = this.y;
    	
    	//to increment or decrement the temporary x and y coordinates
    	if(isWPressed)
    		tempY -= speed;
    	if(isAPressed)
    		tempX -= speed;
    	if(isSPressed)
    		tempY += speed;
    	if(isDPressed)
    		tempX += speed;
    	
    	//to check these temporary x and y coordinates if these are in the playable area !! OUR RADIUS IS 15
    	if(tempX - 15 < minX)
    		tempX = minX + 15;
    	if(tempY - 15 < minY)
    		tempY = minY + 15;
    	if(tempX + 15 > maxX)
    		tempX = maxX - 15;
    	if(tempY + 15> maxY)
    		tempY = maxY - 15;
    	
    	//to assign the temporary coordinates to real coordinates
    	this.x = tempX;
    	this.y = tempY;
    	
    	this.view.setLayoutX(this.x);
    	this.view.setLayoutY(this.y);
    }
    public Bounds vacuumArea() {
		return this.triangle.localToScene(this.triangle.getBoundsInLocal());
	}
    public Bounds hunterBounds() {
		return this.circle.localToScene(this.circle.getBoundsInLocal());
	}
    public void applyDamageEffect(boolean isTakingDamage) {
    	if (isTakingDamage) {
			time++;
			if (time % 20 < 10) {
				adjust.setBrightness(1.0);
				circle.setEffect(adjust);
			} else {
				circle.setEffect(null);
			}
		} else {
			circle.setEffect(null);
		}
	}

	public boolean wantsToVacuum(){
		return isSpacePressed;
	}

	public void enableVacuumEffect(){
		triangle.setVisible(true);
	}

	public void disableVacuumEffect(){
		triangle.setVisible(false);
	}

	public void increaseVacuumRange(double increaseAmount){
		// The coordinates below are the original coordinates of the triangle
		// (0.0 ,0.0 , 100.0, -30.0, 100.0, 30.0)

		// Get 100.0 in the x coordinate from index 2.
		double baseX = triangle.getPoints().get(2);
		double newX = baseX + increaseAmount;

		// Keep the ratio between x and y.
		double newY = newX * 0.3;

		// Set new points
		triangle.getPoints().setAll(0.0, 0.0, newX, -newY, newX, newY);
	}
}
