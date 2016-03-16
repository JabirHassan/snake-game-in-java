
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

//Level 1 state
public class Level1State extends GameState {

    private Random rand = new Random();
    private ArrayList<Integer> xCoor = new ArrayList<>();    //Holds x coordinates
    private ArrayList<Integer> yCoor = new ArrayList<>();    //Holds y coordinates

    private ArrayList<Snake> snakeBody; //Holds snake parts
    private Snake snake;
    private Food food;
    private int snakeSize = 3; //snake minimum size
    private BufferedImage image;    //background image
    private BufferedImage body;     //snake body image

    //snake's location
    private int x, y;
    private int width = 20, height = 20;
    //Movement boundaries for snake
    private int sceneX = 15;
    private int sceneY = 65;
    private int sceneWidth = 660;
    private int sceneHeight = 510;

    private int lives = 3;

    public static int score = 0;

    //Direction of snake's movement
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    private int refresh = 0;    //To track how man times the game refreshes (updates)

    //Constructor
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        snakeBody = new ArrayList<>();
        food = new Food();
        init();
        addCoors();
        setXY();
    }

    @Override
    public void init() {
        try {
            image = ImageIO.read(getClass().getResource("/images/background.png"));
            body = ImageIO.read(getClass().getResource("/images/body.png"));
        } catch (IOException ex) {
            Logger.getLogger(Level1State.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //creates x and y coordinates

    public void addCoors() {
        for (int i = 25; i < 630; i++) {
            xCoor.add(i);
        }
        for (int i = 80; i < 470; i++) {
            yCoor.add(i);
        }
    }

    //sets a random snake position

    public void setXY() {
        x = xCoor.get(rand.nextInt(xCoor.size() - 1));
        y = yCoor.get(rand.nextInt(yCoor.size() - 1));
    }

    //Draws the snake
    public void drawSnake(Graphics2D g) {
        for (int i = 0; i < snakeBody.size(); i++) {

            snakeBody.get(i).draw(g);
        }
    }

    //creates the snake 
    public void createSnake() {
        if (up) {
            y -= 15;
        }
        if (down) {
            y += 15;
        }
        if (left) {
            x -= 15;
        }
        if (right) {
            x += 15;
        }

        snakeBody.add(new Snake(body, x, y, width, height));
        if (snakeBody.size() > snakeSize) {
            snakeBody.remove(0);
        }
    }

    //Checks collision with food
    public void checkFood() {
        if (food.getX() >= x && food.getX() + food.getWidth() <= x + width && food.getY() >= y && food.getY() + food.getHeight() <= y + height) {
            snakeBody.add(new Snake(body, x, y, width, height));    // Adds new part to snake body
            food.addFood();
            score++;
        }

    }

    @Override
    public void update() {
        //Creates the main snake body
        if (snakeBody.size() == 0) {
            snakeBody.add(new Snake(body, x, y, width, height));
        }
        refresh++;
        if (refresh > 7) {
            createSnake();
            refresh = 0;
        }
        checkFood();
        checkLives();
        //If level is complete
        if (score == 10) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level1State.class.getName()).log(Level.SEVERE, null, ex);
            }
            snakeBody.clear();  //Resets the snakeBody
            GameStateManager.currentState = GameStateManager.LEVEL2STATE;   // sets the GameState to Level2State
        }

    }

    //Tracks is the player hits the boundaries
    public void checkLives() {
        if (checkCollision()) {
            snakeBody.clear();  // Resets the snakeBody
            lives--;    //Removes one live
            //Resets snake's movement
            up = false;
            down = false;
            left = false;
            right = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level1State.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Sets a new position for the snake
            setXY();
            //If all lives are lost
            if (lives == 0) {
                GameStateManager.currentState = GameStateManager.GAMEOVERSTATE; //Changes the GameState to GameOverState
                lives = 3;  //Resets lives
            }
        }
    }

    //Checks collision with food
    public boolean checkCollision() {
        if (snakeBody.get(0).getX() < 15 || snakeBody.get(0).getX() + width > 660 + width
                || snakeBody.get(0).getY() <= 65 || snakeBody.get(0).getY() + height > 575) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        //Draws the background
        g.drawImage(image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        g.setColor(Color.yellow);
        //Draws the game Boundaries
        g.drawRect(sceneX, sceneY, sceneWidth, sceneHeight);
        Font font = new Font("Century Gothic", Font.PLAIN, 25);
        g.setFont(font);
        g.drawString("Level 1", 300, 30);
        g.drawString("Lives ", 570, 30);
        g.drawString("Score: " + score, 50, 30);

        for (int i = 1; i <= lives; i++) {
            g.fillRect(560 + (i * 15), 40, 10, 10);
        }
        drawSnake(g);   //Draw the snake
        food.draw(g);   //Draws food
    }

    //Tracks the player key presses and updates directions,  movement in opposite direction is prevented
    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_UP && down != true) {
            up = true;
            down = false;
            left = false;
            right = false;

        }
        if (k == KeyEvent.VK_DOWN && up != true) {
            up = false;
            down = true;
            left = false;
            right = false;

        }
        if (k == KeyEvent.VK_LEFT && right != true) {
            up = false;
            down = false;
            left = true;
            right = false;

        }
        if (k == KeyEvent.VK_RIGHT && left != true) {
            up = false;
            down = false;
            left = false;
            right = true;

        }
    }

    @Override
    public void keyReleased(int k) {

    }

}
