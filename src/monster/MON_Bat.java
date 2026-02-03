package monster;

import entity.Entity;
import java.awt.Rectangle;
import java.util.Random;
import object.OBJ_Heart;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class MON_Bat extends Entity {

    GamePanel gp;

    public MON_Bat(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = EntityType.MONSTER;
        name = "Bat";
        speed = 7;
        maxLife = 4;
        life = maxLife;
        attack = 3;
        defense = 0;
        exp = 2;

        up1 = setup("/monsterImg/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monsterImg/bat_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monsterImg/bat_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monsterImg/bat_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monsterImg/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monsterImg/bat_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monsterImg/bat_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monsterImg/bat_down_2", gp.tileSize, gp.tileSize);

        collision = true;

        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 15;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 42;
        solidArea.height = 25;
    }

    @Override
    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 30) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

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