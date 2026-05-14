import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class GameScreen extends StackPane {
    
    private Label scoreText = new Label("SCORE: 0");
    private Label timeText = new Label("00:00");
    private BarPane vacuumBar = new BarPane("VACUUM", Color.rgb(157,0,255, 1));
    private BarPane healthBar = new BarPane("HEALTH", Color.rgb(255,0,0, 1));
    
    private App appManager;
    private int levelNumber;
    private PlayableArea area;
    private Hunter hunter;
    private EnemyManager enemyManager;
    private TokenManager tokenManager;
    private AnimationTimer gameLoop;
    
    private double targetHealth = 1.0;
    private double currentHealth = 1.0;
    private double currentVacuum = 1.0;

    private final double vacuumDecrease = Config.get("vacuum_decrease");
    private final double vacuumIncrease = Config.get("vacuum_increase");
    private final double VACUUM_THRESHOLD = 0.01;
    private boolean isVacuumInTimeout = false;
    private static boolean isEyeOn = false;
    private int score = 0;
    private int levelTimeLimit;

    public GameScreen(int levelNumber, App appManager, ImageView backgroundImage) {
        this.appManager = appManager;
        this.levelNumber = levelNumber;

        this.levelTimeLimit = Config.get("level_" + levelNumber + "_time");
        
        area = new PlayableArea(Config.get("level_" + levelNumber + "_playable_area_x") , Config.get("level_" + levelNumber + "_playable_area_y") , Config.get("level_" + levelNumber + "_playable_area_width") , Config.get("level_" + levelNumber + "_playable_area_height"));

        enemyManager = new EnemyManager(levelNumber, area);
        
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

        this.getChildren().addAll(backgroundImage, area, vacuumBar, healthBar, textBox);
    }

    public void initLevel(Scene scene) {
    	
        hunter = new Hunter((area.getMinX() + area.getMaxX()) / 2, (area.getMinY() + area.getMaxY()) / 2, scene);
        area.getChildren().add(hunter.getView());

        setupTokenManager();
        setupGameLoop();
    }

    private void setupTokenManager() {
        tokenManager = new TokenManager(area, hunter.getView(),
                () -> { targetHealth += Config.get("health_token_increase") / 100.0; } ,
                
                () -> {
                    PauseTransition pause = new PauseTransition(Duration.seconds(Config.get("eye_token_duration")));
                    pause.setOnFinished(e -> {//to avoid name conflict between event e and entity e , we used entity instead of e
                        for(Entity entity : enemyManager.getEnemies()) {
                            entity.getView().setVisible(false);
                        }
                        setIsEyeOn(false);
                    });
                    for(Entity entity : enemyManager.getEnemies()) {
                    	entity.getView().setVisible(true);
                    }
                    setIsEyeOn(true);
                    pause.play();
                } ,
                
                () -> {
                    PauseTransition pause = new PauseTransition(Duration.seconds(10));
                    pause.setOnFinished(e -> hunter.increaseVacuumRange(-Config.get("vacuum_token_increase")));
                    hunter.increaseVacuumRange(Config.get("vacuum_token_increase"));
                    pause.play();
                }
        );
    }

    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            long startTime = -1;

            @Override
            public void handle(long now) {
                if (startTime < 0) startTime = now;
                int elapsedSeconds = (int) ((now - startTime) / 1_000_000_000);
                int remainingTime = levelTimeLimit - elapsedSeconds;
                
                hunter.move(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
                enemyManager.moveAll(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());

                if (currentVacuum <= VACUUM_THRESHOLD && !isVacuumInTimeout){
                    isVacuumInTimeout = true;
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e -> isVacuumInTimeout = false);
                    pause.play();
                }

                if(hunter.wantsToVacuum() && currentVacuum > VACUUM_THRESHOLD && !isVacuumInTimeout){
                    hunter.enableVacuumEffect();
                    
                    score = Collision.handleVacuum(enemyManager.getEnemies(), hunter, area, score);
                    updateScore(score);
                    currentVacuum -= vacuumDecrease * 0.0025;
                } else {
                    hunter.disableVacuumEffect();
                    currentVacuum += vacuumIncrease * 0.0025;
                    
                    
                    if(!isEyeOn) {
                        for(Entity e : enemyManager.getEnemies()) {
                            ((Enemy)e).applyScannerEffect(false); 
                        }
                    }
                }
                updateVacuum(currentVacuum);
                
                targetHealth = Health.damage(targetHealth, hunter, enemyManager.getEnemies());

                // If they are really close, set it to targetHealth to avoid jittering
                if (Math.abs(targetHealth - currentHealth) < 0.01)
                    updateHealth(targetHealth);
                    // We can add or subtract little by little to the health, that will create a smooth transition because AnimationTimer runs 60 fps
                else if (targetHealth > currentHealth) {
                    updateHealth(currentHealth + 0.01);
                } else{
                    updateHealth(currentHealth - 0.01);
                }

                tokenManager.checkTokenCollision();

                // If we don't do this, once you start vacuuming an entity, the other entities disappear.
                if (isEyeOn){
                    for(Entity enemy : enemyManager.getEnemies()){
                        enemy.getView().setVisible(true);
                    }
                }
                
                if(remainingTime >= 0) {
                    timeText.setText(String.format("%02d:%02d", remainingTime / 60, remainingTime % 60));
                }

                if (targetHealth <= 0 || remainingTime <= 0) {
                    stopGame();
                    appManager.showLoseScene(score, levelNumber);
                } else if (enemyManager.getEnemies().isEmpty()) {
                    stopGame();
                    appManager.showWinScene(levelNumber);
                }
            }
        };
    }
    public static void setIsEyeOn(boolean isEyeOn) {
	    GameScreen.isEyeOn = isEyeOn;
    }

    public void updateVacuum(double percentage){
        currentVacuum = Math.clamp(percentage, 0.0, 1.0);
        vacuumBar.setFill(currentVacuum);
    }

    public void updateHealth(double percentage){
        currentHealth = Math.clamp(percentage, 0.0, 1.0);
        healthBar.setFill(currentHealth);
    }
    
    public void updateScore(int score){
        scoreText.setText("SCORE: " + score);
    }
    
    public void startGame() {
        gameLoop.start();
        tokenManager.startSpawning();
    }
    
    public void stopGame() {
        gameLoop.stop();
        tokenManager.stopSpawning();
        tokenManager.flush();
    }
   
}