package com.group1.groupproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

// We use Pane instead of StackPane because StackPane allows you to set margins whereas Pane doesn't.
public class GameScreen extends StackPane {
    Label scoreText = new Label("Score: 0");
    Label timeText = new Label("00:00");
    BarPane vacuumBar = new BarPane("VACUUM:", Color.rgb(157,0,255, 1));
    BarPane healthBar = new BarPane("HEALTH:", Color.rgb(255,0,0, 1));

    int score = 0;
    double healthPercentage = 1.0;
    double vacuumPercentage = 1.0;

    public GameScreen(PlayableArea playableArea){
        scoreText.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: black; -fx-font-size: 32px; -fx-font-weight: bold;");
        timeText.setStyle("-fx-font-family: 'Chiller'; -fx-text-fill: white; -fx-font-size: 16px;");

        // TODO: Image placeholder background
        this.setStyle("-fx-background-color: turquoise");

        VBox textBox = new VBox();
        textBox.getChildren().addAll(scoreText, timeText);
        textBox.setAlignment(Pos.CENTER);

        vacuumBar.setAlignment(Pos.TOP_LEFT);
        healthBar.setAlignment(Pos.TOP_RIGHT);
        textBox.setAlignment(Pos.TOP_CENTER);

        this.setMargin(vacuumBar, new Insets(10,0 ,0,20));
        this.setMargin(healthBar, new Insets(10, 20, 0, 0));
        this.setMargin(textBox, new Insets(25, 0, 0, 0));

        this.getChildren().addAll(playableArea, vacuumBar, healthBar, textBox);
    }

    public void updateVacuum(double percentage){
        vacuumBar.setFill(percentage);
        vacuumPercentage = percentage;
    }

    public void updateHealth(double percentage){
        healthBar.setFill(percentage);
        healthPercentage = percentage;
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
}
