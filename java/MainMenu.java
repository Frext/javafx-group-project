import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainMenu extends Pane{
	
	private App app;
	
	public MainMenu(App app) {//to create teh main menu Emir Aydın 150124001

		//we took the main class (App) as a parameter because main class keeps the stage and we need it to change the scene
		this.app = app;
		
		this.setStyle("-fx-background-image: url('file:main.png'); " + "-fx-background-size: cover;");
		
		ButtonPane start = new ButtonPane(325 , 325 , "Start" , Color.PURPLE);
		ButtonPane selectLevel = new ButtonPane(325 , 450 , "Select Level" , Color.PURPLE);
		ButtonPane exit = new ButtonPane(325 , 575 , "Exit" , Color.PURPLE);
		
		start.setOnMouseClicked(e -> app.startLevel(1));
		
		selectLevel.setOnMouseClicked(e -> app.showLevelSelectMenu());

		exit.setOnMouseClicked(e -> app.exit());
		
		this.getChildren().addAll(start , selectLevel , exit);
		
	}
}
