package npc;

import entity.Entity;
import gameCalc.GameManager;

public abstract class Npc extends Entity{

	public Npc(int id, String name, double x, double y, int width, int height, GameManager gameManager) 
	{
		super(id, name, x, y, width, height, gameManager);
	}
	
	
	
}
