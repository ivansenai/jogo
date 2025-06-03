package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
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
import mapa.Mapa;
import mapa.MapaCargado;

public class Juego extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static final int ANCHO = 1024;
	private static final int ALTO = 800;
	private static volatile boolean enFuncionamiento = false;
	private static final String NOMBRE = "Juego";

	private static String CONTADOR_APS = "";
	private static String CONTADOR_FPS = "";

	private static JFrame ventana;
	private static Thread thread;
	private static Teclado teclado;
	private static Pantalla pantalla;
	private static Mapa mapa;
	private static Jogador jogador;

	private BufferedImage imagen;
	private int[] pixeles;

	public Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO));

		pantalla = new Pantalla(ANCHO, ALTO);
		teclado = new Teclado();
		addKeyListener(teclado);
		imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
		pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setLayout(new BorderLayout());
		ventana.add(this, BorderLayout.CENTER);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

		// Carregamento do mapa de exemplo (substitua pelo seu mapa desejado)
		// Para um mapa gerado, use: mapa = new MapaGenerado(64, 64);
		mapa = new MapaCargado("/texturas/mapa.png"); // Certifique-se de que o caminho está correto
		jogador = new Jogador(mapa, teclado, 50, 50); // Passa 'mapa' ao construtor do Jogador
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
		mapa.actualizar();
	}

	private void mostrar() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		pantalla.limpiar();
		int posicionX = jogador.obtenerPosicionX() - (pantalla.obtemAncho() / 2);
		int posicionY = jogador.obtenerPosicionY() - (pantalla.obtemAlto() / 2);

		mapa.mostrar(posicionX, posicionY, pantalla);
		jogador.mostrar(pantalla);
		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

		Graphics g = bs.getDrawGraphics();
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		g.drawString(CONTADOR_APS, 10, 20);
		g.drawString(CONTADOR_FPS, 10, 35);
		g.dispose();

		bs.show();
	}

	@Override
	public void run() {
		final int APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = 1000000000 / APS_OBJETIVO;
		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;

		int aps = 0;
		int fps = 0;

		requestFocus();

		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) {
				actualizar();
				aps++;
				delta--;
			}

			mostrar();
			fps++;
			if (System.nanoTime() - referenciaContador > 1000000000) {
				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "FPS: " + fps;
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.iniciar();
	}
}
