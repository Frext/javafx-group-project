import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class App extends Application {
	private Stage stage;
	
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
		
		Scene scene = new Scene(gameScreen , 1500 , 900);
		stage.setScene(scene);
		
		gameScreen.initLevel(scene);
		gameScreen.startGame();
	}
	
	public void showLoseScene(int score, int levelNum) {
		LosePane losePane = new LosePane(this, score, levelNum);
		stage.setScene(new Scene(losePane, 1500, 900));
	}

	public void showWinScene(int levelnum) {
		WinPane winPane = new WinPane();
		stage.setScene(new Scene(winPane, 1500, 900));
	}
	
    public static void main(String[] args) {
    	Config.loadConfig();
        launch(args);
    }
}
