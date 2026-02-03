package object;

import entity.Entity;
import java.awt.Rectangle;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class OBJ_Door_Iron extends Entity{
    
    //public static final String objName = "Iron door";
    
    GamePanel gp;
    
    public OBJ_Door_Iron(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = EntityType.OBSTACLE;
        name = "Iron door";
        down1 = setup("/objectsImg/door_iron", gp.tileSize, gp.tileSize);
        
        collision = true;
        
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 48;
        solidArea.height = 32;
        
        setDialogue();
    }
    
    public void setDialogue(){
        dialogues[0][0] = "The only way out is victory or retry.";
    }
    
    @Override
    public void interact() {

      if (gp.keyH.enter == true) {
            startDialogue(this, 0);
        }
        gp.keyH.enter = false;
    }
    
}


