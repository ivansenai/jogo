package entes.criaturas;

import entes.Ente;
import graficos.Sprite;
import mapa.Mapa;

public abstract class Criatura extends Ente {
	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false;

	public Criatura(Mapa mapa) {
		super(mapa); // Chama o construtor da superclasse Ente
	}

	@Override
	public void actualizar() {
		// Implementação pode ser adicionada em subclasses se necessário
	}

	@Override
	public void mostrar() {
		// Implementação pode ser adicionada em subclasses se necessário
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
			// Verifica colisão apenas no eixo X antes de mover em X
			if (!enColision(desplazamientoX, 0)) {
				modificarPosicionX(desplazamientoX);
			}
			// Verifica colisão apenas no eixo Y antes de mover em Y
			if (!enColision(0, desplazamientoY)) {
				modificarPosicionY(desplazamientoY);
			}
		}
	}

	private boolean enColision(int desplazamientoX, int desplazamientoY) {
		boolean colision = false;

		// Posição futura do ente
		int proximaPosicionX = x + desplazamientoX;
		int proximaPosicionY = y + desplazamientoY;

		// Margens de colisão dentro do sprite da criatura
		// Ajuste esses valores conforme o tamanho real da sua criatura e o que você
		// considera o ponto de colisão.
		// Exemplo: se o sprite tem 32x32 e você quer a base do meio, pode ajustar.
		int margenIzquierda = 6;  // Ponto de colisão x do lado esquerdo do sprite
		int margenDerecho = 26;   // Ponto de colisão x do lado direito do sprite (32 - 6)
		int margenSuperior = 16;  // Ponto de colisão y do lado superior do sprite
		int margenInferior = 30;  // Ponto de colisão y do lado inferior do sprite (32 - 2)

		// Calcular os pixels dos 4 cantos da caixa de colisão da criatura
		int pixelEsquerda = proximaPosicionX + margenIzquierda;
		int pixelDireita = proximaPosicionX + margenDerecho;
		int pixelCima = proximaPosicionY + margenSuperior;
		int pixelBaixo = proximaPosicionY + margenInferior;

		// Converter as coordenadas de pixel para coordenadas de tile
		// Asumimos que Cuadro.LADO é o tamanho em pixels de um tile (e.g., 32)
		int tileEsquerda = pixelEsquerda / Cuadro.LADO;
		int tileDireita = pixelDireita / Cuadro.LADO;
		int tileCima = pixelCima / Cuadro.LADO;
		int tileBaixo = pixelBaixo / Cuadro.LADO;

		// Verificar a solidez dos 4 cantos da caixa de colisão
		// Canto superior esquerdo
		if (mapa.obtenerCuadro(tileEsquerda, tileCima).esSolido()) {
			colision = true;
		}
		// Canto superior direito
		if (mapa.obtenerCuadro(tileDireita, tileCima).esSolido()) {
			colision = true;
		}
		// Canto inferior esquerdo
		if (mapa.obtenerCuadro(tileEsquerda, tileBaixo).esSolido()) {
			colision = true;
		}
		// Canto inferior direito
		if (mapa.obtenerCuadro(tileDireita, tileBaixo).esSolido()) {
			colision = true;
		}

		return colision;
	}

	public Sprite obtenerSprite() {
		return sprite;
	}
}
