package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
	private final int ancho;
	private final int alto;
	public final int[] pixeles;
	
	//coleccion de hojas de sprites 
	public static HojaSprites desierto = new HojaSprites("/texturas/HojaSprites.png", 416, 416); // Deve ser indicada a imagem e tamanho da imagem
	public static HojaSprites jogador = new HojaSprites("/imagenes/hojasPersonajes/2.png", 128, 96); // Deve ser indicada a imagem e tamanho da imagem
	
	//fim de la coleccion
	
	public HojaSprites(final String ruta, final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto  = alto;
		
		pixeles = new int[ancho * alto];
		
		try {
			BufferedImage image = ImageIO.read(HojaSprites.class.getResource(ruta));
			
			image.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int obtenAncho() {
		return ancho;
	}
}
