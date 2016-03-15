package player;

import java.awt.Rectangle;
import java.util.List;

import entity.Entity;
import gameCalc.GameManager;
import gameCalc.KeyHandler;

 /* 1 = Up
 * 2 = Down
 * 3 = Left
 * 4 = Right
 */
public class Player extends Entity{
	
	KeyHandler keyHandler;
	
	private double velocity;
	
	private int direction = 0;

	public Player(double x, double y, int width, int height, int vel, KeyHandler keyHandler, GameManager gameManager) 
	{
		super(0, "Spieler", x, y, width, height, gameManager);
		
		this.keyHandler = keyHandler;
		this.velocity = vel;
		
		loadAnimation();
		
		System.out.println("Spieler geladen!");
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public void movePlayer(int direction){
		
		if(direction == 1)
		{
			if(getGameManager().getMap().getTile((int)(posX / getGameManager().getTileSize()), (int)((posY - velocity) / getGameManager().getTileSize())).isEnterable())
			{
				posY -= velocity;
				getAnimation(0).nextImage();
			}
		}
		if(direction == 2)
		{
			if(getGameManager().getMap().getTile((int)(posX / getGameManager().getTileSize()), (int)((posY + getHeight()) / getGameManager().getTileSize())).isEnterable())
			{
				posY += velocity;
				getAnimation(0).nextImage();
			}
		}
		if(direction == 3)
		{
			if(getGameManager().getMap().getTile((int)((posX - velocity) / getGameManager().getTileSize()), (int)(posY / getGameManager().getTileSize())).isEnterable())
			{
				posX -= velocity;
				getAnimation(1).nextImage();
			}
		}
		if(direction == 4)
		{
			if(getGameManager().getMap().getTile((int)((posX + getWidth())/ getGameManager().getTileSize()), (int)(posY / getGameManager().getTileSize())).isEnterable())
			{
				posX += velocity;
				getAnimation(1).nextImage();
			}
		}
	}
	
	public void update(List<Entity> entityList){
		movePlayer(direction);
	}
}