package entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public class Animation {
		
	private int entityId;
	private int animationId;
	
	private List<BufferedImage> imageList;
	private int currentImageId = 0;
	
	public Animation(int entityId, int animationId)
	{
		this.entityId = entityId;
		this.animationId = animationId;
		
		imageList = new LinkedList<BufferedImage>();
		
		loadImages();
	}
	
	private void loadImages()
	{
		int imageIndex = 0;
		
		try {
			while(hasNextImage(imageIndex))
			{
				imageList.add(ImageIO.read(new File(System.getProperty("user.dir") + "//resources//animations//" + entityId + "//" + animationId + "//" + imageIndex + ".png")));
				imageIndex++;
				System.out.println("geladen: animationID :" + animationId + "/ imageId: " + imageIndex);
			}
			
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private boolean hasNextImage(int currentImageIndex)
	{
		if(new File(System.getProperty("user.dir") + "//resources//animations//" + entityId + "//" + animationId +  "//" + (currentImageIndex) + ".png").exists())
			return true;
		else
			return false;
	}
	
	
	public void nextImage()
	{
		
		if(currentImageId >= imageList.size()-1)
		{
			currentImageId = 0;
		}
		else if(currentImageId < imageList.size())
		{
			currentImageId++;
		}
	}
	
	public BufferedImage getCurrentImage()
	{
		return imageList.get(currentImageId);
	}
	public BufferedImage getImage(int id)
	{
		return imageList.get(id);
	}
	
}
