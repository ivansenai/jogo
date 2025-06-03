package mapa.cuadro;

import graficos.Sprite;

public class CuadroVacio extends Cuadro {

    public CuadroVacio(Sprite sprite, boolean solido) { // Adicionado parâmetro 'solido'
        super(sprite, solido);
        // Vazio pode ser sólido para representar limites, ou não, dependendo da sua necessidade
    }
}
