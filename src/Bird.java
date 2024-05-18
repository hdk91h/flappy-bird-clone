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
    public int velocityY;
    public int gravity;

    private Image image;

    

    Bird() {
        x = 100;
        y = 200;
        width = 30;
        height = 20;
        velocityY =0;
        gravity = 1;
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
