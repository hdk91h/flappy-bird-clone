import java.awt.*;
import javax.swing.*;

public class Board extends JPanel {

    public Background bg;



    Board() {
        bg = new Background();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.drawImage(bg.getImage(), 0, 0, null);

        g.drawImage(bg.getImage(), 288, 0, null);
    }

    
}
