package object;

import entity.Entity;
import java.awt.Rectangle;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;
import rs.ac.singidunum.GameState;

public class OBJ_Chest extends Entity {
    
    GamePanel gp;
    Entity loot;
    boolean opened = false;
    
    public OBJ_Chest(GamePanel gp, Entity loot){
        super(gp);
        this.gp = gp;
        this.loot = loot;
        
        type = EntityType.OBSTACLE;
        name = "Chest";
        image = setup("/objectsImg/chest", gp.tileSize, gp.tileSize);
        image2 = setup("/objectsImg/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        
        collision = true;
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
    }
    
    @Override
    public void interact() {

        gp.gameState = GameState.DIALOGUE;

        if (gp.keyH.enter == true) {

            if (opened == false) {
                gp.playSE(2);

                StringBuilder string = new StringBuilder();
                string.append("You opend the chest and found the " + loot.name + "!");

                if (gp.player.canObtainItem(loot) == false) {
                    string.append("\n... But inventory is full!");
                } else {
                    //gp.player.inventory.add(loot);
                    down1 = image2;
                    opened = true;
                }
                dialogues[0][0] = string.toString();
                startDialogue(this, 0);

            } else {
                dialogues[1][0] = "It's already open.";
                startDialogue(this, 1);
            }
        }
        gp.keyH.enter = false;

    }
    
}