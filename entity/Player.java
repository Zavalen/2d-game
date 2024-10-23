package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    int hasKey = 0;
    Boolean hasHoverBoots = false;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

        //SETS PLAYER HIT BOX
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = (gp.tileSize - 2 *solidArea.x) ;
        solidArea.height = (gp.tileSize - solidArea.y) ;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        spd = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            if (hasHoverBoots == true) {
                upIdle = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_forward_idle.png"));
                up1 = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_forward1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_forward2.png"));
                downIdle = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_back_idle.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_back1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_back2.png"));
                leftIdle = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_left_idle.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_left1.png"));
                rightIdle = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_right_idle.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player/hover_boots_right1.png"));
            }
            else {
                upIdle = ImageIO.read(getClass().getResourceAsStream("/player/Forwardidle.png"));
                up1 = ImageIO.read(getClass().getResourceAsStream("/player/forwardOne.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/player/forwardTwo.png"));
                downIdle = ImageIO.read(getClass().getResourceAsStream("/player/backIdle.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/player/backOne.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/player/backTwo.png"));
                leftIdle = ImageIO.read(getClass().getResourceAsStream("/player/leftIdle.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player/leftOne.png"));
                rightIdle = ImageIO.read(getClass().getResourceAsStream("/player/rightIdle.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player/rightOne.png"));
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        // PLAYER MOVEMENT
        if(keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.upPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJ COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION FALSE, PLAYER CANT MOVE
            if(collisionOn == false) {
                if (direction == "up") {
                    y -= spd;
                }
                if (direction == "down") {
                    y += spd;
                }
                if (direction == "left") {
                    x -= spd;
                }
                if (direction == "right") {
                    x += spd;
                }
            }
            // SPRITE ANIMATION SWITCH TIME

            spriteCounter++;
            if (spriteCounter > aniSpeed) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 3;
                }
                else if(spriteNum == 3){
                    spriteNum = 4;
                }
                else if(spriteNum == 4){
                    spriteNum = 1;
                }

                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject (int i) {
        if (i != 999) {

            String objectName = gp.obj[i].name;
            if (objectName == "key") {
                hasKey++;
                gp.obj[i] = null;
            }
            else if (objectName == "door" && hasKey > 0) {
                hasKey--;
                gp.obj[i] = null;
            }
            else if (objectName == "hover boots") {
                hasHoverBoots = true;
                getPlayerImage();
                gp.obj[i] = null;
            }
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        if (direction == "up") {
            if (spriteNum == 1) {
                image = up1;
            }
            if (spriteNum == 2 || spriteNum == 4) {
                image = upIdle;
            }
            if (spriteNum == 3) {
                image = up2;
            }

        }
       if (direction == "down") {
           if (spriteNum == 1) {
               image = down1;
           }
           if (spriteNum == 2 || spriteNum == 4) {
               image = downIdle;
           }
           if (spriteNum == 3) {
               image = down2;
           }
        }
        if (direction == "left") {
            if (spriteNum == 1 || spriteNum == 3) {
                image = left1;
            }
            if (spriteNum == 2 || spriteNum == 4) {
                image = leftIdle;
            }
        }
        if (direction == "right") {
            if (spriteNum == 1 || spriteNum == 3) {
                image = right1;
            }
            if (spriteNum == 2 || spriteNum == 4) {
                image = rightIdle;
            }
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
