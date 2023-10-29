package Jogo;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Tiro {
    
    private Image imagem; // imagem do tiro
    private int x, y; // posição do tiro
    private int largura, altura; // tamanho do tiro
    private boolean isVisivel; // se o tiro está visivel ou não

    private static final int LARGURA = 990; // até onde o tiro pode ir
    private static int VELOCIDADE = 7; // velocidade do tiro

    public Tiro(int x, int y) { // construtor do tiro
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load() { // carrega a imagem do tiro
        ImageIcon referencia = new ImageIcon("files\\TiroVerde.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update() { // atualiza a posição do tiro
        this.x += VELOCIDADE;
        if (this.x > LARGURA) {
            isVisivel = false;
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
        Tiro.VELOCIDADE = VELOCIDADE;
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
