package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import control.Teclado;
import entes.criaturas.Jogador;
import graficos.Pantalla;
import mapa.Mapa;
import mapa.MapaCargado;

public class Juego extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private static final int ANCHO = 1024;
    private static final int ALTO = 800;
    private static volatile boolean enFuncionamiento = false;
    private static final String NOMBRE = "Meu Jogo Top-Down";

    private static String CONTADOR_APS = "";
    private static String CONTADOR_FPS = "";

    private static JFrame ventana;
    private static Thread thread;
    private static Teclado teclado;
    private static Pantalla pantalla;
    private static Mapa mapa;
    private static Jogador jogador;

    private BufferedImage imagen;
    private int[] pixeles;

    public Juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO));

        pantalla = new Pantalla(ANCHO, ALTO);
        teclado = new Teclado();
        addKeyListener(teclado);

        imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
        pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

        // Usar MapaCargado, ajuste o caminho da imagem conforme necessário
        mapa = new MapaCargado("/mapas/mapa01.png"); // Exemplo de uso de mapa carregado
        // mapa = new MapaGenerado(128, 128); // Exemplo de uso de mapa gerado (descomente se for usar)

        // Criar o jogador com o mapa, teclado e posição inicial
        jogador = new Jogador(mapa, teclado, 450, 360);

        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }

    private synchronized void iniciar() {
        enFuncionamiento = true;
        thread = new Thread(this, "Graficos");
        thread.start();
    }

    private synchronized void detener() {
        enFuncionamiento = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void actualizar() {
        teclado.actualizar(); // Atualiza o estado das teclas

        jogador.actualizar(); // Atualiza o jogador

        if (teclado.isSalir()) { // Se a tecla ESCAPE for pressionada
            detener();
        }
    }

    private void mostrar() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3); // Cria 3 buffers para otimização
            return;
        }

        pantalla.limpiar(); // Limpa a tela antes de desenhar

        // Ajustar a compensação da câmera com base na posição do jogador
        // Centraliza o jogador na tela
        int compensacionX = jogador.obtenerPosicionX() - (pantalla.obtemAncho() / 2) + (jogador.obtenerSprite().getLado() / 2);
        int compensacionY = jogador.obtenerPosicionY() - (pantalla.obtemAlto() / 2) + (jogador.obtenerSprite().getLado() / 2);

        // Mostra o mapa, aplicando a compensação da câmera
        mapa.mostrar(compensacionX, compensacionY, pantalla);

        // Mostra o jogador, aplicando a compensação da câmera
        // CORREÇÃO AQUI: Chamar o método mostrarJogador da Pantalla
        pantalla.mostrarJogador(jogador.obtenerPosicionX(), jogador.obtenerPosicionY(), jogador);


        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        g.drawString(CONTADOR_APS, 10, 20); // Exibe o contador de APS
        g.drawString(CONTADOR_FPS, 10, 35); // Exibe o contador de FPS
        g.dispose(); // Libera os recursos gráficos
        bs.show(); // Exibe o próximo buffer disponível
    }

    @Override
    public void run() {
        final int APS_OBJETIVO = 60; // Atualizações por segundo desejadas
        final double NS_POR_ACTUALIZACION = 1000000000.0 / APS_OBJETIVO;

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0;

        int aps = 0;
        int fps = 0;

        requestFocus();

        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while (delta >= 1) {
                actualizar();
                aps++;
                delta--;
            }

            mostrar();
            fps++;

            if (System.nanoTime() - referenciaContador > 1000000000) {
                CONTADOR_APS = "APS: " + aps;
                CONTADOR_FPS = "FPS: " + fps;
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
        detener();
    }
}
