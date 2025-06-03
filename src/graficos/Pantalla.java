package graficos;

import entes.criaturas.Jogador;
import mapa.cuadro.Cuadro;

public final class Pantalla {
    private final int ancho;
    private final int alto;
    private int diferenciaX;
    private int diferenciaY;
    public final int[] pixeles;

    public Pantalla(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.pixeles = new int[ancho * alto];
    }

    public void limpiar() {
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = 0;
        }
    }

    public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;

        for (int y = 0; y < cuadro.sprite.getLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < cuadro.sprite.getLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -cuadro.sprite.getLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    continue;
                }
                if (cuadro.sprite.pixeles[x + y * cuadro.sprite.getLado()] != Sprite.COLOR_TRANSPARENTE) {
                    pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.getLado()];
                }
            }
        }
    }

    public void mostrarJogador(int compensacionX, int compensacionY, Jogador jogador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;

        for (int y = 0; y < jogador.obtenerSprite().getLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jogador.obtenerSprite().getLado(); x++) {
                int posicionX = x + compensacionX;

                if (posicionX < -jogador.obtenerSprite().getLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    continue;
                }

                int colorPixelSprite = jogador.obtenerSprite().pixeles[x + y * jogador.obtenerSprite().getLado()];

                if (colorPixelSprite != Sprite.COLOR_TRANSPARENTE) {
                    pixeles[posicionX + posicionY * ancho] = colorPixelSprite;
                }
            }
        }
    }

    public void estableceDiferencia(fint diferenciaX, int diferenciaY) {
        this.diferenciaX = diferenciaX;
        this.diferenciaY = diferenciaY;
    }

    public int obtemAncho() {
        return ancho;
    }

    public int obtemAlto() {
        return alto;
    }
}
