
package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Food {

    private Random rand;

    private ArrayList<Integer> xCoor;   //Holds x coordinates
    private ArrayList<Integer> yCoor;   //Hold y coordinates

    private int x, y, width = 6, height = 6;
    //Boundaries that within the food must be drawn
    private int topBound = 70;
    private int bottomBound = 500;
    private int leftBound = 25;
    private int rightBound = 650;

    //onstructor
    public Food() {
        rand = new Random();
        xCoor = new ArrayList<Integer>();
        yCoor = new ArrayList<Integer>();
        addCoors();
        addFood();

    }

    //Creates x and y coordinates

    public void addCoors() {
        for (int i = leftBound; i < rightBound; i++) {
            xCoor.add(i);
        }
        for (int i = topBound; i < bottomBound; i++) {
            yCoor.add(i);
        }
    }

    //Creates random coordinates for the food

    public void addFood() {
        x = xCoor.get(rand.nextInt(xCoor.size() - 1));
        y = yCoor.get(rand.nextInt(yCoor.size() - 1));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //Draws the food

    public void draw(Graphics2D g) {
        g.setColor(Color.red);
        g.drawOval(x, y, width, height);
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
        g.setColor(Color.yellow);
    }

}

