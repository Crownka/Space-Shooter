package Jogo;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Estrelas {
    
    private Image imagem; // imagem do Estrela
    private int x, y; // posição do Estrela
    private int largura, altura; // tamanho do Estrela (caso precise)
    private boolean isVisivel; // se o Estrela está visivel ou não

    //private static final int LARGURA = 990; // até onde o Estrela pode ir
    private static int VELOCIDADE = 20; // velocidade do Estrela

    public Estrelas(int x, int y) { // construtor do Estrela
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load() { // carrega a imagem do Estrela
        ImageIcon referencia = new ImageIcon("files\\Estrela.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update() { // atualiza a posição do Estrela
        if (this.x < 0) { 
            this.x = 1024; // se o Estrela passar da tela, ele volta para o começo
            Random a = new Random();
            int b = a.nextInt(500); // posição aleatória
            this.x = b + 1024; // posição aleatória

            Random c = new Random();
            int d = c.nextInt(768); // posição aleatória
            this.y = d; // posição aleatória
        } else {
            this.x -= VELOCIDADE;
        }
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
        Estrelas.VELOCIDADE = VELOCIDADE;
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
