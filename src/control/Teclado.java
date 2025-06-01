package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {

    private final static int NUMERO_TECLAS = 120; // Constante em maiúsculas
    private final boolean[] teclas = new boolean[NUMERO_TECLAS];

    // Variáveis agora privadas
    private boolean arriba;
    private boolean abajo;
    private boolean izquierda;
    private boolean derecha;
    private boolean correr;
    private boolean salir;

    /**
     * Atualiza o estado das variáveis de controle com base nas teclas pressionadas.
     * Este método deve ser chamado uma vez por frame do jogo.
     */
    public void actualizar() {
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        direita = teclas[KeyEvent.VK_D];
        salir = teclas[KeyEvent.VK_ESCAPE];
        correr = teclas[KeyEvent.VK_SHIFT];
    }

    // --- Getters para as variáveis de controle ---
    public boolean isArriba() {
        return arriba;
    }

    public boolean isAbajo() {
        return abajo;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public boolean isDireita() { // Renomeado para "Direita" para consistência
        return direita;
    }

    public boolean isCorrer() {
        return correr;
    }

    public boolean isSalir() {
        return salir;
    }

    /**
     * Verifica se uma tecla específica está pressionada.
     * @param keyCode O código da tecla a ser verificada (ex: KeyEvent.VK_SPACE).
     * @return true se a tecla estiver pressionada, false caso contrário.
     */
    public boolean estaPressionada(int keyCode) {
        if (keyCode >= 0 && keyCode < NUMERO_TECLAS) { // Adiciona validação para evitar ArrayIndexOutOfBoundsException
            return teclas[keyCode];
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < NUMERO_TECLAS) { // Adiciona validação
            teclas[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < NUMERO_TECLAS) { // Adiciona validação
            teclas[keyCode] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não é necessário implementar para o controle de pressionar/soltar teclas
    }
}
