package com.group1.groupproject;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ButtonPane extends Pane {
	private Label text = new Label();

	public ButtonPane(String a) {
		text.setText(a);
		text.setPrefSize(150, 50);
		text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 24px;");
		text.setAlignment(Pos.CENTER);
		StackPane button = new StackPane();
		button.setStyle("-fx-background-color: purple;");
		button.setPrefSize(150, 50);
		button.getChildren().add(text);
		button.setOnMouseEntered(e -> {
			text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: red; -fx-font-size: 24px;");
			button.setStyle("-fx-background-color: white;");
		});
		button.setOnMouseExited(e -> {
			text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 24px;");
			button.setStyle("-fx-background-color: purple;");
		});
		this.getChildren().add(button);
	}

	public Label getText() {
		return text;
	}

	public void setText(Label text) {
		this.text = text;
	}
}
