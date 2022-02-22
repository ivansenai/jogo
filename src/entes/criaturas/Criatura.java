package entes.criaturas;

import entes.Ente;
import graficos.Sprite;

public abstract class Criatura extends Ente {
	protected Sprite sprite;
	protected char direccion = 'n';

	protected boolean enMovimiento = false;

	public void actualizar() {

	}

	public void mostrar() {

	}

	public void mover(int desplazamientoX, int desplazamientoY) {
		if (desplazamientoX > 0) {
			direccion = 'e';
		}
		if (desplazamientoX < 0) {
			direccion = 'o';
		}
		if (desplazamientoY > 0) {
			direccion = 's';
		}
		if (desplazamientoY < 0) {
			direccion = 'n';
		}
		if (!estaEliminado()) {
			if (!enColision(desplazamientoX, 0)) {
				modificarPosicionX(desplazamientoX);
			}
			if (!enColision(0, desplazamientoY)) {
				modificarPosicionY(desplazamientoY);
			}
			// Comentado no curso 52
			// modificarPosicionX(desplazamientoX);
			// modificarPosicionY(desplazamientoY);
			// Fim comentado no curso 52
		}
	}

	private boolean enColision(int desplazamientoX, int desplazamientoY) {
		boolean colision = false;
		int posicionX = x + desplazamientoX;
		int posicionY = y + desplazamientoY;

		int margenIzquierda = -25;
		int margenDerecho = 30;
		int margenSuperior = -25;
		int margenInferior = 30;

		int bordeIzquierdo = (posicionX + margenDerecho) / sprite.obtemLado();
		int bordeDerecho =  (posicionX + margenDerecho + margenIzquierda) / sprite.obtemLado();  
		int bordeSuperior =  (posicionY + margenInferior) / sprite.obtemLado();  
		int bordeInferior =  (posicionY + margenInferior + margenSuperior) / sprite.obtemLado();  
		if (mapa.obtenerCuadroCatalogo(bordeIzquierdo + bordeSuperior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadroCatalogo(bordeIzquierdo + bordeInferior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadroCatalogo(bordeDerecho + bordeSuperior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadroCatalogo(bordeDerecho + bordeInferior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}
		
		
		
		return colision;
	}

	public Sprite obtenerSprite() {
		return sprite;
	}
}
