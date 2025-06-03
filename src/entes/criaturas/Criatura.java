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

        // Próximas posições do jogador
        int proximaPosicionX = x + desplazamientoX;
        int proximaPosicionY = y + desplazamientoY;

        // Margens da caixa de colisão dentro do sprite
        // AJUSTE ESTES VALORES SE SEUS SPRITES TIVEREM MARGENS DIFERENTES
        int margenIzquierda = 6; 
        int margenDerecho = 26; 
        int margenSuperior = 16; 
        int margenInferior = 30; 

        int pixelEsquerda = proximaPosicionX + margenIzquierda;
        int pixelDireita = proximaPosicionX + margenDerecho;
        int pixelCima = proximaPosicionY + margenSuperior;
        int pixelBaixo = proximaPosicionY + margenInferior;

        // Converter as coordenadas de pixel para coordenadas de tile
        // Cuadro.LADO é o tamanho do tile (ex: 32 pixels)
        int tileEsquerda = pixelEsquerda / Cuadro.LADO; // Corrigido
        int tileDireita = pixelDireita / Cuadro.LADO;   // Corrigido
        int tileCima = pixelCima / Cuadro.LADO;         // Corrigido
        int tileBaixo = pixelBaixo / Cuadro.LADO;       // Corrigido

        // Verificar a solidez dos 4 cantos da caixa de colisão
        if (mapa.obtenerCuadro(tileEsquerda, tileCima).esSolido()) {
            colision = true;
        }
        if (mapa.obtenerCuadro(tileDireita, tileCima).esSolido()) {
            colision = true;
        }
        if (mapa.obtenerCuadro(tileEsquerda, tileBaixo).esSolido()) {
            colision = true;
        }
        if (mapa.obtenerCuadro(tileDireita, tileBaixo).esSolido()) {
            colision = true;
        }

        return colision;
    }

    public Sprite obtenerSprite() {
        return sprite;
    }
}
