import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bird {

    public int x;
    public int y;

    public int width;
    public int height;

    public boolean collision;

    // velocity
    public double velocityY;
    public double gravity;

    private Image image;

    Bird() {
        x = 100;
        y = 200;
        velocityY = 0;
        width = 40;
        height = 40;
        gravity = 0.6;
        collision = false;
    }



    public Image getImage() {

        try {

            image = ImageIO.read(new File("src/asset/sprite/redbird-midflap.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
    
}
