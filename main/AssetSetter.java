package main;

import object.OBJ_Door;
import object.OBJ_EvilChest;
import object.OBJ_HoverBoots;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].x = gp.tileSize * 4;
        gp.obj[0].y = gp.tileSize * 4;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].x = gp.maxScreenRow*gp.tileSize/2 + 2*gp.tileSize;
        gp.obj[1].y = 0;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].x = gp.maxScreenRow*gp.tileSize/2 + gp.tileSize;
        gp.obj[4].y =0;

        gp.obj[2] = new OBJ_EvilChest();
        gp.obj[2].x = gp.tileSize * 8;
        gp.obj[2].y = gp.tileSize * 4;

        gp.obj[3] = new OBJ_HoverBoots();
        gp.obj[3].x = gp.tileSize * 8;
        gp.obj[3].y = gp.tileSize * 8;
    }
}
