package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

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
		// A inicialização de cuadrosCatalogo deve ser feita na subclasse MapaCargado após definir ancho/alto.
	}

	// Método abstrato para gerar o mapa (implementado em MapaGenerado)
	protected abstract void generarMapa();

	// Método abstrato para carregar o mapa de um arquivo (implementado em MapaCargado)
	protected abstract void carregarMapa(String ruta);

	public void actualizar() {
		// As subclasses podem adicionar lógica de atualização se necessário
	}

	public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla) {
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
					Cuadro.VACIO.mostrar(x, y, pantalla); // Desenha um quadro vazio se fora dos limites
				} else {
					// Mostra o quadro correspondente na tela
					obtenerCuadro(x, y).mostrar(x, y, pantalla);
				}
			}
		}
	}

	// Retorna o quadro em uma determinada coordenada (x, y) do mapa
	public Cuadro obtenerCuadro(final int x, final int y) {
		// Retorna um quadro vazio se as coordenadas estiverem fora dos
		// limites, evitando ArrayIndexOutOfBoundsException
		if (x < 0 || y < 0 || x >= ancho || y >= alto) {
			return Cuadro.VACIO;
		}

		// Retorna o quadro do catálogo
		return cuadrosCatalogo[x + y * ancho];
	}

	// Métodos auxiliares para obter largura e altura do mapa
	public int obtenerAncho() {
		return ancho;
	}

	public int obtenerAlto() {
		return alto;
	}
}
