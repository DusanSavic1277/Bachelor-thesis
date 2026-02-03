package object;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class OBJ_PotionGreen extends Entity {

    GamePanel gp;

    public OBJ_PotionGreen(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.CONSUMABLE;
        name = "Thunderbolt potion";
        description = "[" + name + "]\nIncreases the attack and \nthus damage dealt using a \nsword.";
        down1 = setup("/objectsImg/potion_green", gp.tileSize, gp.tileSize);
        stackable = true;
        
        setDialogue();
    }
    
    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\nYour strength is increased by 3 for 60 seconds!";
        dialogues[1][0] = "You're already under a potion effect!";
    }

    @Override
    public boolean use(Entity entity) {

        if (!entity.usePotion) {
            
            startDialogue(this, 0);

            entity.usePotion = true;
            entity.potionCounter = 0;
            entity.strengthBonus = 3;
            
            entity.attack = entity.getAttack();

            gp.playSE(10);
            return true;
        } else {
            startDialogue(this, 1);
            return false;
        }
    }

}

