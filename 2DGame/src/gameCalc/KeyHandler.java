package gameCalc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import player.Player;

public class KeyHandler implements KeyListener{

	private boolean ctrl;
	
	private Player player; 
	
	public KeyHandler(Player player){
		this.player = player;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_CONTROL){
			ctrl = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_Q&&ctrl){
			System.exit(0);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_W)player.setDirection(1);
		else if(e.getKeyCode()==KeyEvent.VK_S)player.setDirection(2);
		else if(e.getKeyCode()==KeyEvent.VK_A)player.setDirection(3);
		else if(e.getKeyCode()==KeyEvent.VK_D)player.setDirection(4);
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_CONTROL)ctrl = false;
		
		if(e.getKeyCode()==KeyEvent.VK_W
			||e.getKeyCode()==KeyEvent.VK_S
			||e.getKeyCode()==KeyEvent.VK_A
			||e.getKeyCode()==KeyEvent.VK_D)player.setDirection(0);
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
