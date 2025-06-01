package graficos;

import entes.criaturas.Jogador;
import mapa.cuadro.Cuadro;

public final class Pantalla {

	private final int ancho;
	private final int alto;

	private int diferenciaX;
	private int diferenciaY;

	// Tornando 'pixeles' privado e fornecendo um getter para acesso controlado
	private final int[] pixeles;

	// Removendo ou explicando o propósito de cuadrosCatalogo, ou removendo se não for usado.
	// Assumindo que não é usado por agora e será removido.
	// protected Cuadro[] cuadrosCatalogo; 

	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;
		this.pixeles = new int[ancho * alto];
	}

	/**
	 * Limpa todos os pixels da tela, preenchendo-os com a cor preta (0).
	 */
	public void limpar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0; // Cor preta
		}
	}

	/**
	 * Desenha um quadro (tile) na tela.
	 *
	 * @param compensacionX Posição X da tela onde o quadro deve ser desenhado.
	 * @param compensacionY Posição Y da tela onde o quadro deve ser desenhado.
	 * @param cuadro O objeto Cuadro contendo o sprite a ser desenhado.
	 */
	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		// Calcula os limites de desenho para evitar ArrayIndexOutOfBoundsException
		int ladoSprite = cuadro.sprite.obtemLado();
		int pixelX;
		int pixelY;

		// Iterar apenas sobre a porção visível do sprite na tela
		for (int y = 0; y < ladoSprite; y++) {
			pixelY = y + compensacionY;

			// Ignora linhas que estão completamente fora da tela verticalmente
			if (pixelY < 0 || pixelY >= alto) {
				continue;
			}

			for (int x = 0; x < ladoSprite; x++) {
				pixelX = x + compensacionX;

				// Ignora pixels que estão completamente fora da tela horizontalmente
				if (pixelX < 0 || pixelX >= ancho) {
					continue;
				}
				
				// Desenha o pixel do sprite na tela
				pixeles[pixelX + pixelY * ancho] = cuadro.sprite.pixeles[x + y * ladoSprite];
			}
		}
	}

	/**
	 * Desenha o jogador na tela, lidando com transparência.
	 *
	 * @param compensacionX Posição X da tela onde o jogador deve ser desenhado.
	 * @param compensacionY Posição Y da tela onde o jogador deve ser desenhado.
	 * @param jogador O objeto Jogador contendo o sprite atual.
	 */
	public void mostrarJogador(int compensacionX, int compensacionY, Jogador jogador) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		// Calcula os limites de desenho para evitar ArrayIndexOutOfBoundsException
		int ladoSpriteJogador = jogador.obtenerSprite().obtemLado();
		int pixelX;
		int pixelY;

		// Iterar apenas sobre a porção visível do sprite do jogador na tela
		for (int y = 0; y < ladoSpriteJogador; y++) {
			pixelY = y + compensacionY;

			// Ignora linhas que estão completamente fora da tela verticalmente
			if (pixelY < 0 || pixelY >= alto) {
				continue;
			}

			for (int x = 0; x < ladoSpriteJogador; x++) {
				pixelX = x + compensacionX;

				// Ignora pixels que estão completamente fora da tela horizontalmente
				if (pixelX < 0 || pixelX >= ancho) {
					continue;
				}
				
				int colorPixelSprite = jogador.obtenerSprite().pixeles[x + y * ladoSpriteJogador];
				
				// Verifica se o pixel não é a cor de transparência (magenta)
				// É uma boa prática definir esta cor como uma constante em Sprite ou em uma classe de constantes de cores
				if (colorPixelSprite != Sprite.COLOR_TRANSPARENTE) { // **MUDANÇA AQUI: usando constante**
					pixeles[pixelX + pixelY * ancho] = colorPixelSprite;
				}
			}
		}
	}

	/**
	 * Define a diferença de rolagem (offset) para a renderização do mapa.
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
	 * Este método permite que outras classes acessem os pixels para operações como cópia.
	 * @return Array de inteiros representando os pixels da tela.
	 */
	public int[] getPixeles() { // **MUDANÇA AQUI: Novo getter para pixeles**
	    return pixeles;
	}
}
