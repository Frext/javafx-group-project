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
		
		//to create text Abdullah Derviş Kombıçak 150124009 Emir Aydın 150124001
		text.setText(s);
		text.setPrefSize(150, 50);
		text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 40px;");
		text.setAlignment(Pos.CENTER);
		
		//determine the size of button
		this.setPrefSize(250, 100);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.getChildren().add(text);
		
		//determine the color of button
		if(color.equals(Color.PURPLE))
			this.setStyle("-fx-background-color: purple;");
		
		if(color.equals(Color.GRAY)) 
			this.setStyle("-fx-background-color: gray;");
		
		//hover part Abdullah Derviş Kombıçak 150124009
		this.setOnMouseEntered(e -> {
			if(color.equals(Color.PURPLE)) {
				text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: red; -fx-font-size: 40px;");
				this.setStyle("-fx-background-color: white;");
			}
			
			if(color.equals(Color.GRAY)) {
				text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 40px;");
				this.setStyle("-fx-background-color: red;");
			}
		});
		
		this.setOnMouseExited(e -> {
			if(color.equals(Color.PURPLE)) {
				text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 40px;");
				this.setStyle("-fx-background-color: purple;");
			}
			
			if(color.equals(Color.GRAY)) {
				text.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 40px;");
				this.setStyle("-fx-background-color: gray;");
			}
		});
		
	}

	//for the level select putton Abdullah Derviş Kombıçak 150124009 Emir Aydın 150124001
	public ButtonPane(int x , int y , String s , Image image) {
		
		ImageView imageView = new ImageView(image);
		
		//to create text 
		text.setText(s);
		text.setPrefSize(150, 50);
		text.setStyle("-fx-font-family: 'serif'; -fx-text-fill: white; -fx-font-size: 48px;");
		text.setAlignment(Pos.CENTER);
		
		imageView.setFitWidth(120);
		imageView.setFitHeight(50);
		
		//determine the size of button
		this.setPrefSize(150, 150);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.getChildren().add(imageView);
		this.getChildren().add(text);
		this.setStyle("-fx-background-color: purple;");
		
		//hover part Abdullah Derviş Kombıçak 150124009
		this.setOnMouseEntered(e -> {
			this.setStyle("-fx-background-color: orchid;");
		});
		
		this.setOnMouseExited(e -> {
			this.setStyle("-fx-background-color: purple;");
		});
		
		//click part Abdullah Derviş Kombıçak 150124009
		this.setOnMouseClicked(e -> {
			this.setStyle("-fx-background-color: white;");
		});
		
	}
	
	public Label getText() {
		return text;
	}

	public void setText(Label text) {
		this.text = text;
	}
}
