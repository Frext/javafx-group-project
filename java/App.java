import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
	private Stage stage;
	private StackPane rootPane;
	
	@Override
	public void init() throws Exception {
		Config.loadConfig();
	}
	
	public void start(Stage stage) {
		this.stage = stage;
		showMainMenu();
		stage.show();
	}
	
	public void showMainMenu() {
		MainMenu mainMenu = new MainMenu(this);
		stage.setScene(new Scene(mainMenu , 1500 , 900));
	}
	
	public void showLevelSelectMenu() {
		LevelSelect levelSelect = new LevelSelect(this);
		stage.setScene(new Scene(levelSelect));
	}
	
	public void startLevel(int levelNum) {
		ImageView background = new ImageView("file:" + levelNum + ".png");
		GameScreen gameScreen = new GameScreen(levelNum , this , background);

		rootPane = new StackPane();
		rootPane.getChildren().add(gameScreen);

		Scene scene = new Scene(rootPane, 1500 , 900);
		stage.setScene(scene);
		
		gameScreen.initLevel(scene);
		gameScreen.startGame();
	}
	
	public void showLoseScene(int score, int levelNum) {
		LosePane losePane = new LosePane(this, score, levelNum);
		stage.setScene(new Scene(losePane, 1500, 900));
	}

	public void showWinScene(int levelNum) {
		WinPane winPane = new WinPane(this, levelNum);
		winPane.setMaxSize(500, 300);

		rootPane.getChildren().add(winPane);
	}

	public void exit(){
		Platform.exit();
	}
	
    public static void main(String[] args) {
    	Config.loadConfig();
        launch(args);
    }
}
