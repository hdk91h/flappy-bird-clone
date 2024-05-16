import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Background {
    

    private Image image;


    Background() {}

     public Image getImage() {

        try {

            image = ImageIO.read(new File("src/asset/sprite/background.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
