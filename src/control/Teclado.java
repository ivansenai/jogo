
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

Explicação das Mudanças
 * NUMERO_TECLAS em Maiúsculas: Constantes estáticas (static final) em Java são geralmente nomeadas em MAIUSCULAS_COM_UNDERLINE para indicar que são valores fixos.
 * Variáveis de Controle Privadas: arriba, abajo, izquierda, derecha, correr, salir agora são private.
 * Métodos is...() (Getters): Adicionei métodos isArriba(), isAbajo(), etc., para acessar o estado dessas variáveis de forma controlada.
 * isDireita(): Renomeei direita para isDireita() para manter a convenção de nomenclatura.
 * Método estaPressionada(): Um novo método que permite verificar o estado de qualquer tecla, não apenas as que você mapeou nas variáveis de direção. Isso torna sua classe mais flexível.
 * Validação de keyCode: Adicionei verificações if (keyCode >= 0 && keyCode < NUMERO_TECLAS) em keyPressed, keyReleased e estaPressionada. Isso previne um possível ArrayIndexOutOfBoundsException se um keyCode inesperado (que esteja fora dos limites do seu array teclas) for recebido. Embora o KeyEvent geralmente retorne códigos válidos, é uma boa prática de segurança para robustez do código.
 * Comentários Javadoc: Adicionei comentários Javadoc (o que começa com /**) para os métodos actualizar() e estaPressionada(). Isso é útil para documentar seu código e torna mais fácil para você (ou para outros desenvolvedores) entender o que cada método faz no futuro.
Se tiver alguma dúvida ou quiser explorar outras formas de melhorar o código, me diga!
