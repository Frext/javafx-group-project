package com.group1.groupproject;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.application.Application;
import javafx.stage.Stage;

public class GhostHunter extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		pane.setPrefSize(800, 600);
		
		ButtonPane startButton = new ButtonPane("START GAME");
		ButtonPane selectLevelButton = new ButtonPane("SELECT LEVEL");
		ButtonPane exitButton = new ButtonPane("EXIT");
		
		pane.getChildren().add(exitButton);
		pane.getChildren().add(selectLevelButton);
		pane.getChildren().add(startButton);
		
		startButton.setLayoutX(100);
		startButton.setLayoutY(400);
		
		selectLevelButton.setLayoutX(100);
		selectLevelButton.setLayoutY(460);
		
		exitButton.setLayoutX(100);
		exitButton.setLayoutY(520);
		
		// Image homeImage = new Image("");
		// ImageView homeImageView = new ImageView(homeImage);
		
		// Image iconImage = new Image("");
		//İcon unutma!!! stage.getIcons().add(iconImage);
		
		Scene homeScene = new Scene(pane);
		stage.setScene(homeScene);
		stage.setTitle("GHOST HUNTER");
		stage.show();
		
	}

}
