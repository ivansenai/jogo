package mapa.cuadro;

import graficos.Sprite;

public class CuadroVacio extends Cuadro {

    public CuadroVacio(Sprite sprite) {
        super(sprite, true); // Vazio pode ser sólido para representar limites, ou não, dependendo da sua necessidade
    }
}
