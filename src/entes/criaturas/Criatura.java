package entes.criaturas;

import entes.Ente;
import graficos.Sprite;
import mapa.Mapa;
import mapa.cuadro.Cuadro; // ESSENCIAL: Importação da classe Cuadro

public abstract class Criatura extends Ente {
	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false; // Declarado como protected

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

    // Margens da caixa de colisão (ajuste conforme o design do seu sprite)
    int margenXInicial = 8; // ex: 8 pixels da esquerda do sprite
    int margenXFinal = 24; // ex: 24 pixels da esquerda do sprite (largura da caixa: 24-8 = 16px)
    int margenYInicial = 16; // ex: 16 pixels do topo do sprite
    int margenYFinal = 30; // ex: 30 pixels do topo do sprite (altura da caixa: 30-16 = 14px)

    // Coordenadas em pixels dos 4 cantos da *próxima* caixa de colisão
    int pixelEsquerdaSuperiorX = proximaPosicionX + margenXInicial;
    int pixelEsquerdaSuperiorY = proximaPosicionY + margenYInicial;

    int pixelDireitaSuperiorX = proximaPosicionX + margenXFinal;
    int pixelDireitaSuperiorY = proximaPosicionY + margenYInicial;

    int pixelEsquerdaInferiorX = proximaPosicionX + margenXInicial;
    int pixelEsquerdaInferiorY = proximaPosicionY + margenYFinal;

    int pixelDireitaInferiorX = proximaPosicionX + margenXFinal;
    int pixelDireitaInferiorY = proximaPosicionY + margenYFinal;

    // Converter para coordenadas de tile
    // Usar Cuadro.LADO para dividir, se for o lado do tile, ou sprite.getLado() se o sprite é o tile
    // Assumindo que sprite.getLado() é o tamanho do tile (32 pixels)
    int ladoTile = sprite.getLado(); // Ou Cuadro.LADO se for constante em Cuadro

    // Verificar os 4 pontos de colisão
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


	public Sprite obtenerSprite() {
		return sprite;
	}
}
