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
	
	
	public final int UP = 0;
	public final int RIGHT = 1;
	public final int DOWN = 2;
	public final int LEFT = 3;
	public final int NODIRECTION = 4;
	
	
	
	
	KeyHandler keyHandler;
	
	private double velocity;
	
	private int direction = NODIRECTION;

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
	
	
	
	
	
	private boolean upIsPossible()
	{
		if(getGameManager().getMap().getTile((int)(posX / getGameManager().getTileSize()), (int)((posY - velocity) / getGameManager().getTileSize())).isEnterable() &&
				getGameManager().getMap().getTile((int)((posX + getGameManager().getCollisionPlayerWidth()) / getGameManager().getTileSize()), (int)((posY - velocity) / getGameManager().getTileSize())).isEnterable())
			return true;
		
		return false;
	}
	
	private boolean rightIsPossible()
	{
		if(getGameManager().getMap().getTile((int)((posX + getWidth())/ getGameManager().getTileSize()), (int)(posY / getGameManager().getTileSize())).isEnterable() && // -1, because if one leaves the value the player is not able to move right next to the object
				getGameManager().getMap().getTile((int)((posX + getWidth())/ getGameManager().getTileSize()), (int)((posY + getGameManager().getCollisionPlayerHeight() - 1) / getGameManager().getTileSize())).isEnterable())
			return true;
		
		return false;
	}
	
	private boolean downIsPossible()
	{
		if(getGameManager().getMap().getTile((int)(posX / getGameManager().getTileSize()), (int)((posY + getHeight()) / getGameManager().getTileSize())).isEnterable() &&
				getGameManager().getMap().getTile((int)((posX + getGameManager().getCollisionPlayerWidth()) / getGameManager().getTileSize()), (int)((posY + getHeight()) / getGameManager().getTileSize())).isEnterable())
		{
			return true;
		}
		
		return false;
	}
	
	private boolean leftIsPossible()
	{
		if(getGameManager().getMap().getTile((int)((posX - velocity) / getGameManager().getTileSize()), (int)(posY / getGameManager().getTileSize())).isEnterable() &&
				getGameManager().getMap().getTile((int)((posX - velocity) / getGameManager().getTileSize()), (int)((posY + getGameManager().getCollisionPlayerHeight() - 1) / getGameManager().getTileSize())).isEnterable()	)
			return true;
			
		return false;
	}
}