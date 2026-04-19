import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class AdkDeneme extends Application {
	private int score = 0;
	private double health = 100.0;
	Label scoreLabel = new Label("Score: " + score);
	Label healthLabel = new Label("Health: " + (int)health);

	@Override
	public void start(Stage stage) throws Exception {
		scoreLabel.setStyle(
				"-fx-font-family: 'Cherif'; -fx-text-fill: black; -fx-font-size: 36px; -fx-font-weight: bold;");
		healthLabel.setStyle(
				"-fx-font-family: 'Cherif'; -fx-text-fill: black; -fx-font-size: 36px; -fx-font-weight: bold;");
		PlayableArea area = new PlayableArea(Config.get("level_1_playable_area_x"),
				Config.get("level_1_playable_area_y"), Config.get("level_1_playable_area_width"),
				Config.get("level_1_playable_area_height"));
		Scene gameScene = new Scene(area, 1500, 900);
		area.setStyle("-fx-background-color: turquoise");
		Hunter hunter = new Hunter((area.getMinX() + area.getMaxX()) / 2, (area.getMinY() + area.getMaxY()) / 2,
				gameScene);
		Ripper ripper = new Ripper(Randomizer.getX(area), Randomizer.getY(area), true);
		Wisp wisp = new Wisp(Randomizer.getX(area), Randomizer.getY(area), true);
		Ghost ghost = new Ghost(Randomizer.getX(area), Randomizer.getY(area), true);
		
		area.getChildren().addAll(hunter.getView(), ripper.getView(), wisp.getView(), ghost.getView());
		area.getChildren().add(scoreLabel);
		area.getChildren().add(healthLabel);
		
		scoreLabel.setLayoutX(((area.getMinX() + area.getMaxX()) / 2) - 10);
		scoreLabel.setLayoutY(10);
		
		healthLabel.setLayoutX(10);
		healthLabel.setLayoutY(10);
		
		AnimationTimer time = new AnimationTimer() {

			@Override
			public void handle(long now) {
				hunter.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
				ripper.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
				wisp.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
				ghost.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
				
				score = Collision.handleVacuum(hunter, ghost, ripper, wisp, area, score);
				scoreLabel.setText("Score: " + score);
				
				health = Health.damage(health, hunter, ghost, ripper, wisp, area);
				healthLabel.setText("Health: " + (int)health);
			}
		};
		time.start();

		stage.setScene(gameScene);
		stage.show();
	}

	public static void main(String[] args) {
		Config.loadConfig();
		launch(args);
	}
}
