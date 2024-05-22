import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bird {

    private int x;
    private int y;

    private int width;
    private int height;

    private boolean collision;

    // velocity
    private int velocityY;
    private int gravity;

    private Image image;

    Bird() {
        x = 100;
        y = 200;
        width = 30;
        height = 20;
        velocityY =0;
        gravity = 1;
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

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
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
