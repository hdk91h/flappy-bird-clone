import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Board extends JPanel implements KeyListener, ActionListener {

    public Background bg;
    public Floor floor;
    public Pipe pipe;
    public Bird bird;

    Timer gameLoop;
    Timer pipePlace;

    boolean gameOver = false;


    Board() {
        bg = new Background();
        floor = new Floor();
        pipe = new Pipe();
        bird = new Bird();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        // draw bg
        g.drawImage(bg.getImage(), 0, 0, null);
        g.drawImage(bg.getImage(), 288, 0, null);
        // draw floor
        g.drawImage(floor.getImage(), 0, 512, null);
        g.drawImage(floor.getImage(), 288, 512, null);

        // pipe
        g.drawImage(pipe.getImage(), 288, 288, null);

        // bird
        g.drawImage(bird.getImage(), bird.x, bird.y, null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    
}
