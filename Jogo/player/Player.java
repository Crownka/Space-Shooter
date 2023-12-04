package Jogo.player;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Player { // classe do player
    private int x, y;
    private int dx, dy;
    private int altura, largura;
    private Image imagem;
    private List <Tiro> tiros; // lista dos tiros
    private List <Vida> vidas; // lista das vidas
    private boolean isVisivel; // se o player está visivel ou não

    public Player() { // onde o player vai começar
        this.x = 80;
        this.y = 80;
        isVisivel = true;
        
        tiros = new ArrayList<Tiro>();
        vidas = new ArrayList<Vida>();

        InitVidas(); // cria as vidas
    }
    
    public void load() { // carrega a imagem do player
        ImageIcon referencia = new ImageIcon("files\\navePlayer.png");
        imagem = referencia.getImage();

        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
    }

    private static final int VELOCIDADE_MAXIMA = 8;
    private static final double AMORTECIMENTO = 1.5;

    public void update() { 
        // Aplica inércia
        dx *= AMORTECIMENTO;
        dy *= AMORTECIMENTO;

        // Atualiza a posição do player
        x += dx;
        y += dy;

        // Limita a velocidade máxima
        if (dx > VELOCIDADE_MAXIMA) {
            dx = VELOCIDADE_MAXIMA;
        } else if (dx < -VELOCIDADE_MAXIMA) {
            dx = -VELOCIDADE_MAXIMA;
        }

        if (dy > VELOCIDADE_MAXIMA) {
            dy = VELOCIDADE_MAXIMA;
        } else if (dy < -VELOCIDADE_MAXIMA) {
            dy = -VELOCIDADE_MAXIMA;
        }
    }

    public void tiroJogador() { // cria um tiro
        this.tiros.add(new Tiro(x + largura, y + (altura / 2)));
    }

    public void InitVidas() { // cria as vidas
        int xInicial = 10;
        int y = 10;
        int espacamento = 30;

        for (int i = 0; i < 5; i++) { // 3 vidas
            Vida vida = new Vida(xInicial + i * espacamento, y);
            vidas.add(vida);
        }
    }

    public void removerVida() { // remove uma vida
        if (vidas.size() > 0) {
            vidas.remove(vidas.size() - 1);
        }
    }

    public Rectangle getBounds() { // retorna o retangulo do player (para colisão)
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla) { // verifica se alguma tecla foi pressionada
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE) {
            tiroJogador();
        }
        if (codigo == KeyEvent.VK_UP) {
            dy = -6;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 6;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = -5;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 5;
        }
    }

    public void keyReleased(KeyEvent tecla) { // verifica se alguma tecla foi despressionada
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public void KeyEvent(KeyEvent tecla) { // verifica se alguma tecla foi pressionada
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = -6;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 6;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = -5;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 5;
        }
    }

    // getters

    public boolean isVisivel() { // retorna se o player está visivel ou não
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) { // seta se o player está visivel ou não
        this.isVisivel = isVisivel;
    }

    public int getX() { // retorna a posição x do player
        return x;
    }

    public int getY() { // retorna a posição y do player
        return y;
    }

    public Image getImagem() { // retorna a imagem do player
        return imagem;
    }

    public List<Tiro> getTiros() { // retorna a lista de tiros
        return tiros;
    }

    public void setX(int i) {
    }

    public void setY(int i) {
    }
}
