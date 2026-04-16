package com.group1.groupproject;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ghost extends Entity implements Enemy{
    public Ghost(double x, double y, boolean isVisible) {
        super(x, y, isVisible);
    }

    @Override
    public void applyScannerEffect(boolean isInsideTriangle) {
        // TODO
    }

    @Override
    public void move(double minX, double maxX, double minY, double maxY) {
        // TODO
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
