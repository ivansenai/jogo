package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HojaSprites {

    private final int ancho;
    private final int alto;
    public final int[] pixeles;

    public static HojaSprites ladrilho = new HojaSprites("/texturas/ladrilho.png");
    public static HojaSprites jogador = new HojaSprites("/texturas/jogador.png");
    public static HojaSprites casa = new HojaSprites("/texturas/casa.png");
    public static HojaSprites paredes = new HojaSprites("/texturas/paredes.png");
    public static HojaSprites muro = new HojaSprites("/texturas/muro.png");
    public static HojaSprites seguranca = new HojaSprites("/texturas/seguranca.png");
    public static HojaSprites casaMadeira = new HojaSprites("/texturas/casa_madeira.png");
    public static HojaSprites casaCimento = new HojaSprites("/texturas/casa_cimento.png");

    public HojaSprites(final String ruta) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(HojaSprites.class.getResourceAsStream(ruta));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.ancho = imagen.getWidth();
        this.alto = imagen.getHeight();

        this.pixeles = new int[ancho * alto];
        imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int[] getPixeles() {
        return pixeles;
    }
}
