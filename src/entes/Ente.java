package entes;

import mapa.Mapa; // Certifique-se de que o pacote mapa existe

public abstract class Ente {
	protected int x;
	protected int y;

	private boolean eliminado = false;
	protected Mapa mapa;

	// Construtor para inicializar o mapa
	public Ente(Mapa mapa) {
		this.mapa = mapa;
	}

	public void actualizar() {
		// As subclasses devem implementar a lógica de atualização
	}

	public void mostrar() {
		// As subclasses devem implementar a lógica de exibição
	}

	public void eliminar() {
		eliminado = true;
	}

	public int obtenerPosicionX() {
		return x;
	}

	public void modificarPosicionX(int desplazamientoX) {
		x += desplazamientoX;
	}

	public int obtenerPosicionY() {
		return y;
	}

	public void modificarPosicionY(int desplazamientoY) {
		y += desplazamientoY;
	}

	public boolean estaEliminado() {
		return eliminado;
	}
}
