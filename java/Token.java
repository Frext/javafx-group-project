package myjavaFXprograms;

import javafx.scene.Group;
import javafx.scene.Node;

public abstract class Token {
	
	private static int numberOfToken;
	private double x , y;
	private Group view;
	
	public Token(double x , double y) {
		this.x = x;
		this.y = y;
		
		this.view = implementView();
		
		view.setLayoutX(x);
        view.setLayoutY(y);
        
        this.numberOfToken++;
	}
	
	public abstract void giveEffect();
	
	public abstract Group implementView();
	
	public Node getView(){
	        return view;
	}
	
	public void consume() {
		this.numberOfToken--;
	}
	
	public static int getNumberOfToken() {
		return numberOfToken;
	}
}
