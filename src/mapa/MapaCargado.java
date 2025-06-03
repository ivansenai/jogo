package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa {

    private int[] pixeles; // Remove 'protected' se não for acessado diretamente

    public MapaCargado(String ruta) {
        // Este construtor NÃO deve chamar super(0,0) que tenta gerar mapa imediatamente
        // A inicialização real do mapa e suas dimensões ocorrerão em carregarMapa
        super(ruta); // Chama o construtor de Mapa(String ruta)
        carregarMapa(ruta); // Agora sim, carrega os dados e define ancho/alto
        // Não chame gerarMapa() aqui se ele preenche com base em pixels da imagem.
        // A lógica que estava em generarMapa() de MapaCargado deve ser parte de carregarMapa.
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
                    case 0xff2A2B2D:
                        cuadrosCatalogo[i] = Cuadro.ASFALTO;
                        break;
                    case 0xff66BC50:
                        cuadrosCatalogo[i] = Cuadro.AREIA;
                        break;
                    case 0xff3F7C00: // Exemplo: cor para GRAMA
                        cuadrosCatalogo[i] = Cuadro.GRAMA;
                        break;
                    case 0xffA3A3A3: // Exemplo: cor para CENTRO_CARRETERA
                        cuadrosCatalogo[i] = Cuadro.CENTRO_CARRETERA;
                        break;
                    // Adicione todos os outros cases para as cores de seus tiles
                    // Certifique-se de que cada cor no seu mapa de imagem corresponda a um Cuadro estático
                    case 0xFF000000: // Preto para Vazio ou algum outro padrão
                        cuadrosCatalogo[i] = Cuadro.VACIO;
                        break;
                    default: // Se uma cor não for mapeada, defina um default
                        cuadrosCatalogo[i] = Cuadro.VACIO; // Ou um Cuadro de erro
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
