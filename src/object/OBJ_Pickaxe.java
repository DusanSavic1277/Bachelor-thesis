package object;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class OBJ_Pickaxe extends Entity{
    
    public OBJ_Pickaxe(GamePanel gp) {
        super(gp);
        
        type = EntityType.PICAXE;
        name = "Pickaxe";
        description = "[" + name + "]\nBreak rocks, \nforge your path.";
        attackValue = 0;
        attackArea.width = 30;
        attackArea.height = 30;
        
        down1 = setup("/objectsImg/pickaxe", gp.tileSize, gp.tileSize);
    }
    
}
