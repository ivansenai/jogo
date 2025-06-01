package mapa.cuadro;

import graficos.Sprite;

public class CuadroAsfalto extends Cuadro {

    public CuadroAsfalto(Sprite sprite) {
        super(sprite, false); // Asfalto geralmente não é sólido
    }
}
