package object;

import javax.imageio.ImageIO;
import java.io.IOException;


public class OBJ_HoverBoots extends SuperObject {

    public OBJ_HoverBoots () {

        name = "hover boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/hover_boots.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
