package com.group1.groupproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainMenu extends Pane{

	public MainMenu() {
		Pane pane = new Pane();
		pane.setPrefSize(800, 600);
		
		ButtonPane startButton = new ButtonPane(100, 400, "START GAME", Color.PURPLE);
		ButtonPane selectLevelButton = new ButtonPane(100, 460, "SELECT LEVEL", Color.PURPLE);
		ButtonPane exitButton = new ButtonPane(100, 520, "EXIT", Color.PURPLE);
		
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
		
		this.getChildren().add(pane);
	}
}
