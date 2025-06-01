package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa {

	private int[] pixeles;

	public MapaCargado(String ruta) {
		super(0, 0); // Chama o construtor base, dimensões serão definidas em carregarMapa
		carregarMapa(ruta);
		generarMapa();
	}

	@Override
	protected void carregarMapa(String ruta) {
		try {
			BufferedImage imagem = ImageIO.read(MapaCargado.class.getResourceAsStream(ruta));
			this.ancho = imagem.getWidth();
			this.alto = imagem.getHeight();

			this.pixeles = new int[ancho * alto];
			imagem.getRGB(0, 0, ancho, alto, this.pixeles, 0, ancho);

			this.cuadrosCatalogo = new Cuadro[ancho * alto];

		} catch (IOException e) {
			System.err.println("ERRO: Não foi possível carregar o mapa de imagem em: " + ruta);
			e.printStackTrace();
		}
	}

	@Override
	protected void generarMapa() {
		for (int i = 0; i < pixeles.length; i++) {
			switch (pixeles[i]) {
				case 0xff2A2B2D:
					cuadrosCatalogo[i] = Cuadro.ASFALTO;
					break;
				case 0xff66BC50:
					cuadrosCatalogo[i] = Cuadro.GRAMA;
					break;
				case 0xff8F918F:
					cuadrosCatalogo[i] = Cuadro.CENTRO_CARRETERA;
					break;
				case 0xffC3C7C3:
					cuadrosCatalogo[i] = Cuadro.ASFALTO_DEITADO;
					break;
				case 0xffFF19CD:
					cuadrosCatalogo[i] = Cuadro.CASA;
					break;
				case 0xffD800A8:
					cuadrosCatalogo[i] = Cuadro.CASA90;
					break;
				case 0xffEF00F5:
					cuadrosCatalogo[i] = Cuadro.CASA180;
					break;
				case 0xffFF63FF:
					cuadrosCatalogo[i] = Cuadro.CASA270;
					break;
				case 0xff273627:
					cuadrosCatalogo[i] = Cuadro.PAREDE_CASA_ALTO_ESQUERDA;
					break;
				case 0xff5400DC:
					cuadrosCatalogo[i] = Cuadro.PAREDE_CASA_ALTO_DIREITA;
					break;
				case 0xffA26DFF:
					cuadrosCatalogo[i] = Cuadro.PAREDE_CASA_BAIXO_ESQUERDA;
					break;
				case 0xffFFE125:
					cuadrosCatalogo[i] = Cuadro.PAREDE_CASA_BAIXO_DIREITA;
					break;
				case 0xff006ED1:
					cuadrosCatalogo[i] = Cuadro.PAREDE_1;
					break;
				case 0xffF37600:
					cuadrosCatalogo[i] = Cuadro.PAREDE_2;
					break;
				case 0xff0018CD:
					cuadrosCatalogo[i] = Cuadro.PAREDE_3;
					break;
				case 0xffA25500:
					cuadrosCatalogo[i] = Cuadro.PAREDE_4;
					break;
				case 0xffAC2C00:
					cuadrosCatalogo[i] = Cuadro.PAREDE_5;
					break;
				case 0xff2000D4:
					cuadrosCatalogo[i] = Cuadro.PAREDE_6;
					break;
				case 0xffDE5B00:
					cuadrosCatalogo[i] = Cuadro.PAREDE_7;
					break;
				case 0xff8B00A8:
					cuadrosCatalogo[i] = Cuadro.PAREDE_8;
					break;
				case 0xff00CFBC:
					cuadrosCatalogo[i] = Cuadro.PAREDE_9;
					break;
				case 0xffD62F00:
					cuadrosCatalogo[i] = Cuadro.PAREDE_10;
					break;
				case 0xff6400D1:
					cuadrosCatalogo[i] = Cuadro.PAREDE_11;
					break;
				case 0xff0094C8:
					cuadrosCatalogo[i] = Cuadro.PAREDE_12;
					break;
				case 0xffF94A00:
					cuadrosCatalogo[i] = Cuadro.PAREDE_13;
					break;
				case 0xff002AC9:
					cuadrosCatalogo[i] = Cuadro.PAREDE_14;
					break;
				case 0xffC70011:
					cuadrosCatalogo[i] = Cuadro.PAREDE_15;
					break;
				case 0xff7A00C3:
					cuadrosCatalogo[i] = Cuadro.PAREDE_16;
					break;
				case 0xff001369:
					cuadrosCatalogo[i] = Cuadro.MURO;
					break;
				case 0xff770076:
					cuadrosCatalogo[i] = Cuadro.SEGURANCA180;
					break;
				case 0xff2A8400:
					cuadrosCatalogo[i] = Cuadro.SEGURANCA90;
					break;
				case 0xffC7FA00:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_01;
					break;
				case 0xffEEF800:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_02;
					break;
				case 0xffC0E700:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_03;
					break;
				case 0xffE1D900:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_04;
					break;
				case 0xffE4AB00:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_05;
					break;
				case 0xffCCCC00:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_06;
					break;
				case 0xffDDFA00:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_01;
					break;
				case 0xffF8E400:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_02;
					break;
				case 0xffD6E400:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_03;
					break;
				case 0xffE5C300:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_04;
					break;
				case 0xffD6B800:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_05;
					break;
				case 0xffADD400:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_06;
					break;
				case 0xffB99200:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_01;
					break;
				case 0xffB28400:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_02;
					break;
				case 0xffB56900:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_03;
					break;
				case 0xff7D7400:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_04;
					break;
				case 0xff8F7600:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_05;
					break;
				case 0xff4B4A00:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_06;
					break;
				case 0xffB19500:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_01;
					break;
				case 0xffB27600:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_02;
					break;
				case 0xff9A7600:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_03;
					break;
				case 0xff859200:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_04;
					break;
				case 0xff728400:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_05;
					break;
				case 0xff523F00:
					cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_06;
					break;
				case 0xff00E3F0:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_01;
					break;
				case 0xff00EBE3:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_02;
					break;
				case 0xff00F0D5:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_03;
					break;
				case 0xff00F7C9:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_04;
					break;
				case 0xff00F4BD:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_05;
					break;
				case 0xff00F3B1:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_06;
					break;
				case 0xff00D7DA:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_01;
					break;
				case 0xff00DFCE:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_02;
					break;
				case 0xff00E4C0:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_03;
					break;
				case 0xff00E1B5:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_04;
					break;
				case 0xff00DEA9:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_05;
					break;
				case 0xff00DE9F:
					cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_06;
					break;
				default:
					System.out.println("AVISO: Cor de pixel não mapeada no mapa. Default para VACIO: " + Integer.toHexString(pixeles[i]));
					cuadrosCatalogo[i] = Cuadro.VACIO;
					break;
			}
		}
	}
}
