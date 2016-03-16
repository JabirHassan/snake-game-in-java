
package snakegame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

//the main game panel
public class GamePanel extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 700, HEIGHT = 600;

    private BufferedImage image;
    private Graphics2D g;

    private Thread thread;
    private boolean running;

    private GameStateManager gsm;

    //Constructor

    public GamePanel() {

        gsm = new GameStateManager();   // initialise a new GameStateManager object
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

    }

    public void addNotify() {
        super.addNotify();
        //start the thread
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    public void init() {
        //initialise the buffered image 
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        running = true;

    }

    // run method that contains the main game loop
    @Override
    public void run() {
        init();
        while (running) {
            update();
            draw();
            paint();

            //set the thread sleep time
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //update the game
    public void update() {
        //call the update method in GameStateManager object
        gsm.update();
    }

    public void draw() {
        //call the draw method in GameStateManager object
        gsm.draw(g);
    }

    //paint to the screen
    public void paint() {
        //creates new bufferStrategy for smoother drawing
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g2 = bs.getDrawGraphics();
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g2.dispose();
        bs.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //invoke keyPressed method in gam object
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //invoke Released method in gam object
        gsm.keyReleased(e.getKeyCode());
    }

}

