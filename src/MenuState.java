
package snakegame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

//The main menu class
public class MenuState extends GameState {

    private BufferedImage image;    //background image

    private String[] choice = {"Start", "Quit"}; //The menu choices
    private int currentChoice = 0;

    private Font font;

    //Constructor
    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        font = new Font("Impact", Font.PLAIN, 50); //sets the font used for the menu
        init();
    }

    @Override
    public void init() {
        //Initialise the background image
        try {
            image = ImageIO.read(getClass().getResource("/images/main.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        //Draws the background
        g.drawImage(image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        g.setColor(Color.yellow);
        g.setFont(font);
        g.drawString("S N A K E", 200, 150);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        //Draws the menu
        for (int i = 0; i < choice.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.yellow);
            }
            g.drawString(choice[i], 200, 230 + i * 40);
        }
    }

    //Tracks the user choice
    public void select() {

        if (currentChoice == 0) {
            //changes the GameState to Level1State
            gsm.setState(GameStateManager.LEVEL1STATE);
        }

        if (currentChoice == 1) {
            System.exit(0); //game exit
        }
    }

    //tracks the user's keypresses and updates the menu
    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = choice.length;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == choice.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }

}

