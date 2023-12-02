package Jogo.player;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Vida extends JComponent { // classe da vida

    private Image imagem;
    private int x, y;

    public Vida(int x, int y) { // construtor da vida
        this.x = x;
        this.y = y;

        ImageIcon referencia = new ImageIcon("files\\Vida.png"); // imagem da vida
        imagem = referencia.getImage();
    }

    @Override
    public void paintComponent(Graphics g) { // desenha a vida
        super.paintComponent(g);
        g.drawImage(imagem, x, y, this);
    }

    // getters e setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
