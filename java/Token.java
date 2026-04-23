package com.group1.groupproject;

import javafx.scene.Group;
import javafx.scene.Node;

public abstract class Token {
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
	
	public void giveEffect(){
		this.consume();

		effect.run();
	}
	
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
