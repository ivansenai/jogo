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
		this.cuadrosCatalogo = new Cuadro[ancho * alto];
		generarMapa();
	}

	public Mapa(String ruta) {
		cargaMapa(ruta);
		// generarMapa() será chamado no construtor de MapaCargado após cargaMapa() definir ancho/alto
	}

	protected abstract void generarMapa();

	protected abstract void cargaMapa(String ruta);

	public void actualizar() {
	}

	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {
		pantalla.estableceDiferencia(compensacionX, compensacionY);

		int offsetX = compensacionX >> 5;
		int offsetY = compensacionY >> 5;

		int tileEndX = (compensacionX + pantalla.obtemAncho() + Cuadro.LADO) >> 5;
		int tileEndY = (compensacionY + pantalla.obtemAlto() + Cuadro.LADO) >> 5;

		for (int y = offsetY; y < tileEndY; y++) {
			for (int x = offsetX; x < tileEndX; x++) {
				if ((x < 0) || (y < 0) || (x >= ancho) || (y >= alto)) {
					Cuadro.VACIO.mostrar(x, y, pantalla);
				} else {
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}

	public Cuadro obtenerCuadro(final int x, final int y) {
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
