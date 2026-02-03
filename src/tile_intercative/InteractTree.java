package tile_intercative;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class InteractTree extends InteractiveTile {

    GamePanel gp;

    public InteractTree(GamePanel gp) {
        super(gp);
        this.gp = gp;

        down1 = setup("/tiles_InteractiveImg/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 2;
    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        
        if(entity.currentWeapon.type == EntityType.AXE){
            isCorrectItem = true;
        }
        
        return isCorrectItem;
    }
    

}
