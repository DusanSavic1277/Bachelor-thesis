package object;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gp;
    
    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = EntityType.CONSUMABLE;
        name = "Key";
        description = "[" + name + "]\nIt opens a door.";
        down1 = setup("/objectsImg/key", gp.tileSize, gp.tileSize);
        stackable = true;
        
        setDialogue();

    }
    
    public void setDialogue(){
        dialogues[0][0] = "You opened the door.";
        dialogues[1][0] = "What are you doing?";
    }
    
    @Override
    public boolean use(Entity entity){
        
        int objIndex = getDetected(entity, gp.obj, "Door");
        
        if(objIndex != 999){
            startDialogue(this, 0);
            gp.playSE(2);
            gp.obj[gp.currentMap][objIndex] = null;
            
            return true;
        }
        else{
            startDialogue(this, 1);
        }
        return false;
    }
    
}