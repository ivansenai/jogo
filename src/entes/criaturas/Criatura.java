package entes.criaturas;

import entes.Ente;
import graficos.Sprite;
import mapa.Mapa; // Import Mapa

public abstract class Criatura extends Ente {
	protected Sprite sprite;
	[span_34](start_span)protected char direccion = 'n';[span_34](end_span)
	[span_35](start_span)protected boolean enMovimiento = false;[span_35](end_span)

	// Add a constructor that takes Mapa as an argument
	public Criatura(Mapa mapa) {
		this.mapa = mapa;
	}

	public void actualizar() {
		// Specific implementation for creatures (if any)
	}

	public void mostrar() {
		// Specific implementation for creatures (if any)
	}

	public void mover(int desplazamientoX, int desplazamientoY) {
		if (desplazamientoX > 0) {
			[span_36](start_span)direccion = 'e';[span_36](end_span)
		}
		if (desplazamientoX < 0) {
			direccion = 'o';
		}
		if (desplazamientoY > 0) {
			[span_37](start_span)direccion = 's';[span_37](end_span)
		}
		if (desplazamientoY < 0) {
			direccion = 'n';
		}
		if (!estaEliminado()) {
			if (!enColision(desplazamientoX, 0)) {
				modificarPosicionX(desplazamientoX);
			}
			if (!enColision(0, desplazamientoY)) {
				[span_38](start_span)modificarPosicionY(desplazamientoY);[span_38](end_span)
			}
		}
	}

	private boolean enColision(int desplazamientoX, int desplazamientoY) {
		[span_39](start_span)boolean colision = false;[span_39](end_span)
		[span_40](start_span)int posicionX = x + desplazamientoX;[span_40](end_span)
		[span_41](start_span)int posicionY = y + desplazamientoY;[span_41](end_span)

		[span_42](start_span)int margenIzquierda = -25;[span_42](end_span)
		[span_43](start_span)int margenDerecho = 30;[span_43](end_span)
		[span_44](start_span)int margenSuperior = -25;[span_44](end_span)
		[span_45](start_span)int margenInferior = 30;[span_45](end_span)

		// Corrected calculations for borders to accurately represent corners of the collision box
		// assuming sprite.obtemLado() gives the tile size
		int ladoSprite = sprite.obtemLado(); // Get the sprite side length once for efficiency

		int bordeIzquierdoPixel = posicionX + margenIzquierda;
		int bordeDerechoPixel = posicionX + margenDerecho;
		int bordeSuperiorPixel = posicionY + margenSuperior;
		int bordeInferiorPixel = posicionY + margenInferior;

		int bordeIzquierdo = bordeIzquierdoPixel / ladoSprite;
		int bordeDerecho = bordeDerechoPixel / ladoSprite;
		int bordeSuperior = bordeSuperiorPixel / ladoSprite;
		int bordeInferior = bordeInferiorPixel / ladoSprite;
		
		// Check all four corners of the creature's collision box
		[span_46](start_span)if (mapa.obtenerCuadroCatalogo(bordeIzquierdo + bordeSuperior * mapa.obtenerAncho()).esSolido()) {[span_46](end_span)
			[span_47](start_span)colision = true;[span_47](end_span)
		}
		if (mapa.obtenerCuadroCatalogo(bordeDerecho + bordeSuperior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadroCatalogo(bordeIzquierdo + bordeInferior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}
		[span_48](start_span)if (mapa.obtenerCuadroCatalogo(bordeDerecho + bordeInferior * mapa.obtenerAncho()).esSolido()) {[span_48](end_span)
			[span_49](start_span)colision = true;[span_49](end_span)
		}
		
		return colision;
	}

	public Sprite obtenerSprite() {
		return sprite;
	}
}
