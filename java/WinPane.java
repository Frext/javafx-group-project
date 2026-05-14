import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class WinPane extends VBox{
	// We add 2 labels and 1 button under each other
	WinPane(App app, int levelNum) {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.setPrefSize(500, 300);
		this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
		Label title = new Label("YOU WIN!");
		title.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");
		Label subText = new Label("YOU'VE VACUUMED ALL THE GHOSTS");
		subText.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");
		ButtonPane button = new ButtonPane(0, 200, levelNum == 3 ? "MAIN MENU" : "NEXT LEVEL", Color.PURPLE);
		button.setMaxSize(250, 100);
		button.setStyle("-fx-font-size: 12px");
		button.getText().setMinWidth(250);
		button.setOnMouseClicked(e -> {
			if (levelNum == 3){
				app.showMainMenu();
			} else{
				app.startLevel(levelNum + 1);
			}
		});
		this.getChildren().addAll(title, subText, button);
	}
	
}
