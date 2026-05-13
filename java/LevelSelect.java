import javafx.scene.layout.Pane;
import javafx.scene.image.Image;

public class LevelSelect extends Pane{
	private App app;
	
	public LevelSelect(App app) {
		
		this.app = app;
		
		this.setStyle("-fx-background-image: url('file:level_select_background.png'); " + "-fx-background-size: cover;");
		this.setPrefSize(1500, 900);
		
		ButtonPane level1 = new ButtonPane(650 , 150 , "1" , new Image("file:1.png"));
		ButtonPane level2 = new ButtonPane(650 , 325 , "2" , new Image("file:2.png"));
		ButtonPane level3 = new ButtonPane(650 , 500 , "3" , new Image("file:3.png"));
		
		level1.setOnMouseClicked(e -> app.startLevel(1));
		level2.setOnMouseClicked(e -> app.startLevel(2));
		level3.setOnMouseClicked(e -> app.startLevel(3));
		
		this.getChildren().addAll(level1 , level2 , level3);
	}
}
