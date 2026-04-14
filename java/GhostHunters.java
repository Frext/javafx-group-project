package com.group1.groupproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class GhostHunters extends Application {
    @Override
    public void start(Stage stage){
        Group root = new Group();
        Canvas canvas = new Canvas(300, 300);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 300, 300);
        stage.setTitle("Ghost Hunters");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(GhostHunters.class, args);
    }
}
