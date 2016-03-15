package map;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {
	
	private Rectangle rectangle;
	
	private int id;
	
	private TileManager tileManager;
	
	public Tile(int id, TileManager manager, int pixelX, int pixelY){
		this.id = id;
		this.tileManager = manager;
		
		if(!isEnterable())
			rectangle = new Rectangle(pixelX, pixelY, Integer.parseInt(tileManager.getSettingManager().getSetting("TileSize").getValue()), Integer.parseInt(tileManager.getSettingManager().getSetting("TileSize").getValue()));
	}
	
	public Rectangle getRectangle()
	{
		return rectangle;
	}
	
	public BufferedImage getImage(){
		return tileManager.getTileInfo(id).getImage();
	}
	
	public int getId(){
		return id;
	}
	
	public boolean isEnterable(){
		return tileManager.getTileInfo(id).getEnterable();
	}
}
