package juego; [span_62](start_span)//[span_62](end_span)

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import control.Teclado; [span_63](start_span)//[span_63](end_span)
import entes.criaturas.Jogador;
import graficos.Pantalla;
import mapa.Mapa;
import mapa.MapaCargado; [span_64](start_span)//[span_64](end_span)

[span_65](start_span)public class Juego extends Canvas implements Runnable { //[span_65](end_span)
	private static final long serialVersionUID = 1L;
	private static final int ANCHO = 1024; [span_66](start_span)//[span_66](end_span)
	private static final int ALTO = 800; [span_67](start_span)//[span_67](end_span)
	private static volatile boolean enFuncionamiento = false; [span_68](start_span)//[span_68](end_span)
	private static final String NOMBRE = "Juego";

	private static String CONTADOR_APS = "";
	private static String CONTADOR_FPS = ""; [span_69](start_span)//[span_69](end_span)

	private static JFrame ventana;
	private static Thread thread;
	private static Teclado teclado; [span_70](start_span)//[span_70](end_span)
	private static Pantalla pantalla; [span_71](start_span)//[span_71](end_span)
	private static Mapa mapa; [span_72](start_span)//[span_72](end_span)
	private static Jogador jogador; [span_73](start_span)//[span_73](end_span)

	private BufferedImage imagen;
	private int[] pixeles; [span_74](start_span)//[span_74](end_span)

	[span_75](start_span)public Juego() { //[span_75](end_span)
		setPreferredSize(new Dimension(ANCHO, ALTO));

		pantalla = new Pantalla(ANCHO, ALTO);
		teclado = new Teclado();
		addKeyListener(teclado);
		imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB); [span_76](start_span)//[span_76](end_span)
		pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData(); [span_77](start_span)//[span_77](end_span)

		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setLayout(new BorderLayout());
		ventana.add(this, BorderLayout.CENTER);
		ventana.pack(); [span_78](start_span)//[span_78](end_span)
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

		// Carregamento do mapa de exemplo (substitua pelo seu mapa desejado)
		// Para um mapa gerado, use: mapa = new MapaGenerado(64, 64);
		mapa = new MapaCargado("/texturas/mapa.png"); [span_79](start_span)// Certifique-se de que o caminho está correto[span_79](end_span)
		jogador = new Jogador(mapa, teclado, 50, 50); [span_80](start_span)// Passa 'mapa' ao construtor do Jogador[span_80](end_span)
	}

	[span_81](start_span)public synchronized void iniciar() { //[span_81](end_span)
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

	[span_82](start_span)private void actualizar() { //[span_82](end_span)
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

		pantalla.limpiar(); [span_83](start_span)//[span_83](end_span)
		int posicionX = jogador.obtenerPosicionX() - (pantalla.obtemAncho() / 2); [span_84](start_span)//[span_84](end_span)
		int posicionY = jogador.obtenerPosicionY() - (pantalla.obtemAlto() / 2); [span_85](start_span)//[span_85](end_span)

		mapa.mostrar(posicionX, posicionY, pantalla);
		jogador.mostrar(pantalla);
		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length); [span_86](start_span)//[span_86](end_span)

		Graphics g = bs.getDrawGraphics();
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		g.drawString(CONTADOR_APS, 10, 20); [span_87](start_span)//[span_87](end_span)
		g.drawString(CONTADOR_FPS, 10, 35); [span_88](start_span)//[span_88](end_span)
		g.dispose();

		bs.show();
	}

	@Override
	public void run() {
		final int APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = 1000000000 / APS_OBJETIVO; [span_89](start_span)//[span_89](end_span)
		long referenciaActualizacion = System.nanoTime(); [span_90](start_span)//[span_90](end_span)
		long referenciaContador = System.nanoTime(); [span_91](start_span)//[span_91](end_span)

		double tiempoTranscurrido;
		double delta = 0;

		int aps = 0; [span_92](start_span)//[span_92](end_span)
		int fps = 0; [span_93](start_span)//[span_93](end_span)

		requestFocus();

		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle; [span_94](start_span)//[span_94](end_span)
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION; [span_95](start_span)//[span_95](end_span)

			while (delta >= 1) {
				actualizar();
				aps++;
				delta--;
			}

			mostrar();
			fps++;
			[span_96](start_span)if (System.nanoTime() - referenciaContador > 1000000000) { //[span_96](end_span)
				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "FPS: " + fps; [span_97](start_span)//[span_97](end_span)
				aps = 0; [span_98](start_span)//[span_98](end_span)
				fps = 0; [span_99](start_span)//[span_99](end_span)
				referenciaContador = System.nanoTime(); [span_100](start_span)//[span_100](end_span)
			}
		}
	}

	public static void main(String[] args) {
		Juego juego = new Juego(); [span_101](start_span)//[span_101](end_span)
		juego.iniciar(); [span_102](start_span)//[span_102](end_span)
	}
			}
	
