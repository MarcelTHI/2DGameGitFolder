package map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TileInfo {
	
	int id;
	String name;
	boolean enterable;
	
	BufferedImage image;
	
	
	public TileInfo(int id, String name, boolean enterable)
	{
		this.id = id;
		this.name = name;
		this.enterable = enterable;
		
		loadImage();
	}
	
	private void loadImage()
	{
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir")+"//resources//map//tileImages//"+id+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getID() { return id; }
	public String getName() { return name; }
	public BufferedImage getImage() { return image; }
	public boolean getEnterable() { return enterable; }
	
}
