package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa {

	private int[] pixeles;

	public MapaCargado(String ruta) {
		super(ruta);
	}

	protected void cargaMapa(String ruta) {
		try {
			BufferedImage image = ImageIO.read(MapaCargado.class.getResource(ruta));
			ancho = image.getWidth();
			alto = image.getHeight();

			cuadrosCatalogo = new Cuadro[ancho * alto];
			pixeles = new int[ancho * alto];

			image.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void generarMapa() {
		for (int i = 0; i < pixeles.length; i++) {
			switch (pixeles[i]) {
			case 0xff2A2B2D: // Conversão hex para int -14013651
				cuadrosCatalogo[i] = Cuadro.ASFALTO;
				continue;
			case 0xff66BC50: // Conversão hex para int -10044336
				cuadrosCatalogo[i] = Cuadro.GRAMA;
				continue;
			case 0xff8F918F: // Conversão hex para int -7368305
				cuadrosCatalogo[i] = Cuadro.CENTRO_CARRETERA;
				continue;
			case 0xffC3C7C3: // Conversão hex para int -3946557
				cuadrosCatalogo[i] = Cuadro.ASFALTO_DEITADO;
				continue;
			case 0xffFF19CD: // Conversão hex para int -2621272
				cuadrosCatalogo[i] = Cuadro.CASA;
				continue;
			case 0xffD800A8: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.CASA90;
				continue;
			case 0xffEF00F5: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.CASA180;
				continue;
			case 0xffFF63FF: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.CASA270;
				continue;
			case 0xff273627: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_CASA_ALTO_ESQUERDA;
				continue;
			case 0xff5400DC: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_CASA_ALTO_DIREITA;
				continue;
			case 0xffA26DFF: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_CASA_BAIXO_ESQUERDA;
				continue;
			case 0xffFFE125: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_CASA_BAIXO_DIREITA;
				continue;

			case 0xff006ED1: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_1;
				continue;
			case 0xffF37600: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_2;
				continue;
			case 0xff0018CD: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_3;
				continue;
			case 0xffA25500: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_4;
				continue;
			case 0xffAC2C00: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_5;
				continue;
			case 0xff2000D4: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_6;
				continue;
			case 0xffDE5B00: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_7;
				continue;
			case 0xff8B00A8: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_8;
				continue;
			case 0xff00CFBC: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_9;
				continue;
			case 0xffD62F00: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_10;
				continue;
			case 0xff6400D1: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_11;
				continue;
			case 0xff0094C8: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_12;
				continue;
			case 0xffF94A00: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_13;
				continue;
			case 0xff002AC9: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_14;
				continue;
			case 0xffC70011: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_15;
				continue;
			case 0xff7A00C3: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.PAREDE_16;
				continue;
			case 0xff001369: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.MURO;
				continue;
			case -8978314: // Conversão int para hex 770076
				cuadrosCatalogo[i] = Cuadro.SEGURANCA180;
				continue;
			case 0xff2A8400: // Conversão hex para int
				cuadrosCatalogo[i] = Cuadro.SEGURANCA90;
				continue;

			// CASA_MADEIRA
			case 0xffC7FA00:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_01;
				continue;
			case 0xffEEF800:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_02;
				continue;
			case 0xffC0E700:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_03;
				continue;
			case 0xffE1D900:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_04;
				continue;
			case 0xffE4AB00:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_05;
				continue;
			case 0xffCCCC00:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_06;
				continue;
			case 0xffDDFA00:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_01;
				continue;
			case 0xffF8E400:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_02;
				continue;
			case 0xffD6E400:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_03;
				continue;
			case 0xffE5C300:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_04;
				continue;
			case 0xffD6B800:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_05;
				continue;
			case 0xffADD400:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_06;
				continue;

			// CASA_MADEIRA_ESQUERDA
			case 0xffB99200 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_01;
				continue;
			case 0xffB28400:
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_02;
				continue;
			case 0xffB56900 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_01;
				continue;
			case 0xff7D7400 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_04;
				continue;
			case 0xff8F7600 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_05;
				continue;
			case 0xff4B4A00 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ABAIXO_ESQUERDA_06;
				continue;
			case 0xffB19500 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_01;
				continue;
			case 0xffB27600 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_02;
				continue;
			case 0xff9A7600 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_03;
				continue;
			case 0xff859200 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_04;
				continue;
			case 0xff728400 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_05;
				continue;
			case 0xff523F00 :
				cuadrosCatalogo[i] = Cuadro.CASA_MADEIRA_ACIMA_ESQUERDA_06;
				continue;

			// CASA_CIMENTO
			case 0xff00E3F0:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_01;
				continue;
			case 0xff00EBE3:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_02;
				continue;
			case 0xff00F0D5:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_03;
				continue;
			case 0xff00F7C9:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_04;
				continue;
			case 0xff00F4BD:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_05;
				continue;
			case 0xff00F3B1:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ABAIXO_06;
				continue;
			case 0xff00D7DA:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_01;
				continue;
			case 0xff00DFCE:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_02;
				continue;
			case 0xff00E4C0:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_03;
				continue;
			case 0xff00E1B5:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_04;
				continue;
			case 0xff00DEA9:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_05;
				continue;
			case 0xff00DE9F:
				cuadrosCatalogo[i] = Cuadro.CASA_CIMENTO_ACIMA_06;
				continue;
			default:
				System.out.println("" + pixeles[i]);
				cuadrosCatalogo[i] = Cuadro.VACIO;
				continue;
			}
		}
	}
}
