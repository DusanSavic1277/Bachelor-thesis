package object;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class OBJ_Axe extends Entity{
    
    public OBJ_Axe(GamePanel gp) {
        super(gp);
        
        type = EntityType.AXE;
        name = "Axe";
        description = "[" + name + "]\nAn old axe,\ngood for cuting trees.";
        attackValue = 0;
        attackArea.width = 30;
        attackArea.height = 30;
        
        down1 = setup("/objectsImg/axe", gp.tileSize, gp.tileSize);
        
    }
    
}
