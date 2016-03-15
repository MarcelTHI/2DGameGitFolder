package map;

/** Diese Klasse ist zum Verwalten der Informationen über die Tiles da.
 * 	Da die Tiles mehr Eigenschaften als die Settings haben wird ein weiterer
 * 	Manager benoetigt.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import settings.SettingManager;

public class TileManager {		
	
	SettingManager settingManager;
	
	BufferedReader reader;
	String path;
	File sourceFile;
	
	List<TileInfo> tileInfoList;
	
	public  TileManager(SettingManager settingManager)
	{
		this.settingManager = settingManager;
		
		tileInfoList = new LinkedList<TileInfo>();
		
		initPath();
		initReader();
		readTileInfo();
		
		System.out.println("TileManager geladen!");
	}
	
	private void initPath()
	{
		path = settingManager.getSetting("TileManagerSourceFilePath").getValue();
	}
	
	private void initReader()
	{
		sourceFile = new File(System.getProperty("java.class.path") + path);
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
		} catch (FileNotFoundException e) { e.printStackTrace(); }
	}
	
	private void readTileInfo()
	{
		String currentLine;
		int id = 0;
		
		try {
			while((currentLine = reader.readLine()) != null)
			{
				String[] lineContent = currentLine.split("##");
				
				tileInfoList.add(id, new TileInfo(id, lineContent[0], Boolean.parseBoolean(lineContent[2])));
				id++;
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	
	
	//Gettermethods
	public TileInfo getTileInfo(int id)
	{
		return tileInfoList.get(id);
	}
	
	public SettingManager getSettingManager()
	{
		return settingManager;
	}
	
	
	
}
