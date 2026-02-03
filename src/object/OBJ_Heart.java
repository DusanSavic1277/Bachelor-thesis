package object;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class OBJ_Heart extends Entity{
    
    GamePanel gp;
    
    public OBJ_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = EntityType.PICK_UP;
        name = "Heart";
        
        down1 = setup("/objectsImg/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/objectsImg/heart_blank", gp.tileSize, gp.tileSize);
        image2 = setup("/objectsImg/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/objectsImg/heart_full", gp.tileSize, gp.tileSize);
        
    }
    
    @Override
    public boolean use(Entity entity){
        
        gp.playSE(1);
        entity.life += 2;
        
        return true;
        
    }
}
