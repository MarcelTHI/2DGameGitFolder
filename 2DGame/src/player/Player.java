package player;

import entity.Entity;
import gameCalc.GameManager;
import gameCalc.KeyHandler;

public class Player extends Entity{
	
	
	public final int UP = 0;
	public final int RIGHT = 1;
	public final int DOWN = 2;
	public final int LEFT = 3;
	public final int NODIRECTION = 4;
	
	
	
	
	KeyHandler keyHandler;
		
	private int direction = NODIRECTION;
	
	

	public Player(double x, double y, int width, int height, int vel, KeyHandler keyHandler, GameManager gameManager) 
	{
		super(0, "Spieler", x, y, width, height, vel, gameManager);
		
		this.keyHandler = keyHandler;
		
		loadAnimation();
		
		System.out.println("Spieler geladen!");
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public void movePlayer(int direction){
		
		if(direction == UP)
		{
			if(upIsPossible())
			{
				posY -= velocity;
				getAnimation(0).nextImage();
			}
		}
		if(direction == RIGHT)
		{
			if(rightIsPossible())
			{
				posX += velocity;
				getAnimation(0).nextImage();
			}
		}
		if(direction == DOWN)
		{
			if(downIsPossible())
			{
				posY += velocity;
				getAnimation(1).nextImage();
			}
		}
		if(direction == LEFT)
		{
			if(leftIsPossible())
			{
				posX -= velocity;
				getAnimation(1).nextImage();
			}
		}
	}
	
	public void update(){
		movePlayer(direction);
	}
}