import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pipe {

    public int x;
    public int y;

    public int width;
    public int height;

    public int velocityX;

    public boolean passed;

    public Image image;

    private static final String DEFAULT_IMAGE_SRC = "src/asset/sprite/toppipe.png";
  
    Pipe(String src) {

        x = 576;
        y = 0;
        width = 60;
        height = 450;

        this.image = this.getImage(src);

        velocityX = -3;
        passed = false;

    }

    Pipe() {
        this(DEFAULT_IMAGE_SRC);
    }

    public Image getImage(String src) {

        try {

            image = ImageIO.read(new File(src));

            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
    
}
