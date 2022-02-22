package graficos;

public class Sprite {
	private final int lado;

	private int x;
	private int y;

	public int[] pixeles;
	private HojaSprites hoja;
	// Colecion de sprites jogador
	public static final Sprite ABAJO0 = new Sprite(32, 1, 1, 0, HojaSprites.jogador);
	public static final Sprite ABAJO0_CIRENE_VERMELHA = new Sprite(32, 1, 1, 0, HojaSprites.jogador);
	public static final Sprite ABAJO0_CIRENA_BRANCA = new Sprite(32, 1, 1, 0, HojaSprites.jogador);
	public static final Sprite ABAJO0_CIRENE_AZUL = new Sprite(32, 1, 1, 0, HojaSprites.jogador);
	public static final Sprite ARRIBA0 = new Sprite(32, 0, 0, 0, HojaSprites.jogador);
	public static final Sprite ARRIBA0_CIRENE_VERMELHA = new Sprite(32, 0, 0, 0, HojaSprites.jogador);
	public static final Sprite ARRIBA0_CIRENA_BRANCA = new Sprite(32, 0, 0, 0, HojaSprites.jogador);
	public static final Sprite ARRIBA0_CIRENE_AZUL = new Sprite(32, 0, 0, 0, HojaSprites.jogador);
	public static final Sprite IZQUIERDA0 = new Sprite(32, 1, 0, 0, HojaSprites.jogador);
	public static final Sprite IZQUIERDA0_CIRENE_VERMELHA = new Sprite(32, 1, 0, 0, HojaSprites.jogador);
	public static final Sprite IZQUIERDA0_CIRENE_BRANCA = new Sprite(32, 1, 0, 0, HojaSprites.jogador);
	public static final Sprite IZQUIERDA0_CIRENE_AZUL = new  Sprite(32, 1, 0, 0, HojaSprites.jogador);
	public static final Sprite DERECHA0 = new Sprite(32, 1, 0, 0, HojaSprites.jogador);
	public static final Sprite DERECHA0_CIRENE_VERMELHA = new Sprite(32, 1, 0, 0, HojaSprites.jogador);
	public static final Sprite DERECHA0_CIRENE_BRANCA = new Sprite(32, 1, 0, 0, HojaSprites.jogador);
	public static final Sprite DERECHA0_CIRENE_AZUL = new Sprite(32, 1, 0, 0, HojaSprites.jogador);
	// FIM Colecion de sprites jogador

	// Colecion de sprites SUBTERRANEO
	
	// FIM Colecion de sprites SUBTERRANEO
	
	
	// Coleccion de sprites
	public static final Sprite VACIO = new Sprite(32, 0x000000);
	public static final Sprite ASFALTO = new Sprite(32, 0, 0, 0, HojaSprites.desierto);
	public static final Sprite CENTRO_CARRETERA = new Sprite(32, 0, 1, 0, HojaSprites.desierto);
	public static final Sprite ASFALTO_DEITADO = new Sprite(32, 0, 2, 0, HojaSprites.desierto);
	public static final Sprite GRAMA = new Sprite(32, 1, 0, 0, HojaSprites.desierto);
	public static final Sprite AREIA = new Sprite(32, 1, 1, 0, HojaSprites.desierto);
	public static final Sprite CASA = new Sprite(32, 2, 1, 0, HojaSprites.desierto);
	public static final Sprite CASA90 = new Sprite(32, 2, 1, 5, HojaSprites.desierto);
	public static final Sprite CASA180 = new Sprite(32, 2, 1, 4, HojaSprites.desierto);
	public static final Sprite CASA270 = new Sprite(32, 2, 1, 3, HojaSprites.desierto);
	public static final Sprite PAREDE_CASA_ALTO_ESQUERDA = new Sprite(32, 2, 2, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_CASA_ALTO_DIREITA = new Sprite(32, 3, 2, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_CASA_BAIXO_ESQUERDA = new Sprite(32, 2, 3, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_CASA_BAIXO_DIREITA = new Sprite(32, 3, 3, 0, HojaSprites.desierto);
	public static final Sprite MURO = new Sprite(32, 0, 3, 0, HojaSprites.desierto);
	public static final Sprite SEGURANCA180 = new Sprite(32, 1, 1, 0, HojaSprites.desierto);
	public static final Sprite SEGURANCA90 = new Sprite(32, 1, 2, 0, HojaSprites.desierto);

	public static final Sprite PAREDE_1 = new Sprite(32, 0, 4, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_2 = new Sprite(32, 0, 5, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_3 = new Sprite(32, 0, 6, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_4 = new Sprite(32, 0, 7, 0, HojaSprites.desierto);

	public static final Sprite PAREDE_5 = new Sprite(32, 1, 4, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_6 = new Sprite(32, 1, 5, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_7 = new Sprite(32, 1, 6, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_8 = new Sprite(32, 1, 7, 0, HojaSprites.desierto);

	public static final Sprite PAREDE_9 = new Sprite(32, 2, 4, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_10 = new Sprite(32, 2, 5, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_11 = new Sprite(32, 2, 6, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_12 = new Sprite(32, 2, 7, 0, HojaSprites.desierto);

	public static final Sprite PAREDE_13 = new Sprite(32, 3, 4, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_14 = new Sprite(32, 3, 5, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_15 = new Sprite(32, 3, 6, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_16 = new Sprite(32, 3, 7, 0, HojaSprites.desierto);

	public static final Sprite PAREDE_17 = new Sprite(32, 4, 4, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_18 = new Sprite(32, 4, 5, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_19 = new Sprite(32, 4, 6, 0, HojaSprites.desierto);
	public static final Sprite PAREDE_20 = new Sprite(32, 4, 7, 0, HojaSprites.desierto);

	// Sprite CASA MADEIRA altura 64 e largura 192 
	public static final Sprite CASA_MADEIRA_ABAIXO_01 = new Sprite(32, 4, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_01 = new Sprite(32, 4, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_02 = new Sprite(32, 5, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_02 = new Sprite(32, 5, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_03 = new Sprite(32, 6, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_03 = new Sprite(32, 6, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_04 = new Sprite(32, 7, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_04 = new Sprite(32, 7, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_05 = new Sprite(32, 8, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_05 = new Sprite(32, 8, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_06 = new Sprite(32, 9, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_06 = new Sprite(32, 9, 6, 0, HojaSprites.desierto);
	// Fim sprite CASA MADEIRA altura 64 e largura 192

	// Sprite CASA MADEIRA altura 64 e largura 192 INVERTER PARA ESQUERDA
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_01 = new Sprite(32, 4, 7, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_01 = new Sprite(32, 4, 6, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_02 = new Sprite(32, 5, 7, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_02 = new Sprite(32, 5, 6, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_03 = new Sprite(32, 6, 7, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_03 = new Sprite(32, 6, 6, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_04 = new Sprite(32, 7, 7, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_04 = new Sprite(32, 7, 6, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_05 = new Sprite(32, 8, 7, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_05 = new Sprite(32, 8, 6, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_06 = new Sprite(32, 9, 7, 5, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_06 = new Sprite(32, 9, 6, 5, HojaSprites.desierto);
	// Fim sprite CASA MADEIRA altura 64 e largura 192 INVERTER PARA ESQUERDA

	// Sprite CASA MADEIRA altura 64 e largura 192 INVERTER PARA DIREITA
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_01 = new Sprite(32, 4, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_01 = new Sprite(32, 4, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_02 = new Sprite(32, 5, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_02 = new Sprite(32, 5, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_03 = new Sprite(32, 6, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_03 = new Sprite(32, 6, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_04 = new Sprite(32, 7, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_04 = new Sprite(32, 7, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_05 = new Sprite(32, 8, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_05 = new Sprite(32, 8, 6, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_06 = new Sprite(32, 9, 7, 0, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_06 = new Sprite(32, 9, 6, 0, HojaSprites.desierto);
	// Fim sprite CASA MADEIRA altura 64 e largura 192 INVERTER PARA DIREITA

	// Sprite CASA CIMENTO altura 64 e largura 192 INVERTER PARA CIMA
	public static final Sprite CASA_CIMENTO_ABAIXO_01 = new Sprite(32, 0, 9, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_01 = new Sprite(32, 0, 8, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_02 = new Sprite(32, 1, 9, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_02 = new Sprite(32, 1, 8, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_03 = new Sprite(32, 2, 9, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_03 = new Sprite(32, 2, 8, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_04 = new Sprite(32, 3, 9, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_04 = new Sprite(32, 3, 8, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_05 = new Sprite(32, 4, 9, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_05 = new Sprite(32, 4, 8, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_06 = new Sprite(32, 5, 9, 3, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_06 = new Sprite(32, 5, 8, 3, HojaSprites.desierto);
	// Fim sprite CASA CIMENTO altura 64 e largura 192 INVERTER PARA CIMA
	// Fim de la collecion

	public Sprite(final int lado, final int coluna, final int fila, final int version, final HojaSprites hoja) {
		this.lado = lado;
		pixeles = new int[lado * lado];
		this.x = coluna * lado;
		this.y = fila * lado;
		this.hoja = hoja;
		cargarSprite(version);
	}

	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
	}

	public int obtemLado() {
		return lado;
	}

	private void cargarSprite(int version) {
		if (version == 0) {
			cargarNormal();
		} else {
			cargaManipulada(version);
		}
	}

	private void cargarNormal() {
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
			}
		}
	}

	private void cargaManipulada(int version) {
		int[] pixelesTemporales = iniciarPixelesTemporales();
		switch (version) {
		case 1:
			invertirX(pixelesTemporales);
			break;
		case 2:
			invertirY(pixelesTemporales);
			break;
		case 3:
			invertirXY(pixelesTemporales);
			break;
		case 4:
			rotar90I(pixelesTemporales);
			break;
		case 5:
			rotar90D(pixelesTemporales);
			break;
		case 6:
			rotarI90InvertirY(pixelesTemporales);
			break;
		case 7:
			rotarD90InvertirY(pixelesTemporales);
			break;
		default:
			cargarNormal();
			break;
		}

	}

	private int[] iniciarPixelesTemporales() {
		int[] pixelesTemporales = new int[lado * lado];
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixelesTemporales[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
			}
		}
		return pixelesTemporales;
	}

	// 1
	private void invertirX(int[] pixelesTemporales) {
		int i = 0;
		for (int y = 0; y < lado; y++) {
			for (int x = lado - 1; x > -1; x--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 2
	private void invertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int y = lado - 1; y > -1; y--) {
			for (int x = 0; x < lado; x++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 3
	private void invertirXY(int[] pixelesTemporales) {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = pixelesTemporales[pixelesTemporales.length - 1 - i];
		}
	}

	// 4
	private void rotar90I(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 5
	private void rotar90D(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = lado - 1; y > -1; y--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 6
	private void rotarI90InvertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 7
	private void rotarD90InvertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = lado; y > -1; y--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}
}
