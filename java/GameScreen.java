package com.group1.groupproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

// We use Pane instead of StackPane because StackPane allows you to set margins whereas Pane doesn't.
public class GameScreen extends StackPane {
    Label scoreText = new Label("SCORE: 0");
    Label timeText = new Label("00:00");
    BarPane vacuumBar = new BarPane("VACUUM", Color.rgb(157,0,255, 1));
    BarPane healthBar = new BarPane("HEALTH", Color.rgb(255,0,0, 1));

    int score = 0;
    double healthPercentage = 1.0;
    double vacuumPercentage = 1.0;

    public GameScreen(PlayableArea playableArea, ImageView backgroundImage){
        scoreText.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold;");
        timeText.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 28px; -fx-font-weight: bold;");

        backgroundImage.fitWidthProperty().bind(this.widthProperty());
        backgroundImage.fitHeightProperty().bind(this.heightProperty());

        VBox textBox = new VBox();
        textBox.getChildren().addAll(scoreText, timeText);
        textBox.setAlignment(Pos.CENTER);

        vacuumBar.setAlignment(Pos.TOP_LEFT);
        healthBar.setAlignment(Pos.TOP_RIGHT);
        textBox.setAlignment(Pos.TOP_CENTER);

        this.setMargin(vacuumBar, new Insets(10,0 ,0,20));
        this.setMargin(healthBar, new Insets(10, 20, 0, 0));
        this.setMargin(textBox, new Insets(25, 0, 0, 0));

        this.getChildren().addAll(backgroundImage, playableArea, vacuumBar, healthBar, textBox);
    }

    public void updateVacuum(double percentage){
        vacuumBar.setFill(Math.clamp(percentage, 0.0, 1.0));
        vacuumPercentage = Math.clamp(percentage, 0.0, 1.0);
    }

    public void updateHealth(double percentage){
        healthBar.setFill(Math.clamp(percentage, 0.0, 1.0));
        healthPercentage = Math.clamp(percentage, 0.0, 1.0);
    }

    public void updateScore(int score){
        scoreText.setText("SCORE: " + score);
        this.score = score;
    }

    public void updateTime(int seconds){
        // Show minutes and seconds in MM:SS format
        timeText.setText(String.format("%02d:%02d", seconds / 60, seconds % 60));
    }

    public int getScore(){
        return score;
    }

    public double getHealth(){
        return healthPercentage;
    }

    public double getVacuum() {
        return vacuumPercentage;
    }
}
