package com.group1.groupproject;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Hunter extends Entity {
    public Group implementView(){
        Group group = new Group();
        Circle circle = new Circle(15);
        circle.setFill(Color.YELLOW);

        // TODO: implement triangle

        group.getChildren().addAll(circle);

        return group;
    }

    public Hunter(double x, double y) {
        super(x, y, true);
    }

    @Override
    public void move(double minX, double maxX, double minY, double maxY) {
        // TODO: implement WASD Movement
    }
}
