package settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SettingManager
 {
	
	BufferedReader reader;
	File settingFile;
	
	String path = "//resources//settings.txt";
	
	List<Setting> settingList;
	
	public SettingManager()
	{
		initList();
		initReader();
		readSettings();
	}
	
	private void initList()
	{
		settingList = new LinkedList<Setting>();
	}
	
	private void initReader()
	{
		settingFile = new File(System.getProperty("java.class.path")+path);
		
		try {
			
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(settingFile)));
			
		} catch (FileNotFoundException e) { e.printStackTrace(); }
	}
	
	private void readSettings()
	{
		String currentLine;
		int id = 0;
		try {
			while((currentLine = reader.readLine()) != null)
			{
				String[] lineContent = currentLine.split("##");
				
				settingList.add(id, new Setting(id, lineContent[0], lineContent[1]));
				id++;
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	//GetterMethoden
	public Setting getSetting(int id)
	{
		return settingList.get(id);
	}
	public Setting getSetting(String name)
	{
		for(int settingIndex = 0; settingIndex < settingList.toArray().length; settingIndex++)
		{
			if(settingList.get(settingIndex).getName().equals(name))
				return settingList.get(settingIndex);
		}
		
		return null;
	}
}
