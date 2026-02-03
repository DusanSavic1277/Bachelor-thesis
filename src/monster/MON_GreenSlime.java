package monster;

import entity.Entity;
import java.awt.Rectangle;
import java.util.Random;
import object.OBJ_Heart;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.MONSTER;
        name = "Green slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;

        up1 = setup("/monsterImg/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monsterImg/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monsterImg/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monsterImg/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monsterImg/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monsterImg/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monsterImg/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monsterImg/greenslime_down_2", gp.tileSize, gp.tileSize);

        collision = true;

        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 42;
        solidArea.height = 30;

    }    
    
    @Override
    public void update(){
        
        super.update();
        
        
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;
        
        if(onPath == false && tileDistance < 5){
                onPath = true;  
        }
        if(onPath == true && tileDistance > 10){
            onPath = false;
        }
        
    }
    
    @Override
    public void setAction() {

        if (onPath == true) {

            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);

        } else {
            actionLockCounter++;

            if (actionLockCounter == 120) {

                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                } else if (i <= 50) {
                    direction = "down";
                } else if (i <= 75) {
                    direction = "left";
                } else if (i <= 100) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }

    }

    @Override
    public void attackReaction() {

        actionLockCounter = 0;
        onPath = true;

    }

    @Override
    public void checkDrop() {

        int i = new Random().nextInt(100) + 1;

        if (i < 70) {
        }
        if (i >= 70 && i < 80) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 80 && i < 90) {
            dropItem(new OBJ_PotionRed(gp));
        }
        if (i >= 90 && i < 100) {
            dropItem(new OBJ_PotionGreen(gp));
        }
    }

}
