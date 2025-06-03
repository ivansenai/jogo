package entes.criaturas;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Jogador extends Criatura {
	private Teclado teclado;
	private int animacion; // Usado para controle de animação

	// Construtor principal para Jogador
	public Jogador(Mapa mapa, Teclado teclado, Sprite sprite) {
		super(mapa); // Chama o construtor da superclasse Criatura que exige um Mapa
		this.teclado = teclado;
		this.sprite = sprite; // Define o sprite inicial do jogador
	}

	// Construtor auxiliar para facilitar a criação do jogador com posição e sprite padrão
	public Jogador(Mapa mapa, Teclado teclado, int posicionX, int posicionY) {
		this(mapa, teclado, Sprite.JOGADOR_BAIXO); // Usa o construtor acima com sprite padrão
		this.x = posicionX;
		this.y = posicionY;
	}

	@Override
	public void actualizar() {
		// !!! ATENÇÃO AQUI: DECLARAÇÃO DAS VARIÁVEIS LOCAIS !!!
		int desplazamientoX = 0;
		int desplazamientoY = 0;
		// !!! FIM DA ATENÇÃO !!!

		int velocidadeMovimiento = 1;

		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}

		// Verifica se o jogador está correndo usando o getter do Teclado
		if (teclado.isCorrer()) { // Usando o getter correto
			velocidadeMovimiento = 2;
		}

		// Atualiza o deslocamento com base nas teclas pressionadas
		// Usa os getters do Teclado
		if (teclado.isArriba()) { // Usando o getter correto
			desplazamentoY = velocidadeMovimiento - 1;
		}
		if (teclado.isAbajo()) { // Usando o getter correto
			desplazamentoY = velocidadeMovimiento + 1;
		}
		if (teclado.isIzquierda()) { // Usando o getter correto
			desplazamentoX = velocidadeMovimiento - 1;
		}
		if (teclado.isDerecha()) { // Usando o getter correto
			desplazamentoX = velocidadeMovimiento + 1;
		}

		// Se houver algum deslocamento, move o jogador
		if (desplazamentoX != 0 || desplazamientoY != 0) {
			mover(desplazamentoX, desplazamientoY);
			// 'enMovimiento' é herdado de Criatura e deve estar acessível
			enMovimiento = true;
		} else {
			enMovimiento = false;
		}

		// Lógica de animação do jogador baseada na direção e movimento
		int resto = animacion % 40; // Controla a fase da animação

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
		} else if (direccion == 's') { // Sul (Baixo)
			if (enMovimento) {
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
			if (enMovimento) {
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
