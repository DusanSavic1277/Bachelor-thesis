package object;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class OBJ_PotionRed extends Entity{
    
    GamePanel gp;
    
    public OBJ_PotionRed(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = EntityType.CONSUMABLE;
        name = "Swallow potion";
        description = "[" + name + "]\nIt restore your HP by 4.";
        down1 = setup("/objectsImg/potion_red", gp.tileSize, gp.tileSize);
        stackable = true;
        
        setDialogue();
        
    }
    
    public void setDialogue(){
        dialogues[0][0] = "You drink the " + name + "!\n" + "Your HP is restored by 4!";
    }
    
    @Override
    public boolean use(Entity entity){
        
       startDialogue(this, 0);
        entity.life += 4;
        gp.playSE(10);
        
        return true;
        
    }
    
}
