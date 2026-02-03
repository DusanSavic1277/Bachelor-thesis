package object;

import entity.Entity;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;
import rs.ac.singidunum.GameState;

public class OBJ_Diamond extends Entity {
    
    GamePanel gp;
    
    public OBJ_Diamond(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = EntityType.PICK_UP;
        name = "Blue heart";
        down1 = setup("/objectsImg/blueheart", gp.tileSize, gp.tileSize);
        
        setDialogues();
        
    }
    
    public void setDialogues(){
        
        dialogues[0][0] = "You picked up the blue gem.";
        dialogues[0][1] = "You have found the blue heart \nlegendary treasure!";
        
    }
    
    @Override
    public boolean use(Entity entity){
        
        gp.gameState = GameState.CUTSCENE;
        gp.cutscene.sceneNum = gp.cutscene.ENDING;
                
        return true;
    }
    
}
