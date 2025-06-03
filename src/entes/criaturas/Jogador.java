package entes.criaturas;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Jogador extends Criatura {
	private Teclado teclado;
	private int animacion;

	public Jogador(Mapa mapa, Teclado teclado, Sprite sprite) {
		super(mapa); // Corrigido: Chama o construtor da superclasse Criatura
		this.teclado = teclado;
		this.sprite = sprite;
	}

	public Jogador(Mapa mapa, Teclado teclado, int posicionX, int posicionY) {
		this(mapa, teclado, Sprite.JOGADOR_BAIXO);
		this.x = posicionX;
		this.y = posicionY;
	}

	public void actualizar() {
		int desplazamientoX = 0; // Corrigido: Declarado como variável local
		int desplazamientoY = 0; // Corrigido: Declarado como variável local
		int velocidadeMovimiento = 1;

		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}

		if (teclado.isCorrer()) { // Corrigido: Usa getter
			velocidadeMovimiento = 2;
		}

		if (teclado.isArriba()) { // Corrigido: Usa getter
			desplazamentoY -= velocidadeMovimiento;
		}
		if (teclado.isAbajo()) { // Corrigido: Usa getter
			desplazamentoY += velocidadeMovimiento;
		}
		if (teclado.isIzquierda()) { // Corrigido: Usa getter
			desplazamentoX -= velocidadeMovimiento;
		}
		if (teclado.isDerecha()) { // Corrigido: Usa getter
			desplazamentoX += velocidadeMovimiento;
		}

		if (desplazamentoX != 0 || desplazamientoY != 0) { // Corrigido: Usa variáveis locais
			mover(desplazamentoX, desplazamientoY); // Corrigido: Usa variáveis locais
			enMovimiento = true;
		} else {
			enMovimiento = false;
		}

		int resto = animacion % 40;

		if (direccion == 'n') { // Cima
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
		}
		if (direccion == 's') { // Baixo
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
		}
		if (direccion == 'o') { // Esquerda
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
		}
		if (direccion == 'e') { // Direita
			if (enMovimento) {
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

	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarJogador(x, y, this);
	}
}
