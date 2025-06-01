package graficos;

import entes.criaturas.Jogador;
import mapa.cuadro.Cuadro;

public final class Pantalla {

	private final int ancho;
	private final int alto;

	private int diferenciaX; // Offset horizontal para a rolagem do mapa
	private int diferenciaY; // Offset vertical para a rolagem do mapa

	// O array de pixels da tela. Agora é privado e final para garantir que o acesso
	// seja feito de forma controlada através do método 'getPixeles()'.
	private final int[] pixeles;

	// O atributo 'cuadrosCatalogo' foi removido, pois não tinha uso claro
	// dentro desta classe e para simplificar o código.

	/**
	 * Construtor da classe Pantalla.
	 * Inicializa a tela com a largura e altura especificadas.
	 *
	 * @param ancho A largura da tela em pixels.
	 * @param alto A altura da tela em pixels.
	 */
	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;
		this.pixeles = new int[ancho * alto]; // Inicializa o array de pixels da tela
	}

	/**
	 * Limpa todos os pixels da tela, preenchendo-os com a cor preta (0).
	 */
	public void limpar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0; // Cor preta (0x000000)
		}
	}

	/**
	 * Desenha um quadro (tile) na tela.
	 *
	 * @param compensacionX Posição X (em pixels do mundo) de onde o quadro deve ser desenhado.
	 * @param compensacionY Posição Y (em pixels do mundo) de onde o quadro deve ser desenhado.
	 * @param cuadro O objeto Cuadro contendo o sprite a ser desenhado.
	 */
	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
		// Ajusta a compensação pelas diferenças de rolagem da tela
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		// Obtém o lado do sprite (largura/altura) para cálculos
		int ladoSprite = cuadro.sprite.obtemLado();
		
		int pixelX; // Posição X do pixel na tela
		int pixelY; // Posição Y do pixel na tela

		// Itera sobre as linhas do sprite
		for (int y = 0; y < ladoSprite; y++) {
			pixelY = y + compensacionY;

			// Se a linha do sprite estiver completamente fora da tela verticalmente, pula para a próxima
			if (pixelY < 0 || pixelY >= alto) {
				continue;
			}

			// Itera sobre as colunas do sprite
			for (int x = 0; x < ladoSprite; x++) {
				pixelX = x + compensacionX;

				// Se o pixel do sprite estiver completamente fora da tela horizontalmente, pula para o próximo
				if (pixelX < 0 || pixelX >= ancho) {
					continue;
				}
				
				// Copia o pixel do sprite para o array de pixels da tela
				// MUDANÇA AQUI: Agora usando 'cuadro.sprite.getPixeles()'
				pixeles[pixelX + pixelY * ancho] = cuadro.sprite.getPixeles()[x + y * ladoSprite];
			}
		}
	}

	/**
	 * Desenha o jogador na tela, lidando com transparência.
	 *
	 * @param compensacionX Posição X (em pixels do mundo) de onde o jogador deve ser desenhado.
	 * @param compensacionY Posição Y (em pixels do mundo) de onde o jogador deve ser desenhado.
	 * @param jogador O objeto Jogador contendo o sprite atual a ser desenhado.
	 */
	public void mostrarJogador(int compensacionX, int compensacionY, Jogador jogador) {
		// Ajusta a compensação pelas diferenças de rolagem da tela
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		// Obtém o lado do sprite do jogador (largura/altura) para cálculos
		int ladoSpriteJogador = jogador.obtenerSprite().obtemLado();
		
		int pixelX; // Posição X do pixel na tela
		int pixelY; // Posição Y do pixel na tela

		// Itera sobre as linhas do sprite do jogador
		for (int y = 0; y < ladoSpriteJogador; y++) {
			pixelY = y + compensacionY;

			// Se a linha do sprite do jogador estiver completamente fora da tela verticalmente, pula para a próxima
			if (pixelY < 0 || pixelY >= alto) {
				continue;
			}

			// Itera sobre as colunas do sprite do jogador
			for (int x = 0; x < ladoSpriteJogador; x++) {
				pixelX = x + compensacionX;

				// Se o pixel do sprite do jogador estiver completamente fora da tela horizontalmente, pula para o próximo
				if (pixelX < 0 || pixelX >= ancho) {
					continue;
				}
				
				// Obtém a cor do pixel do sprite do jogador
				// MUDANÇA AQUI: Agora usando 'jogador.obtenerSprite().getPixeles()'
				int colorPixelSprite = jogador.obtenerSprite().getPixeles()[x + y * ladoSpriteJogador];
				
				// Verifica se o pixel não é a cor de transparência (definida em Sprite.COLOR_TRANSPARENTE)
				if (colorPixelSprite != Sprite.COLOR_TRANSPARENTE) { // Usando a constante de Sprite
					// Copia o pixel para o array de pixels da tela se não for transparente
					pixeles[pixelX + pixelY * ancho] = colorPixelSprite;
				}
			}
		}
	}

	/**
	 * Define a diferença de rolagem (offset) para a renderização do mapa.
	 * Isso afeta onde os elementos do jogo são desenhados na tela.
	 * @param diferenciaX Offset horizontal.
	 * @param diferenciaY Offset vertical.
	 */
	public void estabeleceDiferencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}

	/**
	 * Retorna a largura da tela.
	 * @return Largura da tela em pixels.
	 */
	public int obtemAncho() {
		return ancho;
	}

	/**
	 * Retorna a altura da tela.
	 * @return Altura da tela em pixels.
	 */
	public int obtemAlto() {
		return alto;
	}
	
	/**
	 * Retorna o array de pixels da tela.
	 * Este método é fornecido para permitir que outras classes (como 'Juego')
	 * acessem o buffer de pixels da tela para operações de cópia, mantendo
	 * o atributo 'pixeles' privado.
	 * @return Array de inteiros representando os pixels da tela.
	 */
	public int[] getPixeles() {
	    return pixeles;
	}
}
