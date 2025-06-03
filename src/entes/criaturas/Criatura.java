package entes.criaturas;

import entes.Ente;
import graficos.Sprite; // Certifique-se de que o pacote graficos existe
import mapa.Mapa;
import mapa.cuadro.Cuadro; // Importação essencial para Cuadro.LADO e Cuadro.esSolido()

public abstract class Criatura extends Ente {
	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false;

	public Criatura(Mapa mapa) {
		super(mapa); // Chama o construtor da superclasse Ente
	}

	@Override
	public void actualizar() {
		// A implementação da lógica de atualização será nas subclasses (ex: Jogador)
	}

	@Override
	public void mostrar() {
		// A implementação da lógica de exibição será nas subclasses
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

		// Margens de colisão dentro do sprite da criatura (ajuste conforme seu sprite)
		int margenIzquierda = 6;
		int margenDerecho = 26;
		int margenSuperior = 16;
		int margenInferior = 30;

		// Calcular os pixels dos 4 cantos da caixa de colisão da criatura
		int pixelEsquerda = proximaPosicionX + margenIzquierda;
		int pixelDireita = proximaPosicionX + margenDerecho;
		int pixelCima = proximaPosicionY + margenSuperior;
		int pixelBaixo = proximaPosicionY + margenInferior;

		// Converter as coordenadas de pixel para coordenadas de tile
		// Assumimos que Cuadro.LADO é o tamanho em pixels de um tile (e.g., 32)
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
