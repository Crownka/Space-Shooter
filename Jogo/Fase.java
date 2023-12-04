package Jogo;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import Jogo.enemy.Enemy1;
import Jogo.player.Player;
import Jogo.player.Tiro;
import Jogo.player.Vida;



public class Fase extends JPanel implements ActionListener { // classe da fase

    private Image fundo; // imagem de fundo
    private Player player; // objeto do player
    private Timer timer; // velocidade do jogos
    private List<Vida> vidas; // lista das vidas
    private List<Enemy1> enemy1; // lista dos enemy1
    private List<Estrelas> estrelas; // lista dos enemy1
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
        
        vidas = new ArrayList<Vida>(); // cria a lista das vidas
        enemy1 = new ArrayList<Enemy1>(); // inicializa a lista dos enemy1
        
        InitVidas(); // cria as vidas no construtor da fase
        InitEstrelas(); // cria os estrelas no construtor da fase
    
        ingame = true; // o jogo está rodando
    
        for (int i = 0; i < 5; i++) { // cria inicialmente 5 inimigos
            criarInimigo();
        }
    }

    public void reiniciar() {   // Reinicialize todas as variáveis necessárias para reiniciar o jogo
        vidas.clear();
        enemy1.clear();
        InitVidas();
        InitEstrelas();
        ingame = true;
    
        for (int i = 0; i < 5; i++) {
            criarInimigo();
        }
    
        player.setVisivel(true);
        player.setX(80);
        player.setY(80);
    
        repaint();
    }
  
    public void InitVidas() { // cria as vidas
        int xInicial = 10;
        int y = 10;
        int espacamento = 30;

        for (int i = 0; i < 3; i++) { // 3 vidas
            Vida vida = new Vida(xInicial + i * espacamento, y);
            vidas.add(vida);
        }
    }

    private void criarInimigo() {
        int x = (int) (Math.random() * 800 + 1024); // posição aleatória
        int y = (int) (Math.random() * 650 + 30); // posição aleatória
        enemy1.add(new Enemy1(x, y)); // cria o enemy1
    }

    public void InitEstrelas() {
        int coordenadas[] = new int[50];
        estrelas = new ArrayList<Estrelas>();

        for (int i = 0; i < coordenadas.length; i++) { // loop que cria os estrelas
            int x = (int) (Math.random() * 1024 + 0); // posição aleatória
            int y = (int) (Math.random() * 768 + 0); // posição aleatória
            estrelas.add(new Estrelas(x, y)); // cria o estrelas
        }
    }

    @Override
    public void paintComponent(Graphics g) { // desenha a fase
        super.paintComponent(g); 

        if (ingame) {
            g.drawImage(fundo, 0, 0, null);

            for (Estrelas st : estrelas) { // loop que desenha os estrelas
                st.load();
                g.drawImage(st.getImagem(), st.getX(), st.getY(), this);
            }

            g.drawImage(player.getImagem(), player.getX(), player.getY(), this); // desenha o player

            List<Tiro> tiros = player.getTiros(); // Coloca lista dos tiros na fase
            for (Tiro t : tiros) {
                t.load();
                g.drawImage(t.getImagem(), t.getX(), t.getY(), this);
            }

            for (Enemy1 in : enemy1) { // loop que desenha os enemy1
                in.load();
                g.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            desenharVidas(g);
        } else {
            ImageIcon fimJogo = new ImageIcon("files\\GameOver.png");
            g.drawImage(fimJogo.getImage(), 0, 0, null);
        }

        Toolkit.getDefaultToolkit().sync(); // sincroniza o jogo
        g.dispose();
    }

    public void desenharVidas(Graphics g) { // desenha as vidas
        for (Vida vida : vidas) {
            vida.paintComponent(g);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) { // atualiza a fase
        player.update();
    
        for (int i = 0; i < estrelas.size(); i++) { // loop que atualiza os estrelas
            Estrelas st = estrelas.get(i);
            if (st.isVisivel()) {
                st.update();
            } else {
                estrelas.remove(i);
            }
        }
    
        List<Tiro> tiros = player.getTiros(); 
        for (int i = 0; i < tiros.size(); i++) { // loop que atualiza os tiros
            Tiro t = tiros.get(i);
            if (t.isVisivel()) {
                t.update();
            } else {
                tiros.remove(i);
            }
        }
    
        List<Enemy1> inimigosARemover = new ArrayList<>(); // lista dos enemy1 a remover
        for (Enemy1 in : enemy1) {
            if (in.isVisivel()) {
                in.update();
            } else {
                inimigosARemover.add(in);
            }
        }
    
        enemy1.removeAll(inimigosARemover);
        for (int i = 0; i < inimigosARemover.size(); i++) { // loop que cria os enemy1
            criarInimigo();
        }
    
        checkCollisions();
        repaint();
    }

    // tive que mudar o nome da função para checkCollisions, pois a função checkCollision já existe na classe Rectangle

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
                if (vidas.isEmpty()) {
                    ingame = false; // O jogo para quando não há mais vidas
                } else {
                    // Continue o jogo, resetando a posição do player e inimigos
                    player.setVisivel(true);
                    player.setX(80);
                    player.setY(80);
                }
                

                if (!vidas.isEmpty()) { // se ainda tiver vidas
                    vidas.remove(vidas.size() - 1); // remove uma vida
                }
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
            if (ingame) {
                player.keyPressed(e); // se o jogo está rodando, o player pode se mover
            } else {
                if (e.getKeyCode() == KeyEvent.VK_R) { // se o jogo não está rodando, o jogo reinicia ao apertar enter
                    reiniciar();
                }
            }
        }
    
        @Override
        public void keyReleased(KeyEvent e) { // verifica se alguma tecla foi solta
            player.keyReleased(e);
        }
    }
}