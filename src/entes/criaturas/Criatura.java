package entes.criaturas;

import entes.Ente;
import graficos.Sprite;
import mapa.Mapa;
import mapa.cuadro.Cuadro; // ESSENCIAL: Importação da classe Cuadro

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
			if (!enColision(desplazamientoX, 0)) {
				modificarPosicionX(desplazamientoX);
			}
			if (!enColision(0, desplazamientoY)) {
				modificarPosicionY(desplazamientoY);
			}
		}
	}
	
	private boolean enColision(int desplazamientoX, int desplazamientoY) {
		boolean colision = false;
		int proximaPosicionX = x + desplazamientoX;
		int proximaPosicionY = y + desplazamientoY;

		// Margens da caixa de colisão do sprite (ajuste conforme o seu sprite)
		// Estes valores dependem de onde a sua caixa de colisão está no sprite
		// EX: Para um sprite de 32x32, uma caixa de 16x16 centrada, as margens seriam:
		// int margenXInicial = 8;  // (32 - 16) / 2
		// int margenXFinal = 24;   // 8 + 16
		// int margenYInicial = 16; // Assumindo base do personagem
		// int margenYFinal = 30;   // 16 + 14 (uma caixa de 14px de altura)
		
		// Usando os valores que você já tinha, mas com a nova lógica
		// AJUSTE ESTES VALORES SE SEUS SPRITES TIVEREM MARGENS DIFERENTES
		int margenXInicial = 8; // Ajustado para ser mais padrao para lado esquerdo
		int margenXFinal = 24;  // Ajustado para ser mais padrao para lado direito
		int margenYInicial = 16; // Ajustado para ser mais padrao para topo
		int margenYFinal = 30; // Ajustado para ser mais padrao para base

		int pixelEsquerdaSuperiorX = proximaPosicionX + margenXInicial;
		int pixelEsquerdaSuperiorY = proximaPosicionY + margenYInicial;

		int pixelDireitaSuperiorX = proximaPosicionX + margenXFinal;
		int pixelDireitaSuperiorY = proximaPosicionY + margenYInicial;

		int pixelEsquerdaInferiorX = proximaPosicionX + margenXInicial;
		int pixelEsquerdaInferiorY = proximaPosicionY + margenYFinal;

		int pixelDireitaInferiorX = proximaPosicionX + margenXFinal;
		int pixelDireitaInferiorY = proximaPosicionY + margenYFinal;

		// Converter para coordenadas de tile
		// Usar sprite.getLado() pois o sprite tem o tamanho do tile (32 pixels)
		int ladoTile = sprite.getLado(); 

		// Verificar a solidez dos 4 cantos da caixa de colisão
		if (mapa.obtenerCuadro(pixelEsquerdaSuperiorX / ladoTile, pixelEsquerdaSuperiorY / ladoTile).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadro(pixelDireitaSuperiorX / ladoTile, pixelDireitaSuperiorY / ladoTile).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadro(pixelEsquerdaInferiorX / ladoTile, pixelEsquerdaInferiorY / ladoTile).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadro(pixelDireitaInferiorX / ladoTile, pixelDireitaInferiorY / ladoTile).esSolido()) {
			colision = true;
		}

		return colision;
	}
}
