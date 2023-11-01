package Jogo.player;

import java.awt.Image;
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

    public Player() { // onde o player vai começar
        this.x = 80;
        this.y = 80;
        
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
