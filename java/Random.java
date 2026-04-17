package myjavaFXprograms;

public class Random {
	
	public static int getX(PlayableArea area) {
		
		int x = (int)((Math.random() * (area.getMaxX() - area.getMinX())) + area.getMinX());
		return x;
	}
	
	public static int getY(PlayableArea area) {
		
		int y = (int)((Math.random() * (area.getMaxY() - area.getMinY())) + area.getMinY());
		return y;
	}
}
