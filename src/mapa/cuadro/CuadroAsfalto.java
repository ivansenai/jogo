package mapa.cuadro;

import graficos.Sprite;

public class CuadroAsfalto extends Cuadro {

    public CuadroAsfalto(Sprite sprite, boolean solido) {
        super(sprite, solido); // Asfalto geralmente não é sólido
    }
}
