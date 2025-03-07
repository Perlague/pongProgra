package main;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		// Creacion de ventana
		JFrame ventana = new JFrame();
		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setTitle("Pong");
		ventana.setResizable(false);
		//ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	
		// Inicializar el panel del juego
		PanelJuego pj = new PanelJuego();
		ventana.add(pj);
		ventana.pack();
		
		
		// -----------SOLUCIÓN AL PROBLEMA----------
		// Request focus for the PanelJuego
        pj.requestFocusInWindow(); // Add this line
		
		// Hacer al juego empezar a correr
		pj.iniciarThreadJuego();
		
	} // método main
	
} // Clase Main