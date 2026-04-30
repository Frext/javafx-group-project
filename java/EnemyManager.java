package myjavaFXprograms;

import java.util.ArrayList;

public class EnemyManager {
	
	private ArrayList<Entity> enemies;
	private PlayableArea area;
	
	public EnemyManager(int levelNum , PlayableArea area) {
		this.area = area;
		this.enemies = new ArrayList<>();
		
		//getting the numbers of entities from Config class
		int ghostNum = Config.get("level_" + levelNum + "_ghosts");
		int ripperNum = Config.get("level_" + levelNum + "_rippers");
		int wispNum = Config.get("level_" + levelNum + "_wisps");
		
		//spawning the entities regarding their number
		for(int i = 0 ; i < ghostNum ; i++) {
			Ghost ghost = new Ghost(Randomizer.getX(area) , Randomizer.getY(area) , false);
			enemies.add(ghost);
			area.getChildren().add(ghost.getView());
		}
		
		for(int i = 0 ; i < ripperNum ; i++) {
			Ripper ripper = new Ripper(Randomizer.getX(area) , Randomizer.getY(area) , false);
			enemies.add(ripper);
			area.getChildren().add(ripper.getView());
		}
		
		for(int i = 0 ; i < wispNum ; i++) {
			Wisp wisp = new Wisp(Randomizer.getX(area) , Randomizer.getY(area) , false);
			enemies.add(wisp);
			area.getChildren().add(wisp.getView());
		}
	}
	//in AnimationTimer , starting all the entities in the enemies arrayList
	public void moveAll(double minX, double minY, double maxX, double maxY) {
		for(Entity e : enemies) {
			e.move(minX, minY, maxX, maxY);
		}
	}
	
	//for parameter in the collision constructor
	public ArrayList<Entity> getEnemies() {
		return this.enemies;
	}
}
