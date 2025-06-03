package mapa;

import java.util.Random;
import mapa.cuadro.Cuadro;

[span_54](start_span)public class MapaGenerado extends Mapa { //[span_54](end_span)
	private static final Random aleatorio = new Random();

	[span_55](start_span)public MapaGenerado(int ancho, int alto) { //[span_55](end_span)
		super(ancho, alto);
	}

	@Override
	protected void generarMapa() {
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				int tipoCuadro = aleatorio.nextInt(4); [span_56](start_span)// Gera um número de 0 a 3[span_56](end_span)

				switch (tipoCuadro) {
					case 0:
						cuadrosCatalogo[x + y * ancho] = Cuadro.ASFALTO;
						break;
					[span_57](start_span)case 1: //[span_57](end_span)
						cuadrosCatalogo[x + y * ancho] = Cuadro.AREIA;
						break;
					case 2:
						cuadrosCatalogo[x + y * ancho] = Cuadro.GRAMA;
						break;
					[span_58](start_span)case 3: //[span_58](end_span)
						cuadrosCatalogo[x + y * ancho] = Cuadro.CENTRO_CARRETERA;
						break;
					default:
						cuadrosCatalogo[x + y * ancho] = Cuadro.VACIO;
						break;
				[span_59](start_span)} //[span_59](end_span)
			}
		}
	}

	[span_60](start_span)@Override //[span_60](end_span) This is correct if Mapa has an abstract method `cargaMapa(String ruta)`
	[span_61](start_span)protected void cargaMapa(String ruta) { //[span_61](end_span)
		// Este método não tem implementação para MapaGenerado
		// It's intentionally empty for a generated map, as it's not loaded from a file
	}
}
