package entes.criaturas;

import entes.Ente;
import graficos.Sprite;
import mapa.Mapa;
import mapa.cuadro.Cuadro; // ESSENCIAL: Importação da classe Cuadro

public abstract class Criatura extends Ente {
	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false; // Declarado como protected

	// !!! ATENÇÃO: CONSTRUTOR CORRIGIDO !!!
	public Criatura(Mapa mapa) {
		super(mapa); // Chama o construtor da superclasse Ente
	}
	// !!! FIM DA ATENÇÃO !!!

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
			if (!enColision(desplazamientoX, 0)) {
				modificarPosicionX(desplazamientoX);
			}
			if (!enColision(0, desplazamientoY)) {
				modificarPosicionY(desplazamientoY);
			}
		}
	}
	
	private boolean enColision(int desplazamientoX, int desplazamientoY) {
		[span_4](start_span)boolean colision = false;[span_4](end_span)

		int proximaPosicionX = x + desplazamientoX;
		int proximaPosicionY = y + desplazamientoY;

		// Margens de colisão dentro do sprite da criatura
		// AJUSTE ESTES VALORES SE SEUS SPRITES TIVEREM MARGENS DIFERENTES
		int margenIzquierda = 6; // Ajustado para valores mais comuns
		int margenDerecho = 26; // Ajustado para valores mais comuns
		int margenSuperior = 16; // Ajustado para valores mais comuns
		int margenInferior = 30; // Ajustado para valores mais comuns

		int pixelEsquerda = proximaPosicionX + margenIzquierda;
		int pixelDireita = proximaPosicionX + margenDerecho;
		int pixelCima = proximaPosicionY + margenSuperior;
		int pixelBaixo = proximaPosicionY + margenInferior;

		// Converter as coordenadas de pixel para coordenadas de tile
		// Cuadro.LADO é o tamanho do tile (ex: 32 pixels)
		// Alterar de sprite.obtemLado() para Cuadro.LADO ou sprite.getLado()
		int tileEsquerda = pixelEsquerda / getLado(); [span_5](start_span)// Alterado aqui[span_5](end_span)
		int tileDireita = pixelDireita / getLado(); [span_6](start_span)// Alterado aqui[span_6](end_span)
		int tileCima = pixelCima / getLado(); [span_7](start_span)// Alterado aqui[span_7](end_span)
		int tileBaixo = pixelBaixo / getLado(); [span_8](start_span)// Alterado aqui[span_8](end_span)

		// Verificar a solidez dos 4 cantos da caixa de colisão
		// Assumindo que mapa.obtenerCuadro(x, y) retorna um Cuadro
		// e que Cuadro tem um método esSolido()
		[span_9](start_span)if (mapa.obtenerCuadro(tileEsquerda, tileCima).esSolido()) { // Corrigido para usar mapa.obtenerCuadro[span_9](end_span)
			colision = true;
		}
		[span_10](start_span)if (mapa.obtenerCuadro(tileDireita, tileCima).esSolido()) { // Corrigido para usar mapa.obtenerCuadro[span_10](end_span)
			colision = true;
		}
		[span_11](start_span)if (mapa.obtenerCuadro(tileEsquerda, tileBaixo).esSolido()) { // Corrigido para usar mapa.obtenerCuadro[span_11](end_span)
			colision = true;
		}
		[span_12](start_span)if (mapa.obtenerCuadro(tileDireita, tileBaixo).esSolido()) { // Corrigido para usar mapa.obtenerCuadro[span_12](end_span)
			colision = true;
		}

		return colision;
	}

	public Sprite obtenerSprite() {
		return sprite;
	}
}
