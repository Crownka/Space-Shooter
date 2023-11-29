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
    private boolean isVisivel; // se o player está visivel ou não

    public Player() { // onde o player vai começar
        this.x = 80;
        this.y = 80;
        isVisivel = true;
        
        tiros = new ArrayList<Tiro>();
    }
    
    public void load() { // carrega a imagem do player
        ImageIcon referencia = new ImageIcon("files\\Nave.png");
        imagem = referencia.getImage();

        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
    }

    public void update() { // atualiza a posição do player
        x += dx;
        y += dy;
    }

    public void tiroJogador() { // cria um tiro
        this.tiros.add(new Tiro(x + largura, y + (altura / 2)));
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
            dy = -8;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 8;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = -8;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 8;
        }
    }

    public void keyReleased(KeyEvent tecla) { // verifica se alguma tecla foi despressionada
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public void KeyEvent(KeyEvent tecla) { // verifica se alguma tecla foi pressionada
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = -8;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 8;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = -8;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 8;
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
}
