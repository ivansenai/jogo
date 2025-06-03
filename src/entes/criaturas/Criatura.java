package entes.criaturas;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Jogador extends Criatura {
	private Teclado teclado;
	private int animacion;

	public Jogador(Mapa mapa, Teclado teclado, Sprite sprite) {
		super(mapa); // Chama o construtor da superclasse Criatura que exige 'mapa'
		this.teclado = teclado;
		this.sprite = sprite;
	}

	public Jogador(Mapa mapa, Teclado teclado, int posicionX, int posicionY) {
		this(mapa, teclado, Sprite.JOGADOR_BAIXO);
		this.x = posicionX;
		this.y = posicionY;
	}

	public void actualizar() {
		// D eclaração das variáveis locais para deslocamento
		int desplazamientoX = 0;
		int desplazamientoY = 0;
		int velocidadeMovimiento = 1;

		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}

		// Usando os métodos getter do Teclado
		if (teclado.isCorrer()) {
			velocidadeMovimiento = 2;
		}

		if (teclado.isArriba()) {
			desplazamientoY -= velocidadeMovimiento;
		}
		if (teclado.isAbajo()) {
			desplazamientoY += velocidadeMovimiento;
		}
		if (teclado.isIzquierda()) {
			desplazamientoX -= velocidadeMovimiento;
		}
		if (teclado.isDerecha()) {
			desplazamentoX += velocidadeMovimiento;
		}

		// Verifica se houve movimento para chamar o método mover
		if (desplazamentoX != 0 || desplazamientoY != 0) {
			mover(desplazamentoX, desplazamientoY);
			enMovimiento = true; // 'enMovimiento' é da superclasse Criatura
		} else {
			enMovimiento = false;
		}

		int resto = animacion % 40;

		// Lógica de animação baseada na direção e no estado de movimento
		if (direccion == 'n') { // Cima
			if (enMovimiento) { // 'enMovimiento' é da superclasse Criatura
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
			if (enMovimiento) { // 'enMovimiento' é da superclasse Criatura
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
			if (enMovimento) { // 'enMovimiento' é da superclasse Criatura
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
			if (enMovimento) { // 'enMovimiento' é da superclasse Criatura
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
