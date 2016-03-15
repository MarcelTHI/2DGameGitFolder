package gameCalc;

import java.util.LinkedList;
import java.util.List;

import map.Map;
import map.TileManager;
import player.Player;
import settings.SettingManager;
import entity.Entity;

public class GameManager {
	
	private SettingManager settingManager;
	private KeyHandler keyHandler;
	
	private List<Entity> entityList;
	
	private Player player;
	//Fuer die Kollisionsabfrage
	private int tileSize;
	private int collisionPlayerWidth;
	private int collisionPlayerHeight;
	
	private Map map;
	
	public GameManager(SettingManager settingManager, KeyHandler keyHandler)
	{
		this.settingManager = settingManager;
		this.keyHandler = keyHandler;
		entityList = new LinkedList<Entity>();
		
		initMap();
		initPlayer();
		initCollision();
		
		System.out.println("GameManager geladen!");
	}
	
	private void initMap() {
		map = new Map(new TileManager(settingManager));
	}

	private void initPlayer()
	{
		player = new Player(Integer.parseInt(settingManager.getSetting("StartPosX").getValue()),//PosX
				Integer.parseInt(settingManager.getSetting("StartPosY").getValue()), 			//PosY
				50, 																				//Width
				50, 																				//Height
				Integer.parseInt(settingManager.getSetting("PlayerVelocity").getValue()),
				keyHandler,
				this);
	}
	
	private void initCollision()
	{
		collisionPlayerWidth = Integer.parseInt(settingManager.getSetting("CollisionPlayerWidth").getValue());
		collisionPlayerHeight = Integer.parseInt(settingManager.getSetting("CollisionPlayerHeight").getValue());
		tileSize = Integer.parseInt(settingManager.getSetting("TileSize").getValue());
	}
	
	public void update()
	{
		player.update();
		
		for(int entityIndex = 0; entityIndex < entityList.size(); entityIndex++)
		{
			entityList.get(entityIndex).update();
		}
	}
	
	
	//Gettermethods
	public Player getPlayer()
	{
		return player;
	}
	
	public Map getMap()
	{
		return map;
	}
	public int getTileSize() { return tileSize; }
	public int getCollisionPlayerWidth() { return collisionPlayerWidth; }
	public int getCollisionPlayerHeight() { return collisionPlayerHeight; }
	
	
}
