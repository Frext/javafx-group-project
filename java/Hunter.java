package com.group1.groupproject;

import javafx.scene.Group;
import javafx.scene.Scene;
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
	private double speed = 1.5;
	private Polygon triangle;
	private Rotate scannerRotate;
	
	//to create the hunter view
    public Group implementView(){
        Group group = new Group();
        Circle circle = new Circle(15);
        circle.setFill(Color.ORANGE);

        triangle = new Polygon();

        // Triangle points right which is 0 degree
        triangle.getPoints().addAll(0.0 ,0.0 , 100.0, -30.0, 100.0, 30.0);
        triangle.setFill(null);
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
    			case KeyCode.W: isWPressed = true; break;
    			case KeyCode.A: isAPressed = true; break;
    			case KeyCode.S: isSPressed = true; break;
    			case KeyCode.D: isDPressed = true; break;
    			case KeyCode.SPACE: triangle.setFill(Color.RED);
    		}
    	});
    	
    	scene.setOnKeyReleased(e -> {
    		switch(e.getCode()) {
    			case KeyCode.W: isWPressed = false; break;
    			case KeyCode.A: isAPressed = false; break;
    			case KeyCode.S: isSPressed = false; break;
    			case KeyCode.D: isDPressed = false; break;
    			case KeyCode.SPACE: triangle.setFill(null);
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
    public void move(double minX, double maxX, double minY, double maxY) {
        
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
    	
    	//to check these temporary x and y coordinates if these are in the playable area
    	if(tempX < minX)
    		tempX = minX;
    	if(tempY< minY)
    		tempY = minY;
    	if(tempX > maxX)
    		tempX = maxX;
    	if(tempY > maxY)
    		tempY = maxY;
    	
    	//to assign the temporary coordinates to real coordinates
    	this.x = tempX;
    	this.y = tempY;
    	
    	this.view.setLayoutX(this.x);
    	this.view.setLayoutY(this.y);
    }
}
