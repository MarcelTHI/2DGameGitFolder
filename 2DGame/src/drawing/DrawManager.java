package drawing;

import gameCalc.GameManager;
import gameCalc.KeyHandler;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import player.Player;
import settings.SettingManager;

public class DrawManager extends JFrame{
	
	//Instances to manager classes	
	private GameManager gameManager;
	private SettingManager settingManager;
	private KeyHandler keyHandler;
	
	//Instance to Player	
	private Player player;
	private int playerY;			//PlayerX und PlayerY muessen fuer die Berechnung von repaintArea() gleich bleiben
	private int playerX;
	
	//Instance Drawclass	
	private DrawLabel drawLabel;
	
	//Variables from Settingclass	
	private int TileSize, TileOffset, FrameWidth, FrameHeight;
	
	public DrawManager(GameManager gameManager, SettingManager settingManager, KeyHandler keyHandler)
	{
		this.settingManager = settingManager;
		this.gameManager = gameManager;
		this.keyHandler = keyHandler;
		this.player = gameManager.getPlayer();
		
		TileSize = Integer.parseInt(settingManager.getSetting("TileSize").getValue());
		TileOffset = Integer.parseInt(settingManager.getSetting("TileOffset").getValue());
		FrameWidth = Integer.parseInt(settingManager.getSetting("FrameWidth").getValue());
		FrameHeight = Integer.parseInt(settingManager.getSetting("FrameHeight").getValue());
		
		playerX = FrameWidth/2-gameManager.getPlayer().getWidth()/2;
		playerY = FrameHeight/2-gameManager.getPlayer().getHeight()/2;
		
		drawLabel = new DrawLabel();
		
		initFrame();
		
		System.out.println("DrawManager geladen!");
		
	}
	//Setup Frame	
	private void initFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setSize(FrameWidth, FrameHeight);
		setLocationRelativeTo(null);
		setLayout(null);
		
		drawLabel.setBounds(0, 0, FrameWidth, FrameHeight);
		
		add(drawLabel);
		addKeyListener(keyHandler);
		setVisible(true);
	}
//Reapaints whole Screen	
	public void repaintAll(){
		drawLabel.repaint();
	}
//Repaints area depending on player position	
	public void repaintArea(){
		int x = playerX-FrameWidth/2-gameManager.getPlayer().getWidth()/2-TileOffset*TileSize;
		int y =	playerY-FrameHeight/2-gameManager.getPlayer().getHeight()/2-TileOffset*TileSize;
		drawLabel.repaint(x, y, x+TileOffset*TileSize*4+FrameWidth, y+TileOffset*TileSize*4+FrameHeight);
	}
//Sync`s Frame variables	
	public void syncVar(){
		FrameWidth = Integer.parseInt(settingManager.getSetting("FrameWidth").getValue());
		FrameHeight = Integer.parseInt(settingManager.getSetting("FrameHeight").getValue());
	}
//Drawclass
	private class DrawLabel extends JLabel{
		
		public void paintComponent(Graphics g)
		{
			//Map
			for(int mapYIndex = 0; mapYIndex < gameManager.getMap().getIntMap()[0].length; mapYIndex++)
			{
				for(int mapXIndex = 0; mapXIndex < gameManager.getMap().getIntMap().length; mapXIndex++)
				{
					g.drawImage(gameManager.getMap().getTile(mapXIndex, mapYIndex).getImage(), 			//Image
							mapXIndex * TileSize - (int)gameManager.getPlayer().getX() + playerX, 		//X-Koordinate
							mapYIndex * TileSize - (int)gameManager.getPlayer().getY() + playerY, 		//Y-Koordinate
							TileSize,																	//Width
							TileSize, 																	//Height
							null);																		//ImageObserver
				}
			}
			
			//Player
			/*g.fillRect(playerX,
					playerY,
					gameManager.getPlayer().getWidth(),
					gameManager.getPlayer().getHeight());*/
			g.drawImage(gameManager.getPlayer().getCurrentAnimation().getCurrentImage(),
					playerX,
					playerY,
					gameManager.getPlayer().getWidth(),
					gameManager.getPlayer().getHeight(),
					null);
			
			
		}
	}
}
