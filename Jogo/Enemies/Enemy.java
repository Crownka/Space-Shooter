package Jogo.Enemies;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {

    private Image imagem; // imagem do Enemy
    private int x, y; // posição do Enemy
    private int largura, altura; // tamanho do Enemy
    private int vida; // vida dos inimigos
    private boolean isVisivel; // se o Enemy está visivel ou não
    private static int VELOCIDADE = 10; // velocidade do Enemy

    public Enemy(int x, int y, int vida) { // Construtor do Enemy
        this.x = x;
        this.y = y;
        this.vida = vida; // Inicializa a vida do inimigo
        isVisivel = true;
    }

    public void diminuirVida() {
        vida--;
        if (vida <= 0) {
            isVisivel = false; // Inimigo se torna invisível quando a vida chegar em 0
        }
    }

    public void load() { // carrega a imagem do Enemy
        ImageIcon referencia = new ImageIcon("files\\naveInimigo.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update() { // atualiza a posição do Enemy
        this.x -= VELOCIDADE;
        if (this.x < 0) {
            isVisivel = false;
        }
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
        Enemy.VELOCIDADE = VELOCIDADE;
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

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
