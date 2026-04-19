package myjavaFXprograms;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TokenManager {
	
	private PlayableArea area;
	private Random random;
	
	public TokenManager(PlayableArea area) {
		this.area = area;
		this.random = new Random();
	}
	
	//to start spawning the tokens
	public void startSpawning() {
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5) , e -> {
			
			if(Token.getNumberOfToken() < 2)
				spawnRandomToken();
		}));
		
		//continue till the game or level stop
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	private void spawnRandomToken() {
		double x = Randomizer.getX(this.area);
		double y = Randomizer.getY(this.area);
		
		int type = random.nextInt(3);
		Token newToken = null;
		
		switch(type) {
			case 0: newToken = new HealthToken(x , y); break;
			case 1: newToken = new EyeToken(x , y); break;
			case 2: newToken = new RangeToken(x , y);
		}
		
		if(newToken != null)
			this.area.getChildren().add(newToken.getView());
	}
	
	
}
