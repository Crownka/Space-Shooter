package Jogo;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class Fase extends JPanel implements ActionListener { // classe da fase

    private Image fundo; // imagem de fundo
    private Player player; // objeto do player
    private Timer timer; // velocidade do jogos

    public Fase() { // construtor da fase
        setFocusable(true); // 
        setDoubleBuffered(true); // 

        ImageIcon referencia = new ImageIcon("files\\Blackground.jpg");
        fundo = referencia.getImage();

        player = new Player(); // cria o player
        player.load(); // carrega a imagem do player

        addKeyListener(new TecladoAdapter()); // adiciona o teclado

        timer = new Timer(4, this); // cria o timer
        timer.start(); // inicia o timer
    }
    
    public void paint(Graphics g) { // desenha a fase
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null); // desenha o fundo
        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this); // desenha o player
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) { // atualiza a fase
        player.update();
        repaint();
    }

    private class TecladoAdapter extends KeyAdapter { // classe do teclado
        @Override
        public void keyPressed(KeyEvent e) { // verifica se alguma tecla foi pressionada
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) { // verifica se alguma tecla foi despressionada
            player.keyReleased(e);
        }
    }
    
}