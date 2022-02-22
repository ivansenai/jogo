package graficos;

import entes.criaturas.Jogador;
import mapa.cuadro.Cuadro;

public final class Pantalla {

	private final int ancho;
	private final int alto;

	private int diferenciaX;
	private int diferenciaY;

	public final int[] pixeles;

	protected Cuadro[] cuadrosCatalogo;

	// Temporal curso 11 apagado no curso 23
	// private final static int LADO_SPRITE = 32;
	// private final static int MASCARA_SPRITE = LADO_SPRITE - 1;
	// fim temporal apagado no curso 23

	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;
		pixeles = new int[ancho * alto];
	}

	public void limpar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;
		}
	}

	// temporal curso 18 apagado no curso 23
	// public void mostrar(final int compensacionX, final int compensacionY) {
	// for (int y = 0; y < alto; y++) {
	// int posicionY = y + compensacionY;
	//
	// if (posicionY < 0 || posicionY >= alto) {
	// continue; // execução comprida deve executar o proximo for
	// }
	//
	// for (int x = 0; x < ancho; x++) {
	// int posicionX = x + compensacionX;
	// if (posicionX < 0 || posicionX >= ancho) {
	// continue;
	// }
	// // Temoporal curso 11
	// pixeles[posicionX + posicionY * ancho] = Sprite.ASFALTO.pixeles[(x &
	// MASCARA_SPRITE)
	// + (y & MASCARA_SPRITE) * LADO_SPRITE];
	// }
	// }
	// }
	// fim temporal curso 18 apagado no curso 23

	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		for (int y = 0; y < cuadro.sprite.obtemLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < cuadro.sprite.obtemLado(); x++) {
				int posicionX = x + compensacionX;
				if (posicionX < -cuadro.sprite.obtemLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;
				}
				if (posicionX < 0) {
					posicionX = 0;
				}
				pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.obtemLado()];
			}
		}
	}

	public void mostrarJogador(int compensacionX, int compensacionY, Jogador jogador) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		for (int y = 0; y < jogador.obtenerSprite().obtemLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < jogador.obtenerSprite().obtemLado(); x++) {
				int posicionX = x + compensacionX;
				if (posicionX < -jogador.obtenerSprite().obtemLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;
				}
				if (posicionX < 0) {
					posicionX = 0;
				}
				// Tempora apagado no episodio 48
				// pixeles[posicionX + posicionY * ancho] =
				// jogador.obtenerSprite().pixeles[x
				// + y * jogador.obtenerSprite().obtemLado()];
				// Fim temporal episodio 48
				int colorPixelJugador = jogador.obtenerSprite().pixeles[x + y * jogador.obtenerSprite().obtemLado()];
				if (colorPixelJugador != 0xffff00ff) {
					pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
				}
			}
		}
	}

	public void estabeleceDiferencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}

	public int obtemAncho() {
		return ancho;
	}

	public int obtemAlto() {
		return alto;
	}

}
