package monster;

import entity.Entity;
import java.awt.Rectangle;
import java.util.Random;
import object.OBJ_Heart;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class MON_Orc extends Entity {

    GamePanel gp;

    public MON_Orc(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.MONSTER;
        name = "Orc";
        speed = 1;
        maxLife = 12;
        life = maxLife;
        attack = 3;
        defense = 3;
        exp = 15;

        collision = true;

        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 48;
        attackArea.height = 48;

        getImage();
        getAttackImage();

    }

    public void getImage() {

        up1 = setup("/monsterImg/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monsterImg/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monsterImg/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monsterImg/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monsterImg/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monsterImg/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monsterImg/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monsterImg/orc_right_2", gp.tileSize, gp.tileSize);

    }

    public void getAttackImage() {

        attackUp1 = setup("/monsterImg/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/monsterImg/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/monsterImg/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/monsterImg/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/monsterImg/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/monsterImg/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/monsterImg/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/monsterImg/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);

    }
    
    @Override
    public void update() {

        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if (onPath == false && tileDistance < 5) {
            onPath = true;
        }
        if (onPath == true && tileDistance > 10) {
            onPath = false;
        }

        if (attacking == false) {
            checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize);
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
