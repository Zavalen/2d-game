package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int spd;

    public BufferedImage upIdle, up1, up2, downIdle, down1, down2, leftIdle, left1, rightIdle, right1; // Used to store image files
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int aniSpeed = 15;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;
}
