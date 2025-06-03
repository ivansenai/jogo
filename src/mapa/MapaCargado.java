package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa {

	private int[] pixeles;

	public MapaCargado(String ruta) {
		super(ruta); // Chama o construtor de Mapa(String ruta)
		// A inicialização de ancho, alto e cuadrosCatalogo ocorre em carregarMapa()
		carregarMapa(ruta);
		// Não chame gerarMapa() aqui se ele preenche com base em pixels da imagem.
		// A lógica que estava em generarMapa() de MapaCargado agora está em carregarMapa.
	}

	@Override
	protected void carregarMapa(String ruta) {
		try {
			BufferedImage imagem = ImageIO.read(MapaCargado.class.getResourceAsStream(ruta));
			this.ancho = imagem.getWidth();
			this.alto = imagem.getHeight();

			this.pixeles = new int[ancho * alto];
			imagem.getRGB(0, 0, ancho, alto, this.pixeles, 0, ancho);

			this.cuadrosCatalogo = new Cuadro[ancho * alto]; // Inicializa o array AQUI

			// Lógica para preencher cuadrosCatalogo com base nos pixels da imagem
			for (int i = 0; i < pixeles.length; i++) {
				switch (pixeles[i]) {
					// As cores abaixo devem CORRESPONDER EXATAMENTE aos pixels do seu mapa de imagem
					case 0xff2A2B2D: // Exemplo de cor para ASFALTO
						cuadrosCatalogo[i] = Cuadro.ASFALTO;
						break;
					case 0xff66BC50: // Exemplo de cor para AREIA
						cuadrosCatalogo[i] = Cuadro.AREIA;
						break;
					case 0xff3F7C00: // Exemplo: cor para GRAMA
						cuadrosCatalogo[i] = Cuadro.GRAMA;
						break;
					case 0xffA3A3A3: // Exemplo: cor para CENTRO_CARRETERA
						cuadrosCatalogo[i] = Cuadro.CENTRO_CARRETERA;
						break;
					case 0xff000000: // Preto para VAZIO ou algum outro padrão
						cuadrosCatalogo[i] = Cuadro.VACIO;
						break;
					case 0xff808080: // Exemplo: Asfalto deitado
						cuadrosCatalogo[i] = Cuadro.ASFALTO_DEITADO;
						break;
					
					// Adicione todos os outros cases para as cores dos seus tiles de CASA/PAREDES/ETC.
					// Certifique-se de que cada cor no seu mapa de imagem corresponda a um Cuadro estático.
					case 0xff7F3300: cuadrosCatalogo[i] = Cuadro.CASA; break;
					case 0xff994000: cuadrosCatalogo[i] = Cuadro.CASA90; break;
					case 0xffB24C00: cuadrosCatalogo[i] = Cuadro.CASA180; break;
					case 0xffCC5900: cuadrosCatalogo[i] = Cuadro.CASA270; break;

					case 0xff522100: cuadrosCatalogo[i] = Cuadro.PAREDE_PEDRA; break;
					case 0xff6D2D01: cuadrosCatalogo[i] = Cuadro.PAREDE_PEDRA_INVERTIDA; break;
					
					case 0xff0080FF: cuadrosCatalogo[i] = Cuadro.MURO_AGUA_CIMA; break;
					case 0xff0066CC: cuadrosCatalogo[i] = Cuadro.MURO_AGUA_BAIXO; break;
					case 0xff00BFFF: cuadrosCatalogo[i] = Cuadro.MURO_AGUA_ESQUERDA; break;
					case 0xff0099FF: cuadrosCatalogo[i] = Cuadro.MURO_AGUA_DIREITA; break;
					case 0xff004C99: cuadrosCatalogo[i] = Cuadro.MURO_AGUA_CANTO_SUP_ESQ; break;
					case 0xff003366: cuadrosCatalogo[i] = Cuadro.MURO_AGUA_CANTO_SUP_DIR; break;
					case 0xff001A33: cuadrosCatalogo[i] = Cuadro.MURO_AGUA_CANTO_INF_ESQ; break;
					case 0xff000D1A: cuadrosCatalogo[i] = Cuadro.MURO_AGUA_CANTO_INF_DIR; break;
					case 0xff2E8B57: cuadrosCatalogo[i] = Cuadro.MURO_AGUA_AREIA; break;

					case 0xff2B2E4A: cuadrosCatalogo[i] = Cuadro.SEGURANCA_BAIXO; break;
					case 0xff4C5182: cuadrosCatalogo[i] = Cuadro.SEGURANCA_CIMA; break;
					case 0xff6F74B0: cuadrosCatalogo[i] = Cuadro.SEGURANCA_ESQUERDA; break;
					case 0xff9197DE: cuadrosCatalogo[i] = Cuadro.SEGURANCA_DIREITA; break;

					case 0xff800000: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_BAIXO_01; break;
					case 0xff9A0000: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_BAIXO_02; break;
					case 0xffB40000: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_BAIXO_03; break;
					case 0xffCE0000: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_BAIXO_04; break;
					case 0xffE80000: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_BAIXO_05; break;
					case 0xffFF0000: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_BAIXO_06; break;
					case 0xff008000: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_CIMA_01; break;
					case 0xff009A00: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_CIMA_02; break;
					case 0xff00B400: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_CIMA_03; break;
					case 0xff00CE00: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_CIMA_04; break;
					case 0xff00E800: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_CIMA_05; break;
					case 0xff00FF00: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_CIMA_06; break;
					case 0xff808000: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_DIREITA_01; break;
					case 0xff9A9A00: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_DIREITA_02; break;
					case 0xffB4B400: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_DIREITA_03; break;
					case 0xffCECE00: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_DIREITA_04; break;
					case 0xffE8E800: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_DIREITA_05; break;
					case 0xffFFFF00: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_DIREITA_06; break;
					case 0xff800080: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ESQUERDA_01; break;
					case 0xff9A009A: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ESQUERDA_02; break;
					case 0xffB400B4: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ESQUERDA_03; break;
					case 0xffCE00CE: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ESQUERDA_04; break;
					case 0xffE800E8: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ESQUERDA_05; break;
					case 0xffFF00FF: cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ESQUERDA_06; break;

					case 0xff00E3F0: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_01; break;
					case 0xff00EBE3: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_02; break;
					case 0xff00F0D5: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_03; break;
					case 0xff00F7C9: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_04; break;
					case 0xff00F4BD: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_05; break;
					case 0xff00F3B1: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_06; break;

					case 0xff00D4C5: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_CIMA_01; break;
					case 0xff00C5BA: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_CIMA_02; break;
					case 0xff00B7B0: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_CIMA_03; break;
					case 0xff00AAA3: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_CIMA_04; break;
					case 0xff009C96: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_CIMA_05; break;
					case 0xff008F8B: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_CIMA_06; break;

					case 0xff6666FF: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_DIREITA_01; break;
					case 0xff7777FF: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_DIREITA_02; break;
					case 0xff8888FF: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_DIREITA_03; break;
					case 0xff9999FF: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_DIREITA_04; break;
					case 0xffAAAAFF: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_DIREITA_05; break;
					case 0xffBBBBFF: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_DIREITA_06; break;

					case 0xffCC0000: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ESQUERDA_01; break; // Duplicado com CASA_MADEIRA_DIREITA_04
					case 0xffDD0000: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ESQUERDA_02; break; // Duplicado
					case 0xffEE0000: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ESQUERDA_03; break; // Duplicado
					case 0xffFF0000: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ESQUERDA_04; break; // Duplicado
					case 0xff001122: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ESQUERDA_05; break;
					case 0xff334455: cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ESQUERDA_06; break;

					default: // Se uma cor não for mapeada, defina um default
						cuadrosCatalogo[i] = Cuadro.VACIO; // Ou um Cuadro de erro visual
						System.out.println("Cor não mapeada no mapa: " + String.format("#%06X", (pixeles[i] & 0xFFFFFF)));
						break;
				}
			}

		} catch (IOException e) {
			System.err.println("ERRO: Não foi possível carregar o mapa de imagem em: " + ruta);
			e.printStackTrace();
		}
	}

	@Override
	protected void generarMapa() {
		// Este método fica vazio aqui para MapaCargado, pois a geração é feita pela imagem
		// A lógica de preenchimento do catálogo de quadros com base nos pixels
		// já foi movida para carregarMapa().
	}
}
