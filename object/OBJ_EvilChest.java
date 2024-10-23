package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_EvilChest extends SuperObject{

        public OBJ_EvilChest(){

            name = "evil chest";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/evil_chest.png"));
            }
            catch(IOException e) {
                e.printStackTrace();
            }

        }

}
