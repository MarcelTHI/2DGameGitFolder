package entity;

import gameCalc.GameManager;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public abstract class Entity {
	private int id;
	protected int width;
	protected int height;
	
	private GameManager gameManager;
	
	private String name;
	
	private List<Animation> animationList;
	private int currentAnimationId;
	private BufferedImage image;
	private String path;
	
	private  Rectangle rectangle;
	
	protected double posX;
	protected double posY;
	
	public Entity(int id, String name, double x, double y, int width, int height, GameManager gameManager)
	{
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.id = id;
		
		this.gameManager = gameManager;
		
		this.rectangle = new Rectangle((int)x, (int)y, width, height);
		
		
	}
	
	protected void loadAnimation()
	{
		animationList = new LinkedList<Animation>();
		currentAnimationId = 0;
		
		int animationId = 0;
		while(hasNextAnimation(animationId))
		{
			animationList.add(new Animation(id, animationId));
			System.out.println("animation geladen" + animationId);
			animationId += 1;
		}
	}
	
	private boolean hasNextAnimation(int currentAnimation)
	{
			
		if(new File(System.getProperty("user.dir") + "\\resources\\animations\\" + id + "\\" + (currentAnimation)).exists())
				return true;
		else
			System.out.println("existiert nicht " + System.getProperty("user.dir") + "\\resources\\animations\\" + id + "\\" + (currentAnimation));
			return false;
	}
	
	protected void loadTexture()
	{
		try {
			image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(System.getProperty("java.class.path")+path));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	protected Rectangle getRectangle()
	{
		rectangle.setBounds((int)posX, (int)posY, width, height);
		return rectangle;
	}
	protected GameManager getGameManager() { return gameManager; }
	
	public double getX() { return posX; }
	public double getY() { return posY; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public BufferedImage getImage() { return image; }
	public int getID() { return id; }
	public String getName() { return name; }
	
	
	public Animation getCurrentAnimation() 
	{ 
		return animationList.get(currentAnimationId); 
	}
	public void nextAnimation()
	{
		if(currentAnimationId < animationList.size())
		{
			currentAnimationId++;
		}
		else if(currentAnimationId >= animationList.size()-1)
			currentAnimationId = 0;
		else
			System.out.println("Entschuldigung, es ist ein Problem beim weiterschalten der Animation aufgetreten.");
	}
	public Animation getAnimation(int animationId) 
	{ 
		currentAnimationId = animationId;
		return animationList.get(animationId); 
	} 
	
	
	
	
	public void update()
	{
		
	}
}
