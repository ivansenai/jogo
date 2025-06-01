package mapa;

import java.util.Random;
import mapa.cuadro.Cuadro;

public class MapaGenerado extends Mapa {
	private static final Random aleatorio = new Random();

	public MapaGenerado(int ancho, int alto) {
		super(ancho, alto);
	}

	@Override
	protected void generarMapa() {
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				int tipoCuadro = aleatorio.nextInt(4); // Gera um número de 0 a 3

				switch (tipoCuadro) {
					case 0:
						cuadrosCatalogo[x + y * ancho] = Cuadro.ASFALTO;
						break;
					case 1:
						cuadrosCatalogo[x + y * ancho] = Cuadro.AREIA;
						break;
					case 2:
						cuadrosCatalogo[x + y * ancho] = Cuadro.GRAMA;
						break;
					case 3:
						cuadrosCatalogo[x + y * ancho] = Cuadro.CENTRO_CARRETERA;
						break;
					default:
						cuadrosCatalogo[x + y * ancho] = Cuadro.VACIO;
						break;
				}
			}
		}
	}

	@Override
	protected void cargaMapa(String ruta) {
		// Este método não tem implementação para MapaGenerado
	}
}
