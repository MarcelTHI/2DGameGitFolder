package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class Map {
	
	private int[][] intMap;
	private Tile[][] tileMap;
	
	private BufferedReader fileInput;
	
	private int Width, Height;
	
	private TileManager tileManager;
	
	public Map(TileManager tileManager){
		this.tileManager = tileManager;
		try {
			fileInput = new BufferedReader(new InputStreamReader(new FileInputStream(new File(System.getProperty("java.class.path")+"//resources//Map.txt"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Width = getWidth();
		Height = getHeight();
		intMap = new int[Width][Height];
		readMap();
		loadMap();
		
	}
	
	public int[][] getIntMap(){
		return intMap;
	}
	
	public Tile[][] getTileMap(){
		return tileMap;
	}
	
	public Tile getTile(int x, int y)
	{
		return tileMap[x][y];
	}
	
	
	
	/**	
	 * 	Private Methods
	 * */
	//putting Map into int[][] with IDs	
	private void readMap(){
		String line;
		int[] currentLine;
		try {
			fileInput = new BufferedReader(new InputStreamReader(new FileInputStream(new File(System.getProperty("java.class.path")+"//resources//Map.txt"))));
			for(int i = 0;i<Height;i++)
			{
				line = fileInput.readLine();
				currentLine = new int[Width];
				String[] tempInts = line.split("##");
				for(int ti = 0;ti<tempInts.length;ti++)
				{
					currentLine[ti] = Integer.parseInt(tempInts[ti]);
				}
				putArray(currentLine, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//put array in index of int[][]	
	private void putArray(int[] line, int index)
	{
		for(int i = 0; i<line.length;i++)
		{
			intMap[i][index] = line[i];
		}
	}
	//returns Map-Width	
	private int getWidth()
	{
		String firstline;
		int width = 0;
		try 
		{
			firstline = fileInput.readLine();
			width = firstline.split("##").length;
		
		} catch (IOException e) { e.printStackTrace(); }
		return width;
	}
	//returns Map-Height	
	private int getHeight()
	{
		int height = 1;
		try {
			while((fileInput.readLine())!= null){
				height++;
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return height;
	}
	
	private void loadMap(){
		tileMap = new Tile[Width][Height];
		for(int y = 0; y < Height; y++)
		{
			for(int x = 0; x < Width; x++)
			{
				tileMap[x][y] = new Tile(intMap[x][y], 
						tileManager, 
						x*Integer.parseInt(tileManager.getSettingManager().getSetting("TileSize").getValue()), 
						y*Integer.parseInt(tileManager.getSettingManager().getSetting("TileSize").getValue()));
			}
		}
	}
}
