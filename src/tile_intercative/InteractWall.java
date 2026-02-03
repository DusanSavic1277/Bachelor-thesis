package tile_intercative;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class InteractWall extends InteractiveTile {

    GamePanel gp;

    public InteractWall(GamePanel gp) {
        super(gp);

        this.gp = gp;

        down1 = setup("/tiles_InteractiveImg/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 2;
    }

    @Override
    public boolean isCorrectItem(Entity entity) {

        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == EntityType.PICAXE) {
            isCorrectItem = true;
        }

        return isCorrectItem;
    }

}
