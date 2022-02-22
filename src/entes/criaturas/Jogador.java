package entes.criaturas;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Jogador extends Criatura {
	private Teclado teclado;
	private int animacion;

	public Jogador(Mapa mapa, Teclado teclado, Sprite sprite) {
		this.mapa = mapa;
		this.teclado = teclado;
		this.sprite = sprite;
	}

	public Jogador(Mapa mapa, Teclado teclado, Sprite sprite, int posicionX, int posicionY) {
		this(mapa, teclado, sprite); 
		this.teclado = teclado;
		this.sprite = sprite;
		this.x = posicionX;
		this.y = posicionY;
	}

	public void actualizar() {
		int desplazamientoX = 0;
		int desplazamientoY = 0;
		int velocidadeMovimiento = 1;

		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}

		if (teclado.correr) {
			velocidadeMovimiento = 2;
		}

		if (teclado.arriba) {
			desplazamientoY -= velocidadeMovimiento;
		}
		if (teclado.abajo) {
			desplazamientoY += velocidadeMovimiento;
		}
		if (teclado.izquierda) {
			desplazamientoX -= velocidadeMovimiento;
		}
		if (teclado.derecha) {
			desplazamientoX += velocidadeMovimiento;
		}
		int resto = animacion % 40;
		if (desplazamientoX != 0 || desplazamientoY != 0) {
			mover(desplazamientoX, desplazamientoY);
			enMovimiento = true;
		} else {
			enMovimiento = false;
		}
		if (direccion == 'n') {
			sprite = Sprite.ARRIBA0;
			// Comentado no curso 50
			// if (enMovimento) {
			// if (animacion % 30 > 15) {
			// sprite = Sprite.ARRIBA1;
			// } else {
			// sprite = Sprite.ARRIBA1;
			// }
			// }
			// Fim comentado curso 50
			if ((resto > 10) && (resto <= 20)) {
				sprite = Sprite.ARRIBA0_CIRENE_VERMELHA;
			} else if ((resto > 20) && (resto <= 30)) {
				sprite = Sprite.ARRIBA0_CIRENA_BRANCA;
			} else if (resto > 30) {
				sprite = Sprite.ARRIBA0_CIRENE_AZUL;
			} else {
				sprite = Sprite.ARRIBA0;
			}
		}
		if (direccion == 's') {
			sprite = Sprite.ABAJO0;
			// Comentado no curso 50
			// if (enMovimento) {
			// if (animacion % 30 > 15) {
			// sprite = Sprite.ABAJO0;
			// } else {
			// sprite = Sprite.ABAJO0;
			// }
			// }
			// Fim comentado curso 50

			if ((resto > 10) && (resto <= 20)) {
				sprite = Sprite.ABAJO0_CIRENE_VERMELHA;
			} else if ((resto > 20) && (resto <= 30)) {
				sprite = Sprite.ABAJO0_CIRENA_BRANCA;
			} else if (resto > 30) {
				sprite = Sprite.ABAJO0_CIRENE_AZUL;
			} else {
				sprite = Sprite.ABAJO0;
			}
		}
		if (direccion == 'o') {
			sprite = Sprite.IZQUIERDA0;
			// Comentado no curso 50
			// if (enMovimento) {
			// if (animacion % 30 > 15) {
			// sprite = Sprite.IZQUIERDA0;
			// } else {
			// sprite = Sprite.IZQUIERDA0;
			// }
			// }
			// Fim comentado curso 50

			if ((resto > 10) && (resto <= 20)) {
				sprite = Sprite.IZQUIERDA0_CIRENE_VERMELHA;
			} else if ((resto > 20) && (resto <= 30)) {
				sprite = Sprite.IZQUIERDA0_CIRENE_BRANCA;
			} else if (resto > 30) {
				sprite = Sprite.IZQUIERDA0_CIRENE_AZUL;
			} else {
				sprite = Sprite.IZQUIERDA0;
			}
		}
		if (direccion == 'e') {
			sprite = Sprite.DERECHA0;
			// Comentado no curso 50
			// if (enMovimento) {
			// if (animacion % 30 > 15) {
			// sprite = Sprite.DERECHA0;
			// } else {
			// sprite = Sprite.DERECHA0;
			// }
			// }
			// Fim comentado curso 50

			if ((resto > 10) && (resto <= 20)) {
				sprite = Sprite.DERECHA0_CIRENE_VERMELHA;
			} else if ((resto > 20) && (resto <= 30)) {
				sprite = Sprite.DERECHA0_CIRENE_BRANCA;
			} else if (resto > 30) {
				sprite = Sprite.DERECHA0_CIRENE_AZUL;
			} else {
				sprite = Sprite.DERECHA0;
			}
		}
	}

	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarJogador(x, y, this);
	}

}
