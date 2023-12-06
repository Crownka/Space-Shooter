package Jogo;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import Jogo.Enemies.Enemy;
import Jogo.Enemies.StrongEnemy;
import Jogo.player.Player;
import Jogo.player.Tiro;
import Jogo.player.Vida;



public class Fase extends JPanel implements ActionListener { // classe da fase

    private Image fundo; // imagem de fundo
    private Player player; // objeto do player
    private Timer timer; // velocidade do jogos
    private List<Vida> vidas; // lista das vidas
    private List<Enemy> enemies; // Altere de Enemy para enemies
    private List<Estrelas> estrelas; // lista dos Enemy
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
        enemies = new ArrayList<Enemy>();
        
        InitVidas(); // cria as vidas no construtor da fase
        InitEstrelas(); // cria os estrelas no construtor da fase
    
        ingame = true; // o jogo está rodando
    
        for (int i = 0; i < 5; i++) { // cria inicialmente 5 inimigos
            criarInimigo();
        }
    }

    public void reiniciar() {   // Reinicialize todas as variáveis necessárias para reiniciar o jogo
        vidas.clear();
        enemies.clear();
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
        int x = (int) (Math.random() * 800 + 1024);
        int y = (int) (Math.random() * 650 + 30);
    
        // Polimorfismo: Enemy pode ser Enemy ou StrongEnemy
        Enemy enemy;
        if (Math.random() < 0.2) {
            enemy = new StrongEnemy(x, y);
        } else {
            enemy = new Enemy(x, y, 1); // inimigo normal com 1 vida
        }
    
        enemy.load();
        enemies.add(enemy);
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

            for (Enemy in : enemies) { // loop que desenha os Enemy
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

        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (enemy.isVisivel()) {
                enemy.update();
            } else {
                enemiesToRemove.add(enemy);
            }
        }

    enemies.removeAll(enemiesToRemove);
    for (int i = 0; i < 5 - enemies.size(); i++) {
        criarInimigo();
    }
        
        checkCollisions();
        repaint();
    }

    // tive que mudar o nome da função para checkCollisions, pois a função checkCollision já existe na classe Rectangle

    // ... (código existente)

public void checkCollisions() { // verifica as colisões
    Rectangle formaNave = player.getBounds(); // retângulo do player
    Rectangle formaEnemy; // retângulo do Enemy
    Rectangle formaTiro; // retângulo do tiro

    for (int i = 0; i < enemies.size(); i++) {
        Enemy tempEnemy = enemies.get(i); // pega o Enemy da lista
        formaEnemy = tempEnemy.getBounds(); // retângulo do Enemy

        if (formaNave.intersects(formaEnemy)) { // verifica se o player colidiu com o Enemy
            player.setVisivel(false); // se colidiu, o player fica invisível
            tempEnemy.setVisivel(false); // se colidiu, o Enemy fica invisível
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
        formaTiro = tempTiro.getBounds(); // retângulo do tiro

        for (int j = 0; j < enemies.size(); j++) {
            Enemy tempEnemy = enemies.get(j); // pega o Enemy da lista
            formaEnemy = tempEnemy.getBounds(); // retângulo do Enemy

            if (formaTiro.intersects(formaEnemy)) { // verifica se o tiro colidiu com o Enemy
                tempEnemy.diminuirVida(); // Diminui a vida do inimigo
                tempTiro.setVisivel(false); // se colidiu, o tiro fica invisível

                if (!tempEnemy.isVisivel()) {
                    // Se o inimigo ficou invisível após ser atingido, cria um novo inimigo
                    enemies.remove(j);
                    criarInimigo();
                }
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