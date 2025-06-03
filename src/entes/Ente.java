package entes;

import mapa.Mapa;

public abstract class Ente {
	protected int x;
	protected int y;

	private boolean eliminado = false;
	[span_50](start_span)protected Mapa mapa;[span_50](end_span)

	// Constructor to initialize mapa
	public Ente(Mapa mapa) {
		this.mapa = mapa;
	}

	public void actualizar() {

	}

	public void mostrar() {

	}

	public void eliminar() {
		eliminado = true;
	}

	public int obtenerPosicionX() {
		[span_51](start_span)return x;[span_51](end_span)
	}

	public void modificarPosicionX(int desplazamientoX) {
		x += desplazamientoX;
	}

	public int obtenerPosicionY() {
		return y;
	}

	public void modificarPosicionY(int desplazamientoY) {
		[span_52](start_span)y += desplazamientoY;[span_52](end_span)
	}

	public boolean estaEliminado() {
		return eliminado;
	}
}
