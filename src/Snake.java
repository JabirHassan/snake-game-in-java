
package snakegame;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Snake {

    private BufferedImage image;    //food image
    //snake location
    private int x, y;
    private int width, height;

    //Constructor

    public Snake(BufferedImage image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics2D g) {
        //Draws the snake
        g.drawImage(image, x, y, width, height, null);
    }
}

