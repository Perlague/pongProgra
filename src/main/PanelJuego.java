package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelJuego extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public final int tamañoCeldaOriginal = 24,
					 escala = 2,
					 tamañoCelda = tamañoCeldaOriginal * escala,
					 colPantallaMax = 16,
					 renPantallaMax = 12,
					 anchoPantalla = colPantallaMax * tamañoCelda,
					 altoPantalla = renPantallaMax * tamañoCelda,
					 
					 //Dimensiones de los jugadores
					 anchoJugador = tamañoCelda / 2,
					 altoJugador = tamañoCelda * 2;
					 
	private int 
					 //Posicion y velocidad del 3er objeto
					 objetoX = anchoPantalla / 2,
					 objetoY = altoPantalla / 2,
					 velocidadObjetoX = 12,
					 velocidadObjetoY = 12,
					 tamañoObjeto = tamañoCeldaOriginal;

	public int 	jugadorX = 15,
				jugadorY = 100,
				velocidad = 8,
				jugador2X = anchoPantalla - tamañoCelda,
				jugador2Y = 100;
	
	ManejadorTecla mTecla = new ManejadorTecla();
	Thread threadJuego;
	
	public PanelJuego() {
		
		this.setPreferredSize( new Dimension(anchoPantalla, altoPantalla) );
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(mTecla);
		this.setFocusable(true);
		
	} // constructor
	
	public void iniciarThreadJuego() {
		threadJuego = new Thread(this);
		threadJuego.start();
	}
	
	long tiempoInicial = System.nanoTime(),
		 delta = 0,
		 FPS = 60,
		 tiempoPorCuadro = 1_000_000_000/FPS;
	
	@Override
	public void run() {
		
		while(threadJuego != null) {
			
			delta = System.nanoTime() - tiempoInicial;
			
			if (delta >= tiempoPorCuadro) {
			
				// Fase 1: Actualización
				update();
				
				// Fase 2: Modificaciones Visuales
				repaint();
				
				tiempoInicial = System.nanoTime();
				
			}
			
		} // while
		
	} // run
	
	//Definir movimiento de los jugadores, objeto y colisiones
	public void update() {
		// Mover el primer jugador y evitar que se salga de la pantalla
		if(mTecla.arribaPresionado && jugadorY > 0) {
			jugadorY -= velocidad * 2;
		}
		if(mTecla.abajoPresionado && jugadorY + altoJugador < altoPantalla) {
			jugadorY += velocidad * 2;
		}
		 //Segundo jugador
		if(mTecla.arribaFlechaPresionado && jugador2Y > 0) {
			jugador2Y -= velocidad * 2;
		}
		if(mTecla.abajoFlechaPresionado && jugador2Y + altoJugador < altoPantalla) {
			jugador2Y += velocidad * 2;
		}
		// Mover el tercer objeto
		objetoX += velocidadObjetoX;
		objetoY += velocidadObjetoY;
	
		//Objeto rebota en los bordes del panel
		if(objetoX <= 0 || objetoX + tamañoObjeto >= anchoPantalla) {
			velocidadObjetoX = -velocidadObjetoX;
		}
		if(objetoY <= 0 || objetoY + tamañoObjeto >= altoPantalla) {
			velocidadObjetoY = -velocidadObjetoY;
		}
	
		//Objeto rebota en los jugadores
		if(objetoX <= jugadorX + anchoJugador && objetoX + tamañoObjeto >= jugadorX &&
		   objetoY <= jugadorY + altoJugador && objetoY + tamañoObjeto >= jugadorY) {
			velocidadObjetoX = -velocidadObjetoX;
		}
		if(objetoX <= jugador2X + anchoJugador && objetoX + tamañoObjeto >= jugador2X &&
		   objetoY <= jugador2Y + altoJugador && objetoY + tamañoObjeto >= jugador2Y) {
			velocidadObjetoX = -velocidadObjetoX;
		}
	}
	
	//Añadir objetos a la pantalla
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		g2.setColor(Color.white);
		g2.fillRect(jugadorX, jugadorY, anchoJugador, altoJugador);

		g2.setColor(Color.red);
		g2.fillRect(jugador2X, jugador2Y, anchoJugador, altoJugador);

		g2.setColor(Color.blue);
		g2.fillRect(objetoX, objetoY, tamañoObjeto, tamañoObjeto);
	
		g2.dispose();
	}
	
}
