import javafx.scene.Group;
import javafx.scene.Node;

public abstract class Token {//Emir Aydın 150124001
	private static int numberOfToken;
	private double x , y;
	private Group view;

	private Runnable effect;

	public Token(double x , double y, Runnable effect) {
		this.x = x;
		this.y = y;
		this.effect = effect;
		
		this.view = implementView();
		
		view.setLayoutX(x);
        view.setLayoutY(y);
        
        this.numberOfToken++;
	}

	//to apply the token effect
	public void giveEffect(){
		this.consume();

		effect.run();
	}

	//to create the view of token (health , eye and range tokens override this method)
	public abstract Group implementView();
	
	public Node getView(){
	        return view;
	}

	//to dicrement the number of token after collected
	public void consume() {
		this.numberOfToken--;
	}

	//to get the number of token to check if there are two tokens or not
	public static int getNumberOfToken() {
		return numberOfToken;
	}

	//to restart the tokens after the level finished
	public static void flushTokens(){
		numberOfToken = 0;
	}
}
