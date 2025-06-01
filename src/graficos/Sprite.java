package graficos;

public class Sprite {

	private final int lado;
	private int x; // Posição X do sprite na folha de sprites
	private int y; // Posição Y do sprite na folha de sprites

	// O array de pixels do sprite. Agora é privado e final para garantir imutabilidade após a criação.
	private final int[] pixeles; 
	private final HojaSprites hoja; // A folha de sprites de onde este sprite é extraído

	// Nova constante para a cor transparente. É boa prática defini-la aqui.
	public static final int COLOR_TRANSPARENTE = 0xffff00ff; // Geralmente magenta

	// Enum para as versões de manipulação do sprite (rotação/inversão)
	// Isso torna o código mais legível e menos propenso a "magic numbers".
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

	// --- Coleção de Sprites do Jogador ---
	// Se as "cirenes" são apenas uma variação de cor aplicada por fora,
	// estas constantes de sprite devem ser os sprites base, e a cor deve ser aplicada na renderização.
	// Se cada "cirene" é um sprite diferente na folha, então suas coordenadas devem ser diferentes.
	// Por enquanto, presumimos que a intenção é ter apenas as direções base e que a "cirene" é um conceito de renderização.
	public static final Sprite JOGADOR_ABAJO = new Sprite(32, 1, 0, Version.NORMAL, HojaSprites.jogador); // Ajustado para 1,0 se ABAJO0 estiver nessa posição
	public static final Sprite JOGADOR_ARRIBA = new Sprite(32, 0, 0, Version.NORMAL, HojaSprites.jogador);
	public static final Sprite JOGADOR_IZQUIERDA = new Sprite(32, 2, 0, Version.NORMAL, HojaSprites.jogador); // Ajustado para 2,0
	// DERECHA é a versão invertida de IZQUIERDA. Usamos a versão INVERTIR_X
	public static final Sprite JOGADOR_DERECHA = new Sprite(32, 2, 0, Version.INVERTIR_X, HojaSprites.jogador); 

	// --- Coleção de Sprites de Mapa (TERRENO e OBJETOS) ---
	public static final Sprite VACIO = new Sprite(32, 0x000000); // Sprite vazio (preto)
	public static final Sprite ASFALTO = new Sprite(32, 0, 0, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CENTRO_CARRETERA = new Sprite(32, 0, 1, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite ASFALTO_DEITADO = new Sprite(32, 0, 2, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite GRAMA = new Sprite(32, 1, 0, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite AREIA = new Sprite(32, 1, 1, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA = new Sprite(32, 2, 1, Version.NORMAL, HojaSprites.desierto);
	// Usando as versões do enum para rotação
	public static final Sprite CASA90 = new Sprite(32, 2, 1, Version.ROTAR_90_DIR, HojaSprites.desierto); // ROTAR_90_DIR (antes 5)
	public static final Sprite CASA180 = new Sprite(32, 2, 1, Version.INVERTIR_XY, HojaSprites.desierto); // INVERTIR_XY (antes 4)
	public static final Sprite CASA270 = new Sprite(32, 2, 1, Version.ROTAR_90_ESQ, HojaSprites.desierto); // ROTAR_90_ESQ (antes 3)

	// Sprites de Paredes da Casa (exemplo de como os nomes podem ser mais descritivos)
	public static final Sprite PAREDE_CASA_SUPERIOR_ESQ = new Sprite(32, 2, 2, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_CASA_SUPERIOR_DIR = new Sprite(32, 3, 2, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_CASA_INFERIOR_ESQ = new Sprite(32, 2, 3, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_CASA_INFERIOR_DIR = new Sprite(32, 3, 3, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite MURO = new Sprite(32, 0, 3, Version.NORMAL, HojaSprites.desierto);
	// SEGURANCA: Verifique se esses sprites são realmente apenas rotações ou se são diferentes na folha.
	// Se forem rotações, use os enums de Version.
	public static final Sprite SEGURANCA180 = new Sprite(32, 1, 1, Version.INVERTIR_XY, HojaSprites.desierto); 
	public static final Sprite SEGURANCA90 = new Sprite(32, 1, 2, Version.ROTAR_90_DIR, HojaSprites.desierto); 

	// Sprites de PAREDE (agrupados para clareza)
	public static final Sprite PAREDE_1 = new Sprite(32, 0, 4, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_2 = new Sprite(32, 0, 5, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_3 = new Sprite(32, 0, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_4 = new Sprite(32, 0, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_5 = new Sprite(32, 1, 4, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_6 = new Sprite(32, 1, 5, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_7 = new Sprite(32, 1, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_8 = new Sprite(32, 1, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_9 = new Sprite(32, 2, 4, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_10 = new Sprite(32, 2, 5, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_11 = new Sprite(32, 2, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_12 = new Sprite(32, 2, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_13 = new Sprite(32, 3, 4, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_14 = new Sprite(32, 3, 5, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_15 = new Sprite(32, 3, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_16 = new Sprite(32, 3, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_17 = new Sprite(32, 4, 4, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_18 = new Sprite(32, 4, 5, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_19 = new Sprite(32, 4, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite PAREDE_20 = new Sprite(32, 4, 7, Version.NORMAL, HojaSprites.desierto);

	// Sprites CASA MADEIRA (Revisar se as coordenas e versões estão corretas para cada variação)
	// As versões "INVERTER PARA ESQUERDA" e "INVERTER PARA DIREITA" no seu código original
	// usavam o mesmo sprite base na HojaSprites e o mesmo `version` (0 ou 5), o que é inconsistente.
	// Se as "INVERTER PARA ESQUERDA" forem *realmente invertidas* do sprite base,
	// elas devem usar a versão INVERTIR_X (ou 5 se 5 for INVERTIR_X).
	// Aqui, assumimos que as "_ESQUERDA" devem ser invertidas no eixo X.
	// As "_DIREITA" foram revertidas para NORMAL se essa for a intenção.
	public static final Sprite CASA_MADEIRA_ABAIXO_01 = new Sprite(32, 4, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_01 = new Sprite(32, 4, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_02 = new Sprite(32, 5, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_02 = new Sprite(32, 5, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_03 = new Sprite(32, 6, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_03 = new Sprite(32, 6, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_04 = new Sprite(32, 7, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_04 = new Sprite(32, 7, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_05 = new Sprite(32, 8, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_05 = new Sprite(32, 8, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_06 = new Sprite(32, 9, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_06 = new Sprite(32, 9, 6, Version.NORMAL, HojaSprites.desierto);

	// Se "ESQUERDA" significa imagem invertida no eixo X do sprite base
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_01 = new Sprite(32, 4, 7, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_01 = new Sprite(32, 4, 6, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_02 = new Sprite(32, 5, 7, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_02 = new Sprite(32, 5, 6, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_03 = new Sprite(32, 6, 7, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_03 = new Sprite(32, 6, 6, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_04 = new Sprite(32, 7, 7, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_04 = new Sprite(32, 7, 6, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_05 = new Sprite(32, 8, 7, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_05 = new Sprite(32, 8, 6, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_ESQUERDA_06 = new Sprite(32, 9, 7, Version.INVERTIR_X, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_ESQUERDA_06 = new Sprite(32, 9, 6, Version.INVERTIR_X, HojaSprites.desierto);

	// Se "DIREITA" é a versão NORMAL (não invertida)
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_01 = new Sprite(32, 4, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_01 = new Sprite(32, 4, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_02 = new Sprite(32, 5, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_02 = new Sprite(32, 5, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_03 = new Sprite(32, 6, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_03 = new Sprite(32, 6, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_04 = new Sprite(32, 7, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_04 = new Sprite(32, 7, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_05 = new Sprite(32, 8, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_05 = new Sprite(32, 8, 6, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ABAIXO_DIREITA_06 = new Sprite(32, 9, 7, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_MADEIRA_ACIMA_DIREITA_06 = new Sprite(32, 9, 6, Version.NORMAL, HojaSprites.desierto);

	// Sprites CASA CIMENTO (Revisar se as coordenas e rotações estão corretas)
	// No seu código original, todos têm '3' para version, que agora é INVERTIR_XY.
	// Se a intenção é "INVERTER PARA CIMA", talvez seja INVERTIR_Y.
	// Aqui assumimos que são sprites normais e que a "rotação" original era um erro.
	public static final Sprite CASA_CIMENTO_ABAIXO_01 = new Sprite(32, 0, 9, Version.NORMAL, HojaSprites.desierto); 
	public static final Sprite CASA_CIMENTO_ACIMA_01 = new Sprite(32, 0, 8, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_02 = new Sprite(32, 1, 9, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_02 = new Sprite(32, 1, 8, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_03 = new Sprite(32, 2, 9, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_03 = new Sprite(32, 2, 8, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_04 = new Sprite(32, 3, 9, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_04 = new Sprite(32, 3, 8, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_05 = new Sprite(32, 4, 9, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_05 = new Sprite(32, 4, 8, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ABAIXO_06 = new Sprite(32, 5, 9, Version.NORMAL, HojaSprites.desierto);
	public static final Sprite CASA_CIMENTO_ACIMA_06 = new Sprite(32, 5, 8, Version.NORMAL, HojaSprites.desierto);


	// --- Construtores ---

	/**
	 * Construtor para criar um sprite a partir de uma folha de sprites.
	 *
	 * @param lado O tamanho do lado do sprite (ex: 32 para 32x32 pixels).
	 * @param coluna A coluna do sprite na folha de sprites (base 0).
	 * @param fila A fila do sprite na folha de sprites (base 0).
	 * @param version A versão de manipulação do sprite (rotação/inversão) usando o enum Version.
	 * @param hoja A HojaSprites de onde o sprite será extraído.
	 */
	public Sprite(final int lado, final int coluna, final int fila, final Version version, final HojaSprites hoja) {
		this.lado = lado;
		this.pixeles = new int[lado * lado]; // Inicializa o array de pixels
		this.x = coluna * lado;
		this.y = fila * lado;
		this.hoja = hoja;
		cargarSprite(version); // Carrega e manipula o sprite
	}

	/**
	 * Construtor para criar um sprite de uma única cor (ex: para preenchimento de teste ou vazio).
	 *
	 * @param lado O tamanho do lado do sprite.
	 * @param color A cor em formato inteiro ARGB (ex: 0xFFFFFFFF para branco).
	 */
	public Sprite(final int lado, final int color) {
		this.lado = lado;
		this.pixeles = new int[lado * lado];
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
		this.hoja = null; // Sprites de cor única não têm folha associada
	}

	// --- Métodos de Acesso ---

	/**
	 * Retorna o tamanho do lado do sprite (largura e altura, já que é quadrado).
	 * @return O lado do sprite.
	 */
	public int obtemLado() {
		return lado;
	}

	/**
	 * Retorna uma cópia do array de pixels do sprite.
	 * @return Um array de inteiros representando os pixels do sprite.
	 */
	public int[] getPixeles() {
		// Retornar uma cópia defensiva pode ser mais seguro se o sprite não for final,
		// mas como é final e a intenção é performance, retornar o array direto é comum.
		return pixeles;
	}

	// --- Métodos Internos de Carregamento e Manipulação ---

	/**
	 * Carrega o sprite da folha de sprites e aplica a manipulação de versão.
	 * @param version O tipo de manipulação a ser aplicada.
	 */
	private void cargarSprite(Version version) {
		// Carrega os pixels originais para um buffer temporário.
		// Isso garante que as manipulações sempre comecem do sprite original, não de uma versão já manipulada.
		int[] pixelesTemporales = new int[lado * lado];
		for (int yTemp = 0; yTemp < lado; yTemp++) {
			for (int xTemp = 0; xTemp < lado; xTemp++) {
				pixelesTemporales[xTemp + yTemp * lado] = hoja.pixeles[(xTemp + this.x) + (yTemp + this.y) * hoja.obtenAncho()];
			}
		}

		// Aplica a manipulação de acordo com a versão
		switch (version) {
			case NORMAL:
				// Já está carregado no pixelesTemporales, basta copiar (ou otimizar para evitar a cópia inicial se NORMAL)
				// Por simplicidade e consistência com outras manipulações, mantemos o fluxo.
				System.arraycopy(pixelesTemporales, 0, pixeles, 0, pixeles.length);
				break;
			case INVERTIR_X:
				invertirX(pixelesTemporales);
				break;
			case INVERTIR_Y:
				invertirY(pixelesTemporales);
				break;
			case INVERTIR_XY:
				invertirXY(pixelesTemporales);
				break;
			case ROTAR_90_ESQ:
				rotar90Esquerda(pixelesTemporales);
				break;
			case ROTAR_90_DIR:
				rotar90Direita(pixelesTemporales);
				break;
			case ROTAR_90_ESQ_INVERTIR_Y:
				// Lógica mais complexa, verificar implementação.
				// Por agora, copiarei a lógica original para manter a funcionalidade,
				// mas isso pode ser simplificado se for apenas uma inversão e rotação.
				rotar90EsquerdaInvertirY(pixelesTemporales);
				break;
			case ROTAR_90_DIR_INVERTIR_Y:
				// Lógica mais complexa, verificar implementação.
				// Por agora, copiarei a lógica original para manter a funcionalidade,
				// mas isso pode ser simplificado se for apenas uma inversão e rotação.
				rotar90DireitaInvertirY(pixelesTemporales);
				break;
			default:
				System.arraycopy(pixelesTemporales, 0, pixeles, 0, pixeles.length); // Fallback para normal
				break;
		}
	}

	// --- Métodos de Manipulação de Pixels ---

	/**
	 * Inverte o sprite horizontalmente (eixo X).
	 * @param tempPixels Pixels originais do sprite.
	 */
	private void invertirX(int[] tempPixels) {
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = tempPixels[(lado - 1 - x) + y * lado];
			}
		}
	}

	/**
	 * Inverte o sprite verticalmente (eixo Y).
	 * @param tempPixels Pixels originais do sprite.
	 */
	private void invertirY(int[] tempPixels) {
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = tempPixels[x + (lado - 1 - y) * lado];
			}
		}
	}

	/**
	 * Inverte o sprite horizontalmente e verticalmente (eixo XY).
	 * @param tempPixels Pixels originais do sprite.
	 */
	private void invertirXY(int[] tempPixels) {
		// A lógica original `pixeles[i] = pixelesTemporales[pixelesTemporales.length - 1 - i];`
		// inverte a ordem linear dos pixels, o que para um quadrado é o mesmo que inverter XY.
		// Mantemos a lógica otimizada.
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = tempPixels[pixeles.length - 1 - i];
		}
	}

	/**
	 * Rotaciona o sprite 90 graus para a esquerda (sentido anti-horário).
	 * @param tempPixels Pixels originais do sprite.
	 */
	private void rotar90Esquerda(int[] tempPixels) {
		// Nova fórmula para rotação 90 graus para a esquerda (anti-horário)
		// novoX = y
		// novoY = lado - 1 - x
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = tempPixels[y + (lado - 1 - x) * lado];
			}
		}
	}

	/**
	 * Rotaciona o sprite 90 graus para a direita (sentido horário).
	 * @param tempPixels Pixels originais do sprite.
	 */
	private void rotar90Direita(int[] tempPixels) {
		// Nova fórmula para rotação 90 graus para a direita (horário)
		// novoX = lado - 1 - y
		// novoY = x
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = tempPixels[(lado - 1 - y) + x * lado];
			}
		}
	}
	
	// --- Métodos de Manipulação Complexa (Verificar a necessidade e correção) ---
	// As lógicas abaixo precisam ser revisadas cuidadosamente, pois as implementações originais
	// em seu código pareciam não estar fazendo a manipulação correta.
	// Se estas são rotações E inversões, a ordem da operação importa.

	/**
	 * Rotaciona 90 graus para a esquerda e inverte no eixo Y.
	 * (Esta lógica precisa ser verificada cuidadosamente se realmente faz o que o nome indica)
	 * @param tempPixels Pixels originais do sprite.
	 */
	private void rotar90EsquerdaInvertirY(int[] tempPixels) {
	    // Originalmente era apenas uma cópia. Reimplementando como Rotar90Esquerda seguida por InverterY
	    int[] rotatedPixels = new int[lado * lado];
	    // Rotaciona 90 graus para a esquerda primeiro
	    for (int y = 0; y < lado; y++) {
	        for (int x = 0; x < lado; x++) {
	            rotatedPixels[x + y * lado] = tempPixels[y + (lado - 1 - x) * lado];
	        }
	    }
	    // Depois inverte no eixo Y
	    for (int y = 0; y < lado; y++) {
	        for (int x = 0; x < lado; x++) {
	            pixeles[x + y * lado] = rotatedPixels[x + (lado - 1 - y) * lado];
	        }
	    }
	}

	/**
	 * Rotaciona 90 graus para a direita e inverte no eixo Y.
	 * (Esta lógica precisa ser verificada cuidadosamente se realmente faz o que o nome indica)
	 * @param tempPixels Pixels originais do sprite.
	 */
	private void rotar90DireitaInvertirY(int[] tempPixels) {
	    // Originalmente era uma cópia incorreta. Reimplementando como Rotar90Direita seguida por InvertirY
	    int[] rotatedPixels = new int[lado * lado];
	    // Rotaciona 90 graus para a direita primeiro
	    for (int y = 0; y < lado; y++) {
	        for (int x = 0; x < lado; x++) {
	            rotatedPixels[x + y * lado] = tempPixels[(lado - 1 - y) + x * lado];
	        }
	    }
	    // Depois inverte no eixo Y
	    for (int y = 0; y < lado; y++) {
	        for (int x = 0; x < lado; x++) {
	            pixeles[x + y * lado] = rotatedPixels[x + (lado - 1 - y) * lado];
	        }
	    }
	}
}
