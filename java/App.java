package com.group1.groupproject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void init() throws Exception {
        super.init();

        Config.loadConfig();
    }

    @Override
    public void start(Stage stage) {
        // TODO: Replace with below line to see main menu
        // MainMenu screen = new MainMenu();

        // Create playableArea according to the config parameters
        PlayableArea area = new PlayableArea(Config.get("level_1_playable_area_x"),
                Config.get("level_1_playable_area_y"),
                Config.get("level_1_playable_area_width"),
                Config.get("level_1_playable_area_height"));


        GameScreen screen = new GameScreen(area);
        screen.updateVacuum(Config.get("maximum_vacuum"));
        screen.updateHealth(Config.get("maximum_health"));

        Scene scene = new Scene(screen, 1500, 900);
        stage.setScene(scene);
        stage.setTitle("Ghost Hunter Inc.");

        Hunter hunter = new Hunter((area.getMinX() + area.getMaxX()) / 2, (area.getMinY() + area.getMaxY()) / 2,
                scene);
        Ripper ripper = new Ripper(Randomizer.getX(area), Randomizer.getY(area), true);
        Wisp wisp = new Wisp(Randomizer.getX(area), Randomizer.getY(area), true);
        Ghost ghost = new Ghost(Randomizer.getX(area), Randomizer.getY(area), true);

        area.getChildren().addAll(hunter.getView(), ripper.getView(), wisp.getView(), ghost.getView());

        AnimationTimer time = new AnimationTimer() {
            long startTime = -1;

            @Override
            public void handle(long now) {
                if (startTime < 0){
                    startTime = now;
                }

                hunter.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
                ripper.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
                wisp.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
                ghost.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());

                screen.updateScore(Collision.handleVacuum(hunter, ghost, ripper, wisp, area, screen.getScore()));
                screen.updateHealth(Health.damage(screen.getHealth(), hunter, ghost, ripper, wisp, area));

                // Convert nanoseconds to seconds
                screen.updateTime((int)((now-startTime) / 1000000000));
            }
        };

        time.start();
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
