package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {
	protected int ancho;
	protected int alto;

	protected int[] cuadros;
	protected Cuadro[] cuadrosCatalogo;

	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		cuadros = new int[ancho * alto];
		generarMapa();
	}

	public Mapa(String ruta) {
		cargaMapa(ruta);
		generarMapa();
	}

	protected void generarMapa() {

	}

	protected void cargaMapa(String ruta) {
	}

	public void actualizar() {
	}

	public void mostrar(final int compensiacionX, final int compensiacionY, final Pantalla pantalla) {
		pantalla.estabeleceDiferencia(compensiacionX, compensiacionY);
		// / 32;
		// >> 5;
		// Similar ao codigo abaixo
		int o = compensiacionX >> 5; // Dividir tamanho dos sprites
		int e = (compensiacionX + pantalla.obtemAncho() + Cuadro.LADO) >> 5;
		int n = compensiacionY >> 5;
		int s = (compensiacionY + pantalla.obtemAlto() + Cuadro.LADO) >> 5;
		// Fim Similar

		for (int y = n; y < s; y++) {
			for (int x = o; x < e; x++) {
				// Curso 31 Comentado
				// obtemCuadro(x, y).mostrar(x, y, pantalla);
				// Curso 31 Fim
				if ((x < 0) || (y < 0) || (x >= ancho) || (y >= alto)) {
					Cuadro.VACIO.mostrar(x, y, pantalla);
				} else {
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}

	public Cuadro obtemCuadro(final int x, final int y) {
		if (x < 0 || y < 0 || x >= ancho || y >= alto) {
			return Cuadro.VACIO;
		}
		switch (cuadros[x + y * ancho]) {
		case 0:
			return Cuadro.ASFALTO;
		case 1:
			return Cuadro.AREIA;
		case 2:
			return Cuadro.GRAMA;
		case 3:
			return Cuadro.CENTRO_CARRETERA;
		default:
			return Cuadro.VACIO;
		}
	}

	public Cuadro obtenerCuadroCatalogo(int posicion) {
		return cuadrosCatalogo[posicion];
	}

	public int obtenerAncho() {
		return ancho;
	}
}
