package Jogo;

import javax.swing.*;

public class Janela extends JFrame {
    private Fase fase;

    public Janela() {
        add(new Fase());
        setTitle("Pew Pew Pew");
        setSize(1024, 728); // 1082 x 898
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
    public static void main (String []args) {
        new Janela();
    }
}

