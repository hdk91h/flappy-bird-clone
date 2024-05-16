import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Floor {

    Image image;

    Floor() {}

    public Image getImage() {

        try {

            image = ImageIO.read(new File("src/asset/sprite/floor.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
