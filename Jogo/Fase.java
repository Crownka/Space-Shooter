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
    private boolean ingame; // se o jogo está rodando ou não

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
        ingame = true; // o jogo está rodando
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
        if (ingame == true) {

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
        } else {
            ImageIcon fimJogo = new ImageIcon("files\\GameOver.jpg"); // imagem de fim de jogo
            graficos.drawImage(fimJogo.getImage(), 0, 0, null); // desenha a imagem de fim de jogo
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
        checkCollisions(); // chama a função que verifica as colisões
        repaint();
    }

    public void checkCollisions() { // verifica as colisões
        Rectangle formaNave = player.getBounds(); // retangulo do player
        Rectangle formaEnemy1; // retangulo do enemy1
        Rectangle formaTiro; // retangulo do tiro

        for (int i = 0; i < enemy1.size(); i++) {
            Enemy1 tempEnemy1 = enemy1.get(i); // pega o enemy1 da lista
            formaEnemy1 = tempEnemy1.getBounds(); // retangulo do enemy1

            if (formaNave.intersects(formaEnemy1)) { // verifica se o player colidiu com o enemy1
                player.setVisivel(false); // se colidiu, o player fica invisivel
                tempEnemy1.setVisivel(false); // se colidiu, o enemy1 fica invisivel
                ingame = false; // o jogo para
            }
        }

        List<Tiro> tiros = player.getTiros(); // lista dos tiros
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tempTiro = tiros.get(i); // pega o tiro da lista
            formaTiro = tempTiro.getBounds(); // retangulo do tiro

            for (int j = 0; j < enemy1.size(); j++) {
                Enemy1 tempEnemy1 = enemy1.get(j); // pega o enemy1 da lista
                formaEnemy1 = tempEnemy1.getBounds(); // retangulo do enemy1

                if (formaTiro.intersects(formaEnemy1)) { // verifica se o tiro colidiu com o enemy1
                    tempEnemy1.setVisivel(false); // se colidiu, o enemy1 fica invisivel
                    tempTiro.setVisivel(false); // se colidiu, o tiro fica invisivel
                }
            }
        }
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