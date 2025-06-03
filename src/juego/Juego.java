package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
// import javax.swing.ImageIcon; // Não usado no código fornecido
import javax.swing.JFrame;

import control.Teclado; // Certifique-se de que este pacote existe
import entes.criaturas.Jogador;
import graficos.Pantalla;
import mapa.Mapa;
import mapa.MapaCargado; // Usado para carregar mapa de um arquivo
// import mapa.MapaGenerado; // Descomente e use se quiser gerar um mapa programaticamente

public class Juego extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L; // Necessário para classes serializáveis (Canvas)
	private static final int ANCHO = 1024; // Largura da janela
	private static final int ALTO = 800;   // Altura da janela
	private static volatile boolean enFuncionamiento = false; // Flag para controlar o loop do jogo
	private static final String NOMBRE = "Meu Jogo Top-Down"; // Título da janela

	private static String CONTADOR_APS = ""; // Contador de Atualizações Por Segundo
	private static String CONTADOR_FPS = ""; // Contador de Frames Por Segundo

	private static JFrame ventana;
	private static Thread thread;
	private static Teclado teclado;
	private static Pantalla pantalla;
	private static Mapa mapa;
	private static Jogador jogador;

	private BufferedImage imagen; // Imagem em que a tela é desenhada
	private int[] pixeles; // Array de pixels da imagem (para acesso direto)

	public Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO)); // Define o tamanho preferido do Canvas

		pantalla = new Pantalla(ANCHO, ALTO); // Inicializa a tela de renderização
		teclado = new Teclado(); // Inicializa o controlador de teclado
		addKeyListener(teclado); // Adiciona o listener de teclado ao Canvas

		imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB); // Cria a imagem de buffer
		// Obtém o array de pixels da imagem para manipulação direta
		pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

		ventana = new JFrame(NOMBRE); // Cria a janela principal do jogo
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar
		ventana.setResizable(false); // Impede que a janela seja redimensionada
		ventana.setLayout(new BorderLayout()); // Define o layout da janela
		ventana.add(this, BorderLayout.CENTER); // Adiciona o Canvas ao centro da janela
		ventana.pack(); // Ajusta o tamanho da janela ao conteúdo
		ventana.setLocationRelativeTo(null); // Centraliza a janela na tela
		ventana.setVisible(true); // Torna a janela visível

		// Carregamento do mapa de exemplo
		// Você pode alternar entre MapaCargado e MapaGenerado
		mapa = new MapaCargado("/texturas/mapa.png"); // Carrega um mapa de um arquivo de imagem
		// Para um mapa gerado aleatoriamente, você usaria:
		// mapa = new MapaGenerado(64, 64); // Cria um mapa gerado com 64x64 tiles

		// Inicializa o jogador, passando o mapa, teclado e sua posição inicial
		jogador = new Jogador(mapa, teclado, 50, 50);
	}

	// Inicia o thread principal do jogo
	public synchronized void iniciar() {
		enFuncionamiento = true;
		thread = new Thread(this, "Graficos"); // Cria um novo thread
		thread.start(); // Inicia o thread, que chamará o método run()
	}

	// Detém o thread principal do jogo
	public synchronized void detener() {
		enFuncionamiento = false;
		try {
			thread.join(); // Espera o thread terminar
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Lógica de atualização do jogo (60 APS)
	private void actualizar() {
		teclado.actualizar(); // Atualiza o estado do teclado
		jogador.actualizar(); // Atualiza a lógica do jogador
		mapa.actualizar();    // Atualiza a lógica do mapa (se houver)
	}

	// Lógica de renderização do jogo (FPS máximo possível)
	private void mostrar() {
		BufferStrategy bs = getBufferStrategy(); // Obtém a estratégia de buffer
		if (bs == null) {
			createBufferStrategy(3); // Cria uma estratégia de 3 buffers (para suavidade)
			return;
		}

		pantalla.limpiar(); // Limpa a tela antes de desenhar

		// Calcula a posição da câmera para centrar o jogador
		int posicionX = jogador.obtenerPosicionX() - (pantalla.obtemAncho() / 2);
		int posicionY = jogador.obtenerPosicionY() - (pantalla.obtemAlto() / 2);

		mapa.mostrar(posicionX, posicionY, pantalla); // Desenha o mapa
		jogador.mostrar(pantalla); // Desenha o jogador

		// Copia os pixels da tela para a imagem de buffer
		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

		Graphics g = bs.getDrawGraphics(); // Obtém o contexto gráfico do buffer
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null); // Desenha a imagem no Canvas

		// Exibe os contadores de APS e FPS
		g.drawString(CONTADOR_APS, 10, 20);
		g.drawString(CONTADOR_FPS, 10, 35);

		g.dispose(); // Libera os recursos gráficos
		bs.show(); // Exibe o próximo buffer
	}

	// Método run do Thread, contém o loop principal do jogo
	@Override
	public void run() {
		final int APS_OBJETIVO = 60; // Atualizações por segundo desejadas
		// Nanosegundos por atualização
		final double NS_POR_ACTUALIZACION = 1000000000.0 / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime(); // Tempo da última atualização
		long referenciaContador = System.nanoTime();     // Tempo da última contagem de APS/FPS

		double tiempoTranscurrido;
		double delta = 0; // Acumulador de tempo para atualizações

		int aps = 0; // Contador de atualizações
		int fps = 0; // Contador de frames

		requestFocus(); // Garante que o Canvas tem o foco para receber eventos de teclado

		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION; // Acumula o tempo para atualizações

			while (delta >= 1) { // Se o delta for 1 ou mais, significa que é hora de uma atualização
				actualizar();
				aps++;
				delta--;
			}

			mostrar(); // Sempre tenta renderizar um frame
			fps++;

			// A cada segundo (1 bilhão de nanosegundos), atualiza os contadores
			if (System.nanoTime() - referenciaContador > 1000000000) {
				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "FPS: " + fps;
				aps = 0; // Reseta o contador de atualizações
				fps = 0; // Reseta o contador de frames
				referenciaContador = System.nanoTime(); // Reseta a referência de tempo
			}
		}
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.iniciar();
	}
}
