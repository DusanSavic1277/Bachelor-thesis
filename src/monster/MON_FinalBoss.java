package monster;

import entity.Entity;
import java.awt.Rectangle;
import java.util.Random;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;

public class MON_FinalBoss extends Entity{
    
    GamePanel gp;
    
    public static final String bossName = "Skeleton Lord";
    
    public MON_FinalBoss(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = EntityType.MONSTER;
        name = bossName;
        sleep = true;
        boss = true;
        speed = 1;
        maxLife = 250;
        life = maxLife;
        attack = 2;
        defense = 3;
        exp = 50;

        collision = true;
        canRegenerate = true;

        solidArea = new Rectangle();
        int size = gp.tileSize * 5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 135;
        attackArea.height = 135;

        getImage();
        getAttackImage();
        setDialogue();

    }

    public void getImage() {

        int i = 5;

        if (inRage == false) {
            up1 = setup("/monsterImg/skeletonlord_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monsterImg/skeletonlord_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/monsterImg/skeletonlord_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monsterImg/skeletonlord_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/monsterImg/skeletonlord_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monsterImg/skeletonlord_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/monsterImg/skeletonlord_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monsterImg/skeletonlord_right_2", gp.tileSize * i, gp.tileSize * i);
        }
        
        if (inRage == true) {
            up1 = setup("/monsterImg/skeletonlord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monsterImg/skeletonlord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/monsterImg/skeletonlord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monsterImg/skeletonlord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/monsterImg/skeletonlord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monsterImg/skeletonlord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/monsterImg/skeletonlord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monsterImg/skeletonlord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);
        }

    }

    public void getAttackImage() {
        
        int i = 5;

        if (inRage == false) {
            attackUp1 = setup("/monsterImg/skeletonlord_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monsterImg/skeletonlord_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/monsterImg/skeletonlord_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monsterImg/skeletonlord_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/monsterImg/skeletonlord_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monsterImg/skeletonlord_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/monsterImg/skeletonlord_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monsterImg/skeletonlord_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
        }
        
        if (inRage == true) {
            attackUp1 = setup("/monsterImg/skeletonlord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monsterImg/skeletonlord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/monsterImg/skeletonlord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monsterImg/skeletonlord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/monsterImg/skeletonlord_phase2_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monsterImg/skeletonlord_phase2_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/monsterImg/skeletonlord_phase2_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monsterImg/skeletonlord_phase2_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
        }
        

    }
    
    public void setDialogue(){
        dialogues[0][0] = "You've reached the end of your story.\nThis is where it all ends!";
    }

    @Override
    public void setAction() {

        if(inRage == false && life < maxLife / 2){
            inRage = true;
            getImage();
            getAttackImage();
            speed += 1;
            attack += 5;
        }

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if (tileDistance < 10) {
            chasingPlayer(60);    
        } 
        else {
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

        if (attacking == false) {
            checkAttackOrNot(60, gp.tileSize * 5, gp.tileSize * 5);
        }

    }

    @Override
    public void attackReaction() {

        actionLockCounter = 0;

    }

    @Override
    public void checkDrop() {
        
        gp.bossBattleOn = false;
        
        gp.stopMusic();
        gp.playMusic(18);
        gp.ui.showMessage("QUEST COMPLETED");
        gp.playSE(14);
        
        for (int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i] instanceof OBJ_Door_Iron){
                gp.playSE(16);
                gp.obj[gp.currentMap][i] = null;
            }
            
        }

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
