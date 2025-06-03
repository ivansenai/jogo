package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {

    private final static int NUMERO_TECLAS = 120;
    private final boolean[] teclas = new boolean[NUMERO_TECLAS];

    private boolean arriba;
    private boolean abajo;
    private boolean izquierda;
    private boolean derecha; 
    private boolean correr;
    private boolean salir;

    public void actualizar() {
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D]; // Usando 'derecha'
        salir = teclas[KeyEvent.VK_ESCAPE];
        correr = teclas[KeyEvent.VK_SHIFT];
    }

    public boolean isArriba() {
        return arriba;
    }

    public boolean isAbajo() {
        return abajo;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public boolean isDerecha() { // Getter correspondente
        return derecha;
    }

    public boolean isCorrer() {
        return correr;
    }

    public boolean isSalir() {
        return salir;
    }

    public boolean estaPressionada(int keyCode) {
        if (keyCode >= 0 && keyCode < NUMERO_TECLAS) {
            return teclas[keyCode];
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < NUMERO_TECLAS) {
            teclas[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < NUMERO_TECLAS) {
            teclas[keyCode] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não é necessário implementar para o controle de pressionar/soltar teclas
    }
}
