
package snakegame;

import java.awt.Graphics2D;
import java.util.ArrayList;

//this class tracks and updates the different game states 
public class GameStateManager {

    public ArrayList<GameState> gameStates; //contains the different game states
    public static int currentState;

    public static int MENUSTATE = 0;
    public static int GAMEOVERSTATE = 1;
    public static int LEVEL1STATE = 2;
    public static int LEVEL2STATE = 3;
    public static int WINSTATE = 4;

    public GameStateManager() {
        gameStates = new ArrayList<>();

        currentState = MENUSTATE;   // the default state
        gameStates.add(new MenuState(this));
        gameStates.add(new GameOverState(this));
        gameStates.add(new Level1State(this));
        gameStates.add(new Level2State(this));
        gameStates.add(new WinState(this));
    }

    //metod to change the game state and initialise it
    public void setState(int state) {
        currentState = state;
        gameStates.get(state).init();
    }

    //method to update the current GameState
    public void update() {
        gameStates.get(currentState).update();
    }

    //methd to draw the current GameState
    public void draw(Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
        //invokes the current GameState's keyPressed method
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        //invokes the current GameState's keyReleased method
        gameStates.get(currentState).keyReleased(k);
    }
}

