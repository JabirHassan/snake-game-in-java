
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

//Called when a player wins
public class WinState extends GameState {

    private BufferedImage image;

    private String[] choice = {"Main Menu", "Quit"}; //Menu

    private int currentChoice = 0;

    private Font font;

    //Constructor
    public WinState(GameStateManager gsm) {
        this.gsm = gsm;
        font = new Font("Century Gothic", Font.PLAIN, 50);
        init();
    }

    @Override
    public void init() {
        try {
            //initialises the background image
            image = ImageIO.read(getClass().getResource("/images/background.png"));
        } catch (IOException ex) {
            Logger.getLogger(GameOverState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        //Draws the bachground
        g.drawImage(image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        g.setColor(Color.yellow);
        g.setFont(font);
        g.drawString("YOU WIN", 220, 220);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        //Draws the menu
        for (int i = 0; i < choice.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.yellow);
            }
            g.drawString(choice[i], 250, 300 + i * 40);
        }
    }

    public void select() {
        if (currentChoice == 0) {
            //Changes the GameState to MenuState (main menu)
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (currentChoice == 1) {
            System.exit(0); // Exits game
        }

    }

    //Tracks the user's choice and updates the menu
    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = choice.length - 1;
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

