package object;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class OBJ_Sword extends Entity{
    
    public OBJ_Sword(GamePanel gp) {
        super(gp);
        
        type = EntityType.SWORAD;
        name = "Silver sword";        
        attackValue = 2;
        description = "[" + name + "]\nSilver sword, \nfor slaying monsters.";
        attackArea.width = 36;
        attackArea.height = 36;
        
        down1 = setup("/objectsImg/sword_normal", gp.tileSize, gp.tileSize);
        
    }
    
}
