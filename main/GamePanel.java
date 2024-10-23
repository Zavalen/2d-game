package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int origTileSize = 16; // 16 x 16 tile
    final int scale = 3;

    public final int tileSize = origTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 16; // Edit these two to change the width and height of the window
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
//    public final int maxWorldCol = 50;
//    public final int maxWorldRow = 50;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int fps = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // Thread used for repeating things such as updating frames. Will automatically call the 'run' method
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    Player player = new Player(this, keyH);
    public SuperObject obj [] = new SuperObject[10]; // display 10 items at a time


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // helps rendering and performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame () {
        aSetter.setObject();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() { // loops the game. This is core of the game

        double drawInterval = 1000000000/fps;
        double nextDrawTime = System.nanoTime() + drawInterval; // adds the interval of fps to system time

        while(gameThread != null) {

            // UPDATES INFORMATION SUCH AS CHARACTER POS
            update();

            // DRAWS THE SCREEN WITH UPDATED INFO
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // converts from nanosecond to millisecond

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // pauses game loop until pause is done
                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // changes Graphics g to a 2d brush
        //TILE
        tileM.draw(g2); //Order in which it's typed is important. Determines the layers of GUI

        //OBJECTS
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }

        //PLAYER
        player.draw(g2);

        g2.dispose();

    }
}
