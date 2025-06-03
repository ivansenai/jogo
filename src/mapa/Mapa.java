package mapa;

import graficos.Pantalla; // Certifique-se de que o pacote graficos existe
import mapa.cuadro.Cuadro; // Importação essencial para Cuadro.LADO e Cuadro.VACIO
import mapa.cuadro.CuadroVacio; // Importar CuadroVacio

public abstract class Mapa {
    protected int ancho;
    protected int alto;
    // O catálogo de quadros do mapa
    protected Cuadro[] cuadrosCatalogo;

    // Construtor para mapas gerados (onde as dimensões são conhecidas primeiro)
    public Mapa(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.cuadrosCatalogo = new Cuadro[ancho * alto]; // Inicializa o catálogo aqui
        generarMapa(); // Chama a implementação da subclasse (ex: MapaGenerado)
    }

    // Construtor para mapas carregados (onde a rota do arquivo é conhecida primeiro)
    public Mapa(String ruta) {
        // Este construtor existe para ser chamado por MapaCargado.
        // MapaCargado será responsável por chamar carregarMapa(ruta) e definir ancho/alto,
        // e então, se necessário, chamar generarMapa().
        // A inicialização de cuadrosCatalogo e a chamada de generarMapa()
        // devem ser feitas na subclasse MapaCargado.
        // Não inicializar cuadrosCatalogo nem chamar generarMapa aqui.
    }

    // Método abstrato para gerar o mapa (implementado em MapaGenerado)
    protected abstract void generarMapa();
    // Método abstrato para carregar o mapa de um arquivo (implementado em MapaCargado)
    // O nome foi padronizado para "carregarMapa" para evitar erros de sobrescrita.
    protected abstract void carregarMapa(String ruta);

    public void actualizar() {
        // Implementação pode ser adicionada em subclasses se necessário,
        // ou pode ser vazia se o mapa não tiver elementos dinâmicos para atualizar.
    }

    public void mostrar( int compensacionX, int compensacionY,  Pantalla pantalla) {
        pantalla.estableceDiferencia(compensacionX, compensacionY);
        // Calcula os offsets em termos de tiles (assumindo Cuadro.LADO = 32)
        int offsetX = compensacionX >> 5; // Equivalente a compensacionX / 32
        int offsetY = compensacionY >> 5; // Equivalente a compensacionY / 32

        // Calcula o final da área visível em termos de tiles, adicionando LADO
        // para garantir que todos os tiles parciais sejam desenhados
        int tileEndX = (compensacionX + pantalla.obtemAncho() + Cuadro.LADO) >> 5;
        int tileEndY = (compensacionY + pantalla.obtemAlto() + Cuadro.LADO) >> 5;
        for (int y = offsetY; y < tileEndY; y++) {
            for (int x = offsetX; x < tileEndX; x++) {
                // Verifica se as coordenadas do tile estão fora dos limites do mapa
                if ((x < 0) || (y < 0) || (x >= ancho) || (y >= alto)) {
                    // Usar o construtor correto para CuadroVacio, passando 'true' para solidez
                    new CuadroVacio(graficos.Sprite.VACIO, true).mostrar(x, y, pantalla); // Corrigido
                } else {
                    // Mostra o quadro correspondente na tela
                    cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
                }
            }
        }
    }

    // Retorna o quadro em uma determinada coordenada (x, y) do mapa
    public Cuadro obtenerCuadro(final int x, final int y) {
        // Retorna um quadro vazio se as coordenadas estiverem fora dos limites do mapa
        if (x < 0 || y < 0 || x >= ancho || y >= alto) {
            // Usar o construtor correto para CuadroVacio, passando 'true' para solidez
            return new CuadroVacio(graficos.Sprite.VACIO, true); // Corrigido
        }
        // Retorna o quadro do catálogo (array 1D mapeado de 2D)
        return cuadrosCatalogo[x + y * ancho];
    }

    public int obtenerAncho() {
        return ancho;
    }

    public int obtenerAlto() {
        return alto;
    }
}
