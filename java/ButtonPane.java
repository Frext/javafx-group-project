package myjavaFXprograms;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ButtonPane extends StackPane {
	
	private Label text = new Label();

	public ButtonPane(int x , int y , String s , Color color) {
		
		//to create text
		text.setText(s);
		text.setPrefSize(150, 50);
		text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 24px;");
		text.setAlignment(Pos.CENTER);
		
		//determine the size of button
		this.setPrefSize(150, 50);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.getChildren().add(text);
		
		//determine the color of button
		if(color.equals(Color.PURPLE))
			this.setStyle("-fx-background-color: purple;");
		
		if(color.equals(Color.GRAY)) 
			this.setStyle("-fx-background-color: gray;");
		
		//hover part
		this.setOnMouseEntered(e -> {
			if(color.equals(Color.PURPLE)) {
				text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: red; -fx-font-size: 24px;");
				this.setStyle("-fx-background-color: white;");
			}
			
			if(color.equals(Color.GRAY)) {
				text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 24px;");
				this.setStyle("-fx-background-color: red;");
			}
		});
		
		this.setOnMouseExited(e -> {
			if(color.equals(Color.PURPLE)) {
				text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 24px;");
				this.setStyle("-fx-background-color: purple;");
			}
			
			if(color.equals(Color.GRAY)) {
				text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 24px;");
				this.setStyle("-fx-background-color: gray;");
			}
		});
		
		this.setOnMouseClicked(e -> {
			//TODO: Level Method
		});
		
	}
	
	public ButtonPane(int x , int y , String s , Image image) {
		
		ImageView imageView = new ImageView(image);
		
		//to create text
		text.setText(s);
		text.setPrefSize(150, 50);
		text.setStyle("-fx-font-family: 'serif'; -fx-text-fill: white; -fx-font-size: 48px;");
		text.setAlignment(Pos.CENTER);
		
		imageView.setFitWidth(60);
		imageView.setFitHeight(24);
		
		//determine the size of button
		this.setPrefSize(75, 75);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.getChildren().add(imageView);
		this.getChildren().add(text);
		this.setStyle("-fx-background-color: purple;");
		
		//hover part
		this.setOnMouseEntered(e -> {
			this.setStyle("-fx-background-color: orchid;");
		});
		
		this.setOnMouseExited(e -> {
			this.setStyle("-fx-background-color: purple;");
		});
		
		//click part
		this.setOnMouseClicked(e -> {
			this.setStyle("-fx-background-color: white;");
			//TODO: Level Method
		});
		
	}
	
	public Label getText() {
		return text;
	}

	public void setText(Label text) {
		this.text = text;
	}
}
