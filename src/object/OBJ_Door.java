package object;

import entity.Entity;
import java.awt.Rectangle;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class OBJ_Door extends Entity {
    
    GamePanel gp;
    
    public OBJ_Door(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = EntityType.OBSTACLE;
        name = "Door";
        down1 = setup("/objectsImg/door", gp.tileSize, gp.tileSize);
        
        collision = true;
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDialogue();
    }
    
    public void setDialogue(){
        
        dialogues[0][0] = "Key required.";
        
    }
    
    @Override
    public void interact() {

      if (gp.keyH.enter == true) {
          startDialogue(this, 0);
        }
        gp.keyH.enter = false;
    }
    
}
