package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {
	protected int ancho;
	protected int alto;

	protected Cuadro[] cuadrosCatalogo;

	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		this.cuadrosCatalogo = new Cuadro[ancho * alto]; // Inicializa o catálogo aqui se as dimensões forem conhecidas
		generarMapa(); // Chama a implementação da subclasse
	}

	public Mapa(String ruta) {
		// Este construtor existe para ser chamado por MapaCargado.
		// MapaCargado será responsável por chamar carregarMapa(ruta) e definir ancho/alto,
		// e então chamar generarMapa().
		// A inicialização de cuadrosCatalogo e a chamada de generarMapa()
		// devem ser feitas na subclasse MapaCargado.
	}

	protected abstract void generarMapa();

	protected abstract void carregarMapa(String ruta); // Renomeado para consistência com MapaCargado

	public void actualizar() {
		// Implementação pode ser adicionada em subclasses se necessário
	}

	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {
		pantalla.estableceDiferencia(compensacionX, compensacionY);

		int offsetX = compensacionX >> 5; // Equivalente a compensacionX / 32 (LADO do Cuadro)
		int offsetY = compensacionY >> 5; // Equivalente a compensacionY / 32

		// Calcula o final da área visível em termos de tiles, adicionando LADO para garantir que todos os tiles parciais sejam desenhados
		int tileEndX = (compensacionX + pantalla.obtemAncho() + Cuadro.LADO) >> 5;
		int tileEndY = (compensacionY + pantalla.obtemAlto() + Cuadro.LADO) >> 5;

		for (int y = offsetY; y < tileEndY; y++) {
			for (int x = offsetX; x < tileEndX; x++) {
				// Verifica se as coordenadas do tile estão fora dos limites do mapa
				if ((x < 0) || (y < 0) || (x >= ancho) || (y >= alto)) {
					Cuadro.VACIO.mostrar(x, y, pantalla);
				} else {
					// Mostra o quadro correspondente na tela
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}

	public Cuadro obtenerCuadro(final int x, final int y) {
		// Retorna um quadro vazio se as coordenadas estiverem fora dos limites do mapa
		if (x < 0 || y < 0 || x >= ancho || y >= alto) {
			return Cuadro.VACIO;
		}
		return cuadrosCatalogo[x + y * ancho];
	}

	public int obtenerAncho() {
		return ancho;
	}

	public int obtenerAlto() {
		return alto;
	}
}
