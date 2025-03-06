package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManejadorTecla implements KeyListener {
	
	public boolean 	arribaPresionado,
					abajoPresionado,
					arribaFlechaPresionado,
               		abajoFlechaPresionado;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();

		//Definir las teclas que se van a utilizar para el movimiento de los jugadores
		if(code == KeyEvent.VK_W) {
			arribaPresionado = true;
		}
		if(code == KeyEvent.VK_S) {
			abajoPresionado = true;
		}
		if(code == KeyEvent.VK_UP) {
			arribaFlechaPresionado = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			abajoFlechaPresionado = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//Parar el movimiento de los jugadores cuando se suelta la tecla
		
		if(code == KeyEvent.VK_W) {
			arribaPresionado = false;
		}
		if(code == KeyEvent.VK_S) {
			abajoPresionado = false;
		}
		if(code == KeyEvent.VK_UP) {
			arribaFlechaPresionado = false;
		}
		if(code == KeyEvent.VK_DOWN) {
			abajoFlechaPresionado = false;
		}
		
	}

}
