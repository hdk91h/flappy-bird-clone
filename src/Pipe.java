import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pipe {

    public int x;
    public int y;

    private Image image;


    Pipe() {

        x = 500;
        y = 0;
        
    }

    public Image getImage() {

        try {

            image = ImageIO.read(new File("src/asset/sprite/pipe-green.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
    
}
