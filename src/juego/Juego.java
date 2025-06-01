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

	// Constantes de dimensão da janela do jogo
	private static final int ANCHO = 1024;
	private static final int ALTO = 800;

	// Flag para controlar o estado de execução do jogo
	private static volatile boolean enFuncionamiento = false;

	// Nome da janela do jogo
	private static final String NOMBRE = "Juego";

	// Contadores de atualizações por segundo (APS) e frames por segundo (FPS)
	private static String CONTADOR_APS = "";
	private static String CONTADOR_FPS = "";

	private static int aps = 0;
	private static int fps = 0;

	// Objetos estáticos para a estrutura do jogo
	private static JFrame ventana; // Janela principal
	private static Thread thread; // Thread de execução do jogo
	private static Teclado teclado; // Objeto para controle do teclado
	private static Pantalla pantalla; // Objeto para renderização na tela
	private static Mapa mapa; // Objeto do mapa do jogo
	private static Jogador jogador; // Objeto do jogador

	// Buffer de imagem para renderização
	private static BufferedImage image = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private static int[] pixeles = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	// Ícone da janela do jogo
	private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono/icone.png"));

	/**
	 * Construtor da classe Juego.
	 * Configura a janela, inicializa a tela, o teclado, o mapa e o jogador.
	 */
	private Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO));

		pantalla = new Pantalla(ANCHO, ALTO);

		teclado = new Teclado();
		addKeyListener(teclado); // Adiciona o KeyListener ao Canvas

		// Inicialização do mapa (usando MapaCargado)
		mapa = new MapaCargado("/texturas/mapa.png");
		
		// Inicialização do jogador com posição e sprite inicial
		// ATENÇÃO: Aqui você precisará usar os novos nomes de Sprite. Ex: Sprite.JOGADOR_ABAJO
		// O código original pode estar usando um sprite não mais válido (ex: Sprite.ARRIBA0)
		// Verifique sua classe Jogador e como o sprite é atribuído.
		// Exemplo de como ficaria com o novo nome:
		jogador = new Jogador(mapa, teclado, Sprite.JOGADOR_ARRIBA, 100, 384);


		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão ao fechar a janela
		ventana.setResizable(false); // Impede redimensionamento da janela
		ventana.setAlwaysOnTop(true); // Mantém a janela sempre visível no topo
		ventana.setIconImage(icono.getImage()); // Define o ícone da janela
		ventana.setLayout(new BorderLayout()); // Define o layout da janela
		ventana.add(this, BorderLayout.CENTER); // Adiciona o Canvas ao centro da janela
		ventana.setUndecorated(true); // Remove a barra de título da janela
		ventana.pack(); // Ajusta o tamanho da janela aos seus componentes
		ventana.setLocationRelativeTo(null); // Centraliza a janela na tela
		ventana.setVisible(true); // Torna a janela visível
	}

	/**
	 * Método principal que inicia o jogo.
	 * @param args Argumentos de linha de comando (não utilizados).
	 */
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.iniciar();
	}

	/**
	 * Inicia a thread principal do jogo.
	 */
	public synchronized void iniciar() {
		enFuncionamiento = true;
		thread = new Thread(this, "Graficos"); // Cria uma nova thread para o loop do jogo
		thread.start(); // Inicia a thread
	}

	/**
	 * Detém a thread principal do jogo.
	 */
	public synchronized void detener() {
		enFuncionamiento = false;
		try {
			thread.join(); // Espera a thread terminar
		} catch (InterruptedException e) {
			e.printStackTrace(); // Imprime o stack trace em caso de interrupção
		}
	}

	/**
	 * Atualiza o estado do jogo.
	 * Inclui a atualização do teclado, jogador e outras lógicas de jogo.
	 */
	private void actualizar() {
		teclado.actualizar(); // Atualiza o estado das teclas no objeto Teclado
		jogador.actualizar(); // Atualiza a lógica do jogador

		// Exemplo de como usar os getters do Teclado para lógica de movimento
		// Se você tiver desomentado o bloco do Curso 46, ele deve usar os getters
		// Ex: if (teclado.isArriba()) { ... }
		
		// Verifica se a tecla de saída (ESC) foi pressionada
		if (teclado.isSalir()) { // Usando o getter isSalir() da classe Teclado
			System.exit(0); // Encerra a aplicação
		}
		aps++; // Incrementa o contador de atualizações por segundo
	}

	/**
	 * Renderiza os gráficos do jogo na tela.
	 * Desenha o mapa, o jogador e a interface de usuário (HUD).
	 */
	private void mostrar() {
		BufferStrategy estrategia = getBufferStrategy();
		if (estrategia == null) {
			createBufferStrategy(3); // Cria uma estratégia de buffer (triplo buffer)
			return;
		}
		pantalla.limpar(); // Limpa a tela antes de desenhar

		// Mostra o mapa centralizado na posição do jogador
		mapa.mostrar(jogador.obtenerPosicionX() - pantalla.obtemAncho() / 2 + jogador.obtenerSprite().obtemLado() / 2,
				jogador.obtenerPosicionY() - pantalla.obtemAlto() / 2 + jogador.obtenerSprite().obtemLado() / 2,
				pantalla);
		jogador.mostrar(pantalla); // Mostra o jogador na tela

		// COPIA DOS PIXELS DA PANTALLA PARA O BUFFER DE IMAGEM DA JANELA
		// MUDANÇA AQUI: Agora usando pantalla.getPixeles()
		System.arraycopy(pantalla.getPixeles(), 0, pixeles, 0, pixeles.length);
		
		Graphics g = estrategia.getDrawGraphics(); // Obtém o contexto gráfico

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // Desenha a imagem no Canvas
		g.setColor(Color.white); // Define a cor do texto para branco
		
		// Desenha as informações de contadores e posição do jogador
		g.drawString(CONTADOR_APS, 10, 20);
		g.drawString(CONTADOR_FPS, 10, 35);
		g.drawString("X: " + jogador.obtenerPosicionX(), 10, 50);
		g.drawString("Y: " + jogador.obtenerPosicionY(), 10, 65);
		
		g.dispose(); // Libera os recursos gráficos
		estrategia.show(); // Exibe o próximo buffer disponível
		fps++; // Incrementa o contador de frames por segundo
	}

	/**
	 * Loop principal do jogo.
	 * Gerencia a lógica de atualização (APS) e renderização (FPS) do jogo.
	 */
	public void run() {
		final int NS_POR_SEGUNDO = 1000000000; // Nanosegundos em um segundo
		final byte APS_OBJETIVO = 60; // Atualizações por segundo desejadas
		final double NS_POR_ACTUALIZACION = (double)NS_POR_SEGUNDO / APS_OBJETIVO; // Nanosegundos por atualização

		long referenciaActualizacion = System.nanoTime(); // Marca o tempo da última atualização
		long referenciaContador = System.nanoTime(); // Marca o tempo do último reset do contador
		double tiempoTranscurrido; // Tempo transcorrido desde a última atualização
		double delta = 0; // Acumulador para as atualizações

		requestFocus(); // Garante que o Canvas receba o foco para eventos de teclado
		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime(); // Marca o início do ciclo do loop
			tiempoTranscurrido = inicioBucle - referenciaActualizacion; // Calcula o tempo desde a última referência
			referenciaActualizacion = inicioBucle; // Atualiza a referência de tempo
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION; // Acumula o delta para as atualizações

			// Loop para garantir que as atualizações ocorram na taxa desejada (APS)
			while (delta >= 1) {
				actualizar(); // Chama o método de atualização
				delta--; // Decrementa o delta
			}
			mostrar(); // Chama o método de renderização (mostrar)

			// Atualiza os contadores de APS e FPS a cada segundo
			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "FPS: " + fps;
				aps = 0; // Reseta o contador de APS
				fps = 0; // Reseta o contador de FPS
				referenciaContador = System.nanoTime(); // Atualiza a referência do contador
			}
		}
	}
}
