import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LosePane extends VBox {

    public LosePane(App app, int score, int levelNum) {        this.setPrefSize(1500, 900);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(40);
        this.setStyle("-fx-background-color: #f4f4f4;");
        Label title = new Label("GAME OVER");
        title.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: red; -fx-font-size: 80px; -fx-font-weight: bold;");
        Label scoreLabel = new Label("FINAL SCORE: " + score);
        scoreLabel.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: red; -fx-font-size: 40px; -fx-font-weight: bold;");
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(30);
        ButtonPane retryBtn = new ButtonPane(0, 0, "RETRY", Color.GRAY);
        retryBtn.setMaxSize(300, 100); 
        retryBtn.getText().setPrefWidth(300); 
        retryBtn.setOnMouseClicked(e -> app.startLevel(levelNum));
        ButtonPane menuBtn = new ButtonPane(0, 0, "MAIN MENU", Color.GRAY);
        menuBtn.setMaxSize(300, 100);
        menuBtn.getText().setPrefWidth(300);        menuBtn.setOnMouseClicked(e -> app.showMainMenu());

        buttonBox.getChildren().addAll(retryBtn, menuBtn);
        
            this.getChildren().addAll(title, scoreLabel, buttonBox);
    }
}