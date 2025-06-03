package entes.criaturas;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Jogador extends Entes {
	private Teclado teclado;
	private int animacion;

	// Construtor principal para Jogador
	public Jogador(Mapa mapa, Teclado teclado, Sprite sprite) {
		super(mapa); // Chama o construtor da superclasse Criatura (que chama Ente)
		this.teclado = teclado;
		this.sprite = sprite;
	}

	// Construtor auxiliar para facilitar a criação do jogador com posição e sprite padrão
	public Jogador(Mapa mapa, Teclado teclado, int posicionX, int posicionY) {
		this(mapa, teclado, Sprite.JOGADOR_BAIXO);
		this.x = posicionX;
		this.y = posicionY;
	}

	@Override
	public void actualizar() {
		// Declaração correta das variáveis locais de deslocamento
		int desplazamientoX = 0;
		int desplazamientoY = 0;

		int velocidadeMovimiento = 1;

		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}

		// Acesso corrigido aos estados do teclado usando os MÉTODOS GETTERS
		if (teclado.isCorrer()) { // Corrigido de teclado.correr para teclado.isCorrer()
			velocidadeMovimiento = 2;
		}

		if (teclado.isArriba()) { // Corrigido de teclado.arriba para teclado.isArriba()
			desplazamientoY -= velocidadeMovimiento;
		}
		if (teclado.isAbajo()) { // Corrigido de teclado.abajo para teclado.isAbajo()
			desplazamientoY += velocidadeMovimiento;
		}
		if (teclado.isIzquierda()) { // Corrigido de teclado.izquierda para teclado.isIzquierda()
			desplazamientoX -= velocidadeMovimiento;
		}
		if (teclado.isDerecha()) { // Corrigido de teclado.derecha para teclado.isDerecha()
			desplazamientoX += velocidadeMovimiento;
		}

		if (desplazamientoX != 0 || desplazamientoY != 0) {
			mover(desplazamientoX, desplazamientoY);
			enMovimiento = true;
		} else {
			enMovimiento = false;
		}

		// Lógica para seleção do sprite de animação
		// A animação é baseada no resto da divisão para ciclar entre os sprites
		int resto = animacion % 40; // Ajustado o divisor para 40, mais comum para ciclos de 4 sprites

		if (direccion == 'n') { // Norte (Cima)
			if (enMovimiento) {
				if ((resto > 10) && (resto <= 20)) {
					sprite = Sprite.JOGADOR_CIMA_1;
				} else if ((resto > 20) && (resto <= 30)) {
					sprite = Sprite.JOGADOR_CIMA_2;
				} else if (resto > 30) {
					sprite = Sprite.JOGADOR_CIMA_1;
				} else {
					sprite = Sprite.JOGADOR_CIMA;
				}
			} else {
				sprite = Sprite.JOGADOR_CIMA;
			}
		} else if (direccion == 's') { // Baixo
			if (enMovimiento) {
				if ((resto > 10) && (resto <= 20)) {
					sprite = Sprite.JOGADOR_BAIXO_1;
				} else if ((resto > 20) && (resto <= 30)) {
					sprite = Sprite.JOGADOR_BAIXO_2;
				} else if (resto > 30) {
					sprite = Sprite.JOGADOR_BAIXO_1;
				} else {
					sprite = Sprite.JOGADOR_BAIXO;
				}
			} else {
				sprite = Sprite.JOGADOR_BAIXO;
			}
		} else if (direccion == 'o') { // Oeste (Esquerda)
			if (enMovimiento) {
				if ((resto > 10) && (resto <= 20)) {
					sprite = Sprite.JOGADOR_ESQUERDA_1;
				} else if ((resto > 20) && (resto <= 30)) {
					sprite = Sprite.JOGADOR_ESQUERDA_2;
				} else if (resto > 30) {
					sprite = Sprite.JOGADOR_ESQUERDA_1;
				} else {
					sprite = Sprite.JOGADOR_ESQUERDA;
				}
			} else {
				sprite = Sprite.JOGADOR_ESQUERDA;
			}
		} else if (direccion == 'e') { // Leste (Direita)
			if (enMovimiento) {
				if ((resto > 10) && (resto <= 20)) {
					sprite = Sprite.JOGADOR_DIREITA_1;
				} else if ((resto > 20) && (resto <= 30)) {
					sprite = Sprite.JOGADOR_DIREITA_2;
				} else if (resto > 30) {
					sprite = Sprite.JOGADOR_DIREITA_1;
				} else {
					sprite = Sprite.JOGADOR_DIREITA;
				}
			} else {
				sprite = Sprite.JOGADOR_DIREITA;
			}
		}
	}

	@Override
	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarJogador(x, y, this);
	}
	
	// Método para obter o sprite atual do jogador
	public Sprite obtenerSprite() {
		return sprite;
	}
}
