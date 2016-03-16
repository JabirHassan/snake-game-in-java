
package snakegame;


import javax.swing.JFrame;

public class Main extends JFrame {

    public static void main(String[] args) {
        //Creates the main frame and adds the game panel to it
        JFrame frame = new JFrame("Snake Game");
        GamePanel panel = new GamePanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}

