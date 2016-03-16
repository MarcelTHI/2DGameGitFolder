package main;

import settings.SettingManager;
import gameCalc.GameManager;
import gameCalc.KeyHandler;
import drawing.DrawManager;

public class Main {
	
	DrawManager drawManager;
	GameManager gameManager;
	SettingManager settingManager;
	KeyHandler keyHandler;
	
	boolean game = true;

	public static void main(String[] args) {
		new Main();
	}
	
	private Main()
	{
		init();
		startGame();
		
		System.out.println("wwww");
	}
	
	private void init()
	{
		settingManager = new SettingManager();
		gameManager = new GameManager(settingManager, keyHandler);
		keyHandler = new KeyHandler(gameManager.getPlayer());
		drawManager = new DrawManager(gameManager, settingManager, keyHandler);
		
		
	}
	
	private void startGame()
	{
		System.out.println("Spiel startet!");
		while(game)
		{
			gameManager.update();
			drawManager.repaintArea();
			//drawManager.repaintAll();
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}

}
