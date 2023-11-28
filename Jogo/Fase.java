package Jogo;

import java.util.ArrayList;
import java.util.List;

import Jogo.enemy.Enemy1;
import Jogo.player.Player;
import java.awt.*;
import Jogo.player.Tiro;
import javax.swing.*;


import java.awt.event.*;

public class Fase extends JPanel implements ActionListener { // classe da fase

    private Image fundo; // imagem de fundo
    private Player player; // objeto do player
    private Timer timer; // velocidade do jogos
    private List<Enemy1> enemy1; // lista dos enemy1

    public Fase() { // construtor da fase
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon("files\\Blackground.jpg");
        fundo = referencia.getImage();

        player = new Player(); // cria o player
        player.load(); // carrega a imagem do player

        addKeyListener(new TecladoAdapter()); // adiciona o teclado

        timer = new Timer(4, this); // cria o timer
        timer.start(); // inicia o timer

        InitEnemy1(); // cria os enemy1 no construtor da fase
    }

    public void InitEnemy1() { // cria os enemy1
        int coordenadas[] = new int[40];
        enemy1 = new ArrayList<Enemy1>();

        for (int i = 0; i < coordenadas.length; i++) { // loop que cria os enemy1
            int x = (int) (Math.random() * 8000 + 1024); // posição aleatória
            int y = (int) (Math.random() * 650 + 30); // posição aleatória
            enemy1.add(new Enemy1(x, y)); // cria o enemy1
        }
    }
    
    public void paint(Graphics g) { // desenha a fase
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null); // desenha o fundo
        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this); // desenha o player

        List<Tiro> tiros = player.getTiros();
        for (int i = 0; i < tiros.size(); i++) { // loop que desenha os tiros
            Tiro t = tiros.get(i);
            t.load();
            graficos.drawImage(t.getImagem(), t.getX(), t.getY(), this);
        }

        for (int i = 0; i < enemy1.size(); i++) { // loop que desenha os enemy1
            Enemy1 in = enemy1.get(i);
            in.load();
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this); // desenha o enemy1
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) { // atualiza a fase
        player.update(); 

        List<Tiro> tiros = player.getTiros(); // Coloca lista dos tiros na fase
        for (int i = 0; i < tiros.size(); i++) { // loop que atualiza a posição dos tiros
            Tiro t = tiros.get(i);
            if (t.isVisivel()) { 
                t.update();
            } else {
                tiros.remove(i);
            }
        }

        for (int i = 0; i < enemy1.size(); i++) { // loop que atualiza a posição dos enemy1
            Enemy1 in = enemy1.get(i);
            if (in.isVisivel()) {
                in.update();
            } else {
                enemy1.remove(i);
            }
        }

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