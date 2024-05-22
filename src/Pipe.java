import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pipe {

    private int x;
    private int y;

    private int width;
    private int height;

    private int velocityX;

    private boolean passed;

    private Image image;

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Image rImage() {
        return this.image;
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
