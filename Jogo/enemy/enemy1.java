package Jogo.enemy;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {
    
    private Image imagem; // imagem do Enemy1
    private int x, y; // posição do Enemy1
    private int largura, altura; // tamanho do Enemy1
    private boolean isVisivel; // se o Enemy1 está visivel ou não

    //private static final int LARGURA = 990; // até onde o Enemy1 pode ir
    private static int VELOCIDADE = 10; // velocidade do Enemy1

    public Enemy1(int x, int y) { // construtor do Enemy1
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load() { // carrega a imagem do Enemy1
        ImageIcon referencia = new ImageIcon("files\\Enemy1.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update() { // atualiza a posição do Enemy1
        this.x -= VELOCIDADE;
        //if (this.x > LARGURA) {
            //isVisivel = false;
        //}
    }

    public Rectangle getBounds() { // retorna o retangulo do tiro (para colisão)
        return new Rectangle(x, y, largura, altura);
    }

    // getters

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int VELOCIDADE) {
        Enemy1.VELOCIDADE = VELOCIDADE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
}
