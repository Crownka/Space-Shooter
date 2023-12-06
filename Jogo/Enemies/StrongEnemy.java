package Jogo.Enemies;

import javax.swing.ImageIcon;

public class StrongEnemy extends Enemy {

    public StrongEnemy(int x, int y) {
        super(x, y, 3);
        setVELOCIDADE(6);
        load();
    }

    @Override
    public void load() {
        ImageIcon referencia = new ImageIcon("files\\StrongEnemy.png");
        setImagem(referencia.getImage());
        setLargura(getImagem().getWidth(null));
        setAltura(getImagem().getHeight(null));
    }
}
