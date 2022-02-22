// Image
// Sempre usar na potência de 2 
//
// Lista de Sprite criado em 320px vs 320px e 300 pixeles (vermelho e azul)
// Guarda em cdr e exportar para png (hojasprite.png ou hojasprite.cdr)
// Cada Sprite é criado em 32px vs 32px e 300 pixeles (livre)
// Guardar em cdr e exportar para pgn (livre)
package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import control.Teclado;
import entes.criaturas.Jogador;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;
import mapa.MapaCargado;

public class Juego extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int ANCHO = 1024; // JFrame altura de 800 (livre)
	private static final int ALTO = 800; // JFrame larguara de 600 (livre)

	private static volatile boolean enFuncionamiento = false;

	private static final String NOMBRE = "Juego";

	private static String CONTADOR_APS = "";
	private static String CONTADOR_FPS = "";

	private static int aps = 0;
	private static int fps = 0;

	// Curso 46 Comentado
	// private static int x = 0;
	// private static int y = 0;
	// Curso 46 Fim

	private static JFrame ventana;
	private static Thread thread;
	private static Teclado teclado;
	private static Pantalla pantalla;

	private static Mapa mapa;
	private static Jogador jogador;

	private static BufferedImage image = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private static int[] pixeles = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono/icone.png"));

	private Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO));

		pantalla = new Pantalla(ANCHO, ALTO);

		teclado = new Teclado();
		addKeyListener(teclado);

		// Curso 31 Comentado
		// mapa = new MapaGenerado(128, 128); // Este numero e o TILES de ancho e alto
		mapa = new MapaCargado("/texturas/mapa.png");
		// Curso 31 Fim
		jogador = new Jogador(mapa, teclado, Sprite.ARRIBA0, 100, 384);

		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setAlwaysOnTop(true);
		ventana.setIconImage(icono.getImage());
		ventana.setLayout(new BorderLayout());
		ventana.add(this, BorderLayout.CENTER);
		ventana.setUndecorated(true);
		ventana.pack(); // Ajustar tudo ao tamanho JFame
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.iniciar();
	}

	public synchronized void iniciar() {
		enFuncionamiento = true;
		thread = new Thread(this, "Graficos");
		thread.start();
	}

	public synchronized void detener() {
		enFuncionamiento = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void actualizar() {
		teclado.actualizar();
		jogador.actualizar();

		// Curso 46 comentado
		// if (teclado.arriba) {
		// y--;
		// }
		// if (teclado.abajo) {
		// y++;
		// }
		// if (teclado.izquierda) {
		// x--;
		// }
		// if (teclado.derecha) {
		// x++;
		// }
		// Curso 46 Fim
		
		if (teclado.salir) {
			System.exit(0);
		}
		aps++;
	}

	private void mostrar() {
		BufferStrategy estrategia = getBufferStrategy();
		if (estrategia == null) {
			createBufferStrategy(3);
			return;
		}
		pantalla.limpar();

		// Curso 47 Comentado
		// mapa.mostrar(jogador.obtenerPosicionX(), jogador.obtenerPosicionY(),
		// pantalla);
		// Curso 47 Fim
		mapa.mostrar(jogador.obtenerPosicionX() - pantalla.obtemAncho() / 2 + jogador.obtenerSprite().obtemLado() / 2,
				jogador.obtenerPosicionY() - pantalla.obtemAlto() / 2 + jogador.obtenerSprite().obtemLado() / 2,
				pantalla);
		jogador.mostrar(pantalla);

		// Curso 46 Comentado
		// mapa.mostrar(x, y, pantalla);
		// Curso 46 Fim
		// Curso 23 Comentado
		// pantalla.mostrar(x, y);
		// Curso 23 Fim
		
		// Mesmo que o "for (int i = 0... pixeles.length"
		// Segue codigo similar comentado abaixo
		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length); // O
		// for (int i = 0; i < pixeles.length; i++) {
		// pixeles[i] = pantalla.pixeles[i];
		// }
		// Fim Codigo Similar
		
		Graphics g = estrategia.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.white);
		g.drawString(CONTADOR_APS, 10, 20);
		g.drawString(CONTADOR_FPS, 10, 35);
		g.drawString("X: " + jogador.obtenerPosicionX(), 10, 50);
		g.drawString("Y: " + jogador.obtenerPosicionY(), 10, 65);
		g.dispose();
		estrategia.show();
		fps++;
	}

	public void run() {
		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();
		double tiempoTranscurrido;
		double delta = 0;

		requestFocus();
		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) {
				actualizar();
				delta--;
			}
			mostrar();

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "FPS: " + fps;

				// Curso 25 Comentado
				// ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " +
				// fps);
				// Curso 25 Fim
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}
}
