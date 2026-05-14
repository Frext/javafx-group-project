import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class TokenManager {
	private PlayableArea area;
	private Random random;

	private Node playerNode;

	private ArrayList<Token> tokens = new ArrayList<>();

	private Runnable healthEffect;
	private Runnable eyeEffect;
	private Runnable rangeEffect;

	private Timeline timeline;
	
	public TokenManager(PlayableArea area, Node player, Runnable healthEffect, Runnable eyeEffect, Runnable rangeEffect) {
		this.area = area;
		this.random = new Random();
		this.playerNode = player;

		this.healthEffect = healthEffect;
		this.eyeEffect = eyeEffect;
		this.rangeEffect = rangeEffect;
	}
	
	//to start spawning the tokens
	public void startSpawning() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(5) , e -> {
			
			if(Token.getNumberOfToken() < 2)
				spawnRandomToken();
		}));
		
		//continue till the game or level stop
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	public void stopSpawning(){
		if (timeline != null){
			timeline.stop();
		}
	}
	
	private void spawnRandomToken() {
		double x = Randomizer.getX(this.area);
		double y = Randomizer.getY(this.area);
		
		int type = random.nextInt(3);
		Token newToken;

		switch(type) {
			case 0: newToken = new HealthToken(x , y, healthEffect); break;
			case 1: newToken = new EyeToken(x , y, eyeEffect); break;
			default: newToken = new RangeToken(x , y, rangeEffect);
		}


		// Make room for the new token in the list.
		while (tokens.size() >= 2){
			// Remove it from the list.
			System.out.print("Remove");
			tokens.removeLast();
		}

		tokens.addFirst(newToken);
		this.area.getChildren().add(newToken.getView());
	}

	public void checkTokenCollision(){
		var iterator = tokens.iterator();

		// If we don't use an iterator we get ConcurrentModificationException.

		while(iterator.hasNext()){
			Token token = iterator.next();

			if(token.getView().getBoundsInParent().intersects(this.playerNode.getBoundsInParent())){
				// Apply token effects
				token.giveEffect();

				// DO NOT call token.consume() it's already invoked in token.giveEffect();

				// Remove from UI
				this.area.getChildren().remove(token.getView());

				// Remove from the list safely without getting an exception
				iterator.remove();
			}
		}
	}

	public void flush(){
		tokens = new ArrayList<>();
		Token.flushTokens();
	}
}
