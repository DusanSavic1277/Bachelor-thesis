package entity;

import rs.ac.singidunum.GamePanel;

public class PlayerDummy extends Entity {
    
    public static final String npcName = "Dummy";

    public PlayerDummy(GamePanel gp) {
        super(gp);

        name = npcName;
        
        getDummyImage();

    }

    public void getDummyImage() {

        up1 = setup("/playerImg/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/playerImg/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/playerImg/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/playerImg/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/playerImg/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/playerImg/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/playerImg/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/playerImg/boy_right_2", gp.tileSize, gp.tileSize);

    }
    
}
