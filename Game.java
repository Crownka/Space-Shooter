/*import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Game extends Canvas implements Runnable {
    public Game() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Tecla pressionada 
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Tecla despressionada
            }
        });
    }

    public void update() {
        
    }

    public void render() {
        
    }

    @Override
    public void run() {
        
        while(true) {
            update();
            render();
            
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
} */