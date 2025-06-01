package graficos;

public final class Sprite {

    private final int lado;
    private int x;
    private int y;
    public final int[] pixeles;
    private final HojaSprites hoja;

    public static final int COLOR_TRANSPARENTE = 0xffff00ff;

    public enum Version {
        NORMAL(0), INVERTIR_X(1), INVERTIR_Y(2), INVERTIR_XY(3), ROTAR_90_ESQ(4), ROTAR_90_DIR(5),
        ROTAR_90_ESQ_INVERTIR_Y(6), ROTAR_90_DIR_INVERTIR_Y(7);
        private final int id;

        Version(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    // SPRITES DO JOGADOR
    public static final Sprite JOGADOR_CIMA = new Sprite(32, 0, 0, HojaSprites.jogador);
    public static final Sprite JOGADOR_BAIXO = new Sprite(32, 0, 1, HojaSprites.jogador);
    public static final Sprite JOGADOR_ESQUERDA = new Sprite(32, 0, 2, HojaSprites.jogador);
    public static final Sprite JOGADOR_DIREITA = new Sprite(32, 0, 3, HojaSprites.jogador);

    public static final Sprite JOGADOR_CIMA_1 = new Sprite(32, 1, 0, HojaSprites.jogador);
    public static final Sprite JOGADOR_CIMA_2 = new Sprite(32, 2, 0, HojaSprites.jogador);
    public static final Sprite JOGADOR_BAIXO_1 = new Sprite(32, 1, 1, HojaSprites.jogador);
    public static final Sprite JOGADOR_BAIXO_2 = new Sprite(32, 2, 1, HojaSprites.jogador);
    public static final Sprite JOGADOR_ESQUERDA_1 = new Sprite(32, 1, 2, HojaSprites.jogador);
    public static final Sprite JOGADOR_ESQUERDA_2 = new Sprite(32, 2, 2, HojaSprites.jogador);
    public static final Sprite JOGADOR_DIREITA_1 = new Sprite(32, 1, 3, HojaSprites.jogador);
    public static final Sprite JOGADOR_DIREITA_2 = new Sprite(32, 2, 3, HojaSprites.jogador);


    // SPRITES DO MAPA
    public static final Sprite VACIO = new Sprite(32, 0x000000);
    public static final Sprite ASFALTO = new Sprite(32, 0, 0, HojaSprites.ladrilho);
    public static final Sprite AREIA = new Sprite(32, 1, 0, HojaSprites.ladrilho);
    public static final Sprite GRAMA = new Sprite(32, 2, 0, HojaSprites.ladrilho);
    public static final Sprite CENTRO_CARRETERA = new Sprite(32, 3, 0, HojaSprites.ladrilho);
    public static final Sprite ASFALTO_DEITADO = new Sprite(32, 4, 0, HojaSprites.ladrilho);

    // SPRITES DA CASA
    public static final Sprite CASA = new Sprite(32, 0, 0, HojaSprites.casa);
    public static final Sprite CASA90 = new Sprite(32, 1, 0, HojaSprites.casa);
    public static final Sprite CASA180 = new Sprite(32, 2, 0, HojaSprites.casa);
    public static final Sprite CASA270 = new Sprite(32, 3, 0, HojaSprites.casa);

    public static final Sprite PAREDE_CASA_ALTO_ESQUERDA = new Sprite(32, 0, 1, HojaSprites.casa);
    public static final Sprite PAREDE_CASA_ALTO_DIREITA = new Sprite(32, 1, 1, HojaSprites.casa);
    public static final Sprite PAREDE_CASA_BAIXO_ESQUERDA = new Sprite(32, 2, 1, HojaSprites.casa);
    public static final Sprite PAREDE_CASA_BAIXO_DIREITA = new Sprite(32, 3, 1, HojaSprites.casa);

    // SPRITES DE PAREDE GENÉRICOS (1-16)
    public static final Sprite PAREDE_1 = new Sprite(32, 0, 0, HojaSprites.paredes);
    public static final Sprite PAREDE_2 = new Sprite(32, 1, 0, HojaSprites.paredes);
    public static final Sprite PAREDE_3 = new Sprite(32, 2, 0, HojaSprites.paredes);
    public static final Sprite PAREDE_4 = new Sprite(32, 3, 0, HojaSprites.paredes);
    public static final Sprite PAREDE_5 = new Sprite(32, 4, 0, HojaSprites.paredes);
    public static final Sprite PAREDE_6 = new Sprite(32, 5, 0, HojaSprites.paredes);
    public static final Sprite PAREDE_7 = new Sprite(32, 6, 0, HojaSprites.paredes);
    public static final Sprite PAREDE_8 = new Sprite(32, 7, 0, HojaSprites.paredes);
    public static final Sprite PAREDE_9 = new Sprite(32, 0, 1, HojaSprites.paredes);
    public static final Sprite PAREDE_10 = new Sprite(32, 1, 1, HojaSprites.paredes);
    public static final Sprite PAREDE_11 = new Sprite(32, 2, 1, HojaSprites.paredes);
    public static final Sprite PAREDE_12 = new Sprite(32, 3, 1, HojaSprites.paredes);
    public static final Sprite PAREDE_13 = new Sprite(32, 4, 1, HojaSprites.paredes);
    public static final Sprite PAREDE_14 = new Sprite(32, 5, 1, HojaSprites.paredes);
    public static final Sprite PAREDE_15 = new Sprite(32, 6, 1, HojaSprites.paredes);
    public static final Sprite PAREDE_16 = new Sprite(32, 7, 1, HojaSprites.paredes);

    // OUTROS ELEMENTOS
    public static final Sprite MURO = new Sprite(32, 0, 0, HojaSprites.muro);
    public static final Sprite SEGURANCA180 = new Sprite(32, 0, 0, HojaSprites.seguranca);
    public static final Sprite SEGURANCA90 = new Sprite(32, 1, 0, HojaSprites.seguranca);

    // SPRITES DE CASA DE MADEIRA
    public static final Sprite CASA_MADEIRA_ABAIXO_01 = new Sprite(32, 0, 0, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_02 = new Sprite(32, 1, 0, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_03 = new Sprite(32, 2, 0, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_04 = new Sprite(32, 3, 0, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_05 = new Sprite(32, 4, 0, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_06 = new Sprite(32, 5, 0, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_01 = new Sprite(32, 0, 1, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_02 = new Sprite(32, 1, 1, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_03 = new Sprite(32, 2, 1, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_04 = new Sprite(32, 3, 1, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_05 = new Sprite(32, 4, 1, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_06 = new Sprite(32, 5, 1, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_01 = new Sprite(32, 0, 2, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_02 = new Sprite(32, 1, 2, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_03 = new Sprite(32, 2, 2, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_04 = new Sprite(32, 3, 2, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_05 = new Sprite(32, 4, 2, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_06 = new Sprite(32, 5, 2, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_01 = new Sprite(32, 0, 3, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_02 = new Sprite(32, 1, 3, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_03 = new Sprite(32, 2, 3, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_04 = new Sprite(32, 3, 3, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_05 = new Sprite(32, 4, 3, HojaSprites.casaMadeira);
    public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_06 = new Sprite(32, 5, 3, HojaSprites.casaMadeira);

    // SPRITES DE CASA DE CIMENTO
    public static final Sprite CASA_CIMENTO_ABAIXO_01 = new Sprite(32, 0, 0, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ABAIXO_02 = new Sprite(32, 1, 0, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ABAIXO_03 = new Sprite(32, 2, 0, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ABAIXO_04 = new Sprite(32, 3, 0, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ABAIXO_05 = new Sprite(32, 4, 0, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ABAIXO_06 = new Sprite(32, 5, 0, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ACIMA_01 = new Sprite(32, 0, 1, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ACIMA_02 = new Sprite(32, 1, 1, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ACIMA_03 = new Sprite(32, 2, 1, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ACIMA_04 = new Sprite(32, 3, 1, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ACIMA_05 = new Sprite(32, 4, 1, HojaSprites.casaCimento);
    public static final Sprite CASA_CIMENTO_ACIMA_06 = new Sprite(32, 5, 1, HojaSprites.casaCimento);


    public Sprite(final int lado, final int coluna, final int linha, final HojaSprites hoja) {
        this.lado = lado;
        this.pixeles = new int[lado * lado];
        this.x = coluna * lado;
        this.y = linha * lado;
        this.hoja = hoja;
        cargar();
    }

    public Sprite(final int lado, final int cor) {
        this.lado = lado;
        this.pixeles = new int[lado * lado];
        this.x = 0;
        this.y = 0;
        this.hoja = null;
        rellenar(cor);
    }

    private void cargar() {
        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixeles[x + y * lado] = hoja.getPixeles()[(x + this.x) + (y + this.y) * hoja.getAncho()];
            }
        }
    }

    private void rellenar(final int cor) {
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = cor;
        }
    }

    public int getLado() {
        return lado;
    }
}
