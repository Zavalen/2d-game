// package is like an internal file directory
package main;

import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // lets user close the window after clicking the x button
        window.setResizable(false); // doesnt allow you to change the size
        window.setTitle("Final Project Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // adds the game panel to this window

        window.pack(); // forces this window to be sized to fit the preferred size of the subcomponent(GamePanel)

        window.setLocationRelativeTo(null); // sets the window to the center of the screen
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

}
