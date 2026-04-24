
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    private double targetHealth = 1.0;
    private boolean isEyeOn = false;
    private ArrayList<Enemy> enemies = new ArrayList<>();
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


        GameScreen screen = new GameScreen(area, new ImageView("file:" + "1.png"));
        screen.updateVacuum(Config.get("maximum_vacuum"));
        screen.updateHealth(Config.get("maximum_health"));

        Scene scene = new Scene(screen, 1500, 900);
        stage.setScene(scene);
        stage.setTitle("Ghost Hunter Inc.");

        Hunter hunter = new Hunter((area.getMinX() + area.getMaxX()) / 2, (area.getMinY() + area.getMaxY()) / 2,
                scene);
        Ripper ripper = new Ripper(Randomizer.getX(area), Randomizer.getY(area), false);
        Wisp wisp = new Wisp(Randomizer.getX(area), Randomizer.getY(area), false);
        Ghost ghost = new Ghost(Randomizer.getX(area), Randomizer.getY(area), false);
        enemies.add(ripper);
        enemies.add(ghost);
        enemies.add(wisp);
        area.getChildren().addAll(hunter.getView(), ripper.getView(), wisp.getView(), ghost.getView());

        TokenManager tokenManager = new TokenManager(area, hunter.getView(),
                () -> {
                    targetHealth += Config.get("health_token_increase") / 100.0;
                },
                () -> {
                    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(Config.get("eye_token_duration")));
                    pauseTransition.setOnFinished(event -> {
                        ripper.getView().setVisible(false);
                        wisp.getView().setVisible(false);
                        ghost.getView().setVisible(false);

                        isEyeOn = false;
                    });

                    ripper.getView().setVisible(true);
                    wisp.getView().setVisible(true);
                    ghost.getView().setVisible(true);

                    isEyeOn = true;

                    pauseTransition.play();
                },  () -> {
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(10));
                pauseTransition.setOnFinished(event -> {
                    // Take back the increase after some seconds
                    hunter.increaseVacuumRange(-Config.get("vacuum_token_increase"));
                });

                hunter.increaseVacuumRange(Config.get("vacuum_token_increase"));

                pauseTransition.play();
        });

        AnimationTimer time = new AnimationTimer() {
            final double VACUUM_THRESHOLD = 0.01;
            long startTime = -1;
            boolean isVacuumInTimeout = false;

            @Override
            public void handle(long now) {
                if (startTime < 0){
                    startTime = now;
                }

                hunter.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
                ripper.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
                wisp.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
                ghost.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());

                // If vacuum is below an amount the vacuuming should be disabled for a certain amount of time to prevent jittery vacuum.
                if (screen.getVacuum() <= VACUUM_THRESHOLD && !isVacuumInTimeout){
                    isVacuumInTimeout = true;

                    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
                    pauseTransition.setOnFinished(event -> {
                        isVacuumInTimeout = false;
                    });

                    pauseTransition.play();
                }
                // If the vacuum is not in timeout and the hunter wants to vacuum, turn on the effects.
                if(hunter.wantsToVacuum() && screen.getVacuum() > VACUUM_THRESHOLD && !isVacuumInTimeout){
                    hunter.enableVacuumEffect();
                    screen.updateScore(Collision.handleVacuum(enemies, hunter, area, screen.getScore()));
                    screen.updateVacuum(screen.getVacuum() - 0.008);
                } else{
                    hunter.disableVacuumEffect();
                    screen.updateVacuum(screen.getVacuum() + 0.001);

                    ripper.applyScannerEffect(false);
                    wisp.applyScannerEffect(false);
                    ghost.applyScannerEffect(false);
                }

                // Convert nanoseconds to seconds
                screen.updateTime((int)((now-startTime) / 1000000000));

                // Set health with animation
                double currentHealth = screen.getHealth();
                targetHealth = Health.damage(targetHealth, hunter, ghost, ripper, wisp, area);

                // If they are really close, set it to targetHealth to avoid jittering
                if (Math.abs(targetHealth - currentHealth) < 0.01)
                    screen.updateHealth(targetHealth);
                // We can add or subtract little by little to the health, that will create a smooth transition because AnimationTimer runs 60 fps
                else if (targetHealth > currentHealth) {
                    screen.updateHealth(currentHealth + 0.01);
                } else{
                    screen.updateHealth(currentHealth - 0.01);
                }

                tokenManager.checkTokenCollision();

                // If we don't do this, once you start vacuuming an entity, the other entities disappear.
                if (isEyeOn){
                    ripper.getView().setVisible(true);
                    wisp.getView().setVisible(true);
                    ghost.getView().setVisible(true);
                }

                if (targetHealth <= 0){
                    this.stop();

                    // TODO: Show death screen
                }
            }
        };

        time.start();
        tokenManager.startSpawning();

        stage.show();
    }

    public static void main(String[] args) {
    	Config.loadConfig();
        launch(args);
    }
}
