package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro { // Não precisa ser abstrata se você instanciar Cuadros diretamente

    // Removido: public final int x;
    // Removido: public final int y;
    public final Sprite sprite;
    private final boolean solido;
    public static final int LADO = 32; // Lado em pixels de cada quadro

    // Constantes de Cuadros (instâncias de Cuadro)
    // CUADRO VACIO agora usa o construtor atualizado de CuadroVacio.
    public static final Cuadro VACIO = new CuadroVacio(Sprite.VACIO, true); // Opcional, pode ser solido ou não, depende da sua lógica
    public static final Cuadro ASFALTO = new CuadroAsfalto(Sprite.ASFALTO, false); // Usando CuadroAsfalto
    public static final Cuadro AREIA = new Cuadro(Sprite.AREIA, false); // Adicionei AREIA
    public static final Cuadro GRAMA = new Cuadro(Sprite.GRAMA, false);
    public static final Cuadro CENTRO_CARRETERA = new Cuadro(Sprite.CENTRO_CARRETERA, false);
    public static final Cuadro ASFALTO_DEITADO = new Cuadro(Sprite.ASFALTO_DEITADO, false);
    // Quadros de Casa
    public static final Cuadro CASA = new Cuadro(Sprite.CASA, true);
    public static final Cuadro CASA90 = new Cuadro(Sprite.CASA90, true);
    public static final Cuadro CASA180 = new Cuadro(Sprite.CASA180, true);
    public static final Cuadro CASA270 = new Cuadro(Sprite.CASA270, true);

    // Quadros de Parede da Casa
    public static final Cuadro PAREDE_CASA_ALTO_ESQUERDA = new Cuadro(Sprite.PAREDE_CASA_ALTO_ESQUERDA, true);
    public static final Cuadro PAREDE_CASA_ALTO_DIREITA = new Cuadro(Sprite.PAREDE_CASA_ALTO_DIREITA, true);
    public static final Cuadro PAREDE_CASA_BAIXO_ESQUERDA = new Cuadro(Sprite.PAREDE_CASA_BAIXO_ESQUERDA, true);
    public static final Cuadro PAREDE_CASA_BAIXO_DIREITA = new Cuadro(Sprite.PAREDE_CASA_BAIXO_DIREITA, true);

    // Quadros de Parede genéricos (1-16)
    public static final Cuadro PAREDE_1 = new Cuadro(Sprite.PAREDE_1, true);
    public static final Cuadro PAREDE_2 = new Cuadro(Sprite.PAREDE_2, true);
    public static final Cuadro PAREDE_3 = new Cuadro(Sprite.PAREDE_3, true);
    public static final Cuadro PAREDE_4 = new Cuadro(Sprite.PAREDE_4, true);
    public static final Cuadro PAREDE_5 = new Cuadro(Sprite.PAREDE_5, true);
    public static final Cuadro PAREDE_6 = new Cuadro(Sprite.PAREDE_6, true);
    public static final Cuadro PAREDE_7 = new Cuadro(Sprite.PAREDE_7, true);
    public static final Cuadro PAREDE_8 = new Cuadro(Sprite.PAREDE_8, true);
    public static final Cuadro PAREDE_9 = new Cuadro(Sprite.PAREDE_9, true);
    public static final Cuadro PAREDE_10 = new Cuadro(Sprite.PAREDE_10, true);
    public static final Cuadro PAREDE_11 = new Cuadro(Sprite.PAREDE_11, true);
    public static final Cuadro PAREDE_12 = new Cuadro(Sprite.PAREDE_12, true);
    public static final Cuadro PAREDE_13 = new Cuadro(Sprite.PAREDE_13, true);
    public static final Cuadro PAREDE_14 = new Cuadro(Sprite.PAREDE_14, true);
    public static final Cuadro PAREDE_15 = new Cuadro(Sprite.PAREDE_15, true);
    public static final Cuadro PAREDE_16 = new Cuadro(Sprite.PAREDE_16, true);

    // Outros elementos
    public static final Cuadro MURO = new Cuadro(Sprite.MURO, true);
    public static final Cuadro SEGURANCA180 = new Cuadro(Sprite.SEGURANCA180, true);
    public static final Cuadro SEGURANCA90 = new Cuadro(Sprite.SEGURANCA90, true);
    // Quadros de Casa de Madeira
    public static final Cuadro CASA_MADEIRA_ABAIXO_01 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_01, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_02 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_02, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_03 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_03, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_04 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_04, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_05 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_05, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_06 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_06, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_01 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_01, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_02 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_02, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_03 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_03, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_04 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_04, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_05 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_05, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_06 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_06, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_ESQUERDA_01 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_ESQUERDA_01, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_ESQUERDA_02 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_ESQUERDA_02, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_ESQUERDA_03 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_ESQUERDA_03, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_ESQUERDA_04 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_ESQUERDA_04, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_ESQUERDA_05 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_ESQUERDA_05, true);
    public static final Cuadro CASA_MADEIRA_ABAIXO_ESQUERDA_06 = new Cuadro(Sprite.CASA_MADEIRA_ABAIXO_ESQUERDA_06, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_ESQUERDA_01 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_ESQUERDA_01, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_ESQUERDA_02 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_ESQUERDA_02, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_ESQUERDA_03 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_ESQUERDA_03, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_ESQUERDA_04 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_ESQUERDA_04, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_ESQUERDA_05 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_ESQUERDA_05, true);
    public static final Cuadro CASA_MADEIRA_ACIMA_ESQUERDA_06 = new Cuadro(Sprite.CASA_MADEIRA_ACIMA_ESQUERDA_06, true);

    // Quadros de Casa de Cimento
    public static final Cuadro CASA_CIMENTO_ABAIXO_01 = new Cuadro(Sprite.CASA_CIMENTO_ABAIXO_01, true);
    public static final Cuadro CASA_CIMENTO_ABAIXO_02 = new Cuadro(Sprite.CASA_CIMENTO_ABAIXO_02, true);
    public static final Cuadro CASA_CIMENTO_ABAIXO_03 = new Cuadro(Sprite.CASA_CIMENTO_ABAIXO_03, true);
    public static final Cuadro CASA_CIMENTO_ABAIXO_04 = new Cuadro(Sprite.CASA_CIMENTO_ABAIXO_04, true);
    public static final Cuadro CASA_CIMENTO_ABAIXO_05 = new Cuadro(Sprite.CASA_CIMENTO_ABAIXO_05, true);
    public static final Cuadro CASA_CIMENTO_ABAIXO_06 = new Cuadro(Sprite.CASA_CIMENTO_ABAIXO_06, true);
    public static final Cuadro CASA_CIMENTO_ACIMA_01 = new Cuadro(Sprite.CASA_CIMENTO_ACIMA_01, true);
    public static final Cuadro CASA_CIMENTO_ACIMA_02 = new Cuadro(Sprite.CASA_CIMENTO_ACIMA_02, true);
    public static final Cuadro CASA_CIMENTO_ACIMA_03 = new Cuadro(Sprite.CASA_CIMENTO_ACIMA_03, true);
    public static final Cuadro CASA_CIMENTO_ACIMA_04 = new Cuadro(Sprite.CASA_CIMENTO_ACIMA_04, true);
    public static final Cuadro CASA_CIMENTO_ACIMA_05 = new Cuadro(Sprite.CASA_CIMENTO_ACIMA_05, true);
    public static final Cuadro CASA_CIMENTO_ACIMA_06 = new Cuadro(Sprite.CASA_CIMENTO_ACIMA_06, true);

    // Construtor
    public Cuadro(Sprite sprite, boolean solido) {
        // Removido: this.x = sprite.getLado();
        // Removido: this.y = sprite.getLado();
        this.sprite = sprite;
        this.solido = solido;
    }

    public void mostrar(int x, int y, Pantalla pantalla) {
        pantalla.mostrarCuadro(x << 5, y << 5, this);
    }

    public boolean esSolido() {
        return solido;
    }
}
