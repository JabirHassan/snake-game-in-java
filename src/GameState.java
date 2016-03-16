
package snakegame;

import java.awt.Graphics2D;

//Abstract class that all game state classes inherit from
public abstract class GameState {

    GameStateManager gsm;

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);
}

