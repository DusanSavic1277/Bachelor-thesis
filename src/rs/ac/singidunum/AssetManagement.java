package rs.ac.singidunum;

import entity.NPC_OldMan;
import monster.MON_Bat;
import monster.MON_FinalBoss;
import monster.MON_GreenSlime;
import monster.MON_Orc;
import monster.MON_RedSlime;
import object.OBJ_Axe;
import object.OBJ_Chest;
import object.OBJ_Diamond;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_Key;
import object.OBJ_Pickaxe;
import object.OBJ_PotionGreen;
import object.OBJ_PotionRed;
import tile_intercative.InteractTree;
import tile_intercative.InteractWall;

public class AssetManagement {

    GamePanel gp;
    NPC_OldMan npc;

    public AssetManagement(GamePanel gp) {
        this.gp = gp;
        
        npc = new NPC_OldMan(gp);

    }

    public void setObject() {

        if (gp.objectPickedUp[gp.currentMap][0] == false) {

            int mapNum = 0;
            gp.obj[mapNum][0] = new OBJ_Axe(gp);
            gp.obj[mapNum][0].worldX = gp.tileSize * 33;
            gp.obj[mapNum][0].worldY = gp.tileSize * 7;

            gp.obj[mapNum][1] = new OBJ_Door(gp);
            gp.obj[mapNum][1].worldX = gp.tileSize * 14;
            gp.obj[mapNum][1].worldY = gp.tileSize * 28;
            
            gp.obj[mapNum][2] = new OBJ_Door(gp);
            gp.obj[mapNum][2].worldX = gp.tileSize * 8;
            gp.obj[mapNum][2].worldY = gp.tileSize * 28;

            gp.obj[mapNum][3] = new OBJ_Door(gp);
            gp.obj[mapNum][3].worldX = gp.tileSize * 10;
            gp.obj[mapNum][3].worldY = gp.tileSize * 12;

            gp.obj[mapNum][4] = new OBJ_Chest(gp, new OBJ_PotionRed(gp));
            gp.obj[mapNum][4].worldX = gp.tileSize * 18;
            gp.obj[mapNum][4].worldY = gp.tileSize * 20;

            gp.obj[mapNum][5] = new OBJ_Chest(gp, new OBJ_PotionGreen(gp));
            gp.obj[mapNum][5].worldX = gp.tileSize * 17;
            gp.obj[mapNum][5].worldY = gp.tileSize * 20;

            gp.obj[mapNum][6] = new OBJ_Key(gp);
            gp.obj[mapNum][6].worldX = gp.tileSize * 13;
            gp.obj[mapNum][6].worldY = gp.tileSize * 7;

            gp.obj[mapNum][7] = new OBJ_Chest(gp, new OBJ_PotionRed(gp));
            gp.obj[mapNum][7].worldX = gp.tileSize * 30;
            gp.obj[mapNum][7].worldY = gp.tileSize * 28;
           
            
            mapNum = 2;
            gp.obj[mapNum][8] = new OBJ_Key(gp);
            gp.obj[mapNum][8].worldX = gp.tileSize * 8;
            gp.obj[mapNum][8].worldY = gp.tileSize * 7;
            
            gp.obj[mapNum][9] = new OBJ_Chest(gp, new OBJ_Pickaxe(gp));
            gp.obj[mapNum][9].worldX = gp.tileSize * 40;
            gp.obj[mapNum][9].worldY = gp.tileSize * 41;
            
            gp.obj[mapNum][10] = new OBJ_Chest(gp, new OBJ_PotionRed(gp));
            gp.obj[mapNum][10].worldX = gp.tileSize * 8;
            gp.obj[mapNum][10].worldY = gp.tileSize * 17;
            
            gp.obj[mapNum][11] = new OBJ_Chest(gp, new OBJ_PotionRed(gp));
            gp.obj[mapNum][11].worldX = gp.tileSize * 13;
            gp.obj[mapNum][11].worldY = gp.tileSize * 16;
            
            gp.obj[mapNum][12] = new OBJ_Chest(gp, new OBJ_PotionGreen(gp));
            gp.obj[mapNum][12].worldX = gp.tileSize * 26;
            gp.obj[mapNum][12].worldY = gp.tileSize * 34;
            
            gp.obj[mapNum][13] = new OBJ_Chest(gp, new OBJ_PotionRed(gp));
            gp.obj[mapNum][13].worldX = gp.tileSize * 27;
            gp.obj[mapNum][13].worldY = gp.tileSize * 15;
            
            gp.obj[mapNum][14] = new OBJ_Chest(gp, new OBJ_PotionGreen(gp));
            gp.obj[mapNum][14].worldX = gp.tileSize * 20;
            gp.obj[mapNum][14].worldY = gp.tileSize * 22;
            
            mapNum = 3;
            gp.obj[mapNum][15] = new OBJ_Door_Iron(gp);
            gp.obj[mapNum][15].worldX = gp.tileSize * 25;
            gp.obj[mapNum][15].worldY = gp.tileSize * 15;
            
            gp.obj[mapNum][16] = new OBJ_Diamond(gp);
            gp.obj[mapNum][16].worldX = gp.tileSize * 25;
            gp.obj[mapNum][16].worldY = gp.tileSize * 8;

        }
    }

    public void setNpc() {

        int mapNum = 0;
        
        if(npc.questOn == false){
        gp.npc[mapNum][0] = new NPC_OldMan(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 17;
        gp.npc[mapNum][0].worldY = gp.tileSize * 21;
        }
        
    }

    public void setMonster() {

        int mapNum = 0;

        gp.monster[mapNum][0] = new MON_GreenSlime(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize * 20;
        gp.monster[mapNum][0].worldY = gp.tileSize * 41;

        gp.monster[mapNum][1] = new MON_GreenSlime(gp);
        gp.monster[mapNum][1].worldX = gp.tileSize * 22;
        gp.monster[mapNum][1].worldY = gp.tileSize * 39;

        gp.monster[mapNum][2] = new MON_GreenSlime(gp);
        gp.monster[mapNum][2].worldX = gp.tileSize * 24;
        gp.monster[mapNum][2].worldY = gp.tileSize * 36;

        gp.monster[mapNum][3] = new MON_GreenSlime(gp);
        gp.monster[mapNum][3].worldX = gp.tileSize * 25;
        gp.monster[mapNum][3].worldY = gp.tileSize * 39;

        gp.monster[mapNum][4] = new MON_GreenSlime(gp);
        gp.monster[mapNum][4].worldX = gp.tileSize * 27;
        gp.monster[mapNum][4].worldY = gp.tileSize * 37;

        gp.monster[mapNum][5] = new MON_RedSlime(gp);
        gp.monster[mapNum][5].worldX = gp.tileSize * 38;
        gp.monster[mapNum][5].worldY = gp.tileSize * 8;

        gp.monster[mapNum][6] = new MON_RedSlime(gp);
        gp.monster[mapNum][6].worldX = gp.tileSize * 36;
        gp.monster[mapNum][6].worldY = gp.tileSize * 9;

        gp.monster[mapNum][7] = new MON_RedSlime(gp);
        gp.monster[mapNum][7].worldX = gp.tileSize * 34;
        gp.monster[mapNum][7].worldY = gp.tileSize * 11;

        gp.monster[mapNum][8] = new MON_RedSlime(gp);
        gp.monster[mapNum][8].worldX = gp.tileSize * 35;
        gp.monster[mapNum][8].worldY = gp.tileSize * 7;

        gp.monster[mapNum][9] = new MON_RedSlime(gp);
        gp.monster[mapNum][9].worldX = gp.tileSize * 37;
        gp.monster[mapNum][9].worldY = gp.tileSize * 10;

        gp.monster[mapNum][10] = new MON_Orc(gp);
        gp.monster[mapNum][10].worldX = gp.tileSize * 12;
        gp.monster[mapNum][10].worldY = gp.tileSize * 33;

        gp.monster[mapNum][11] = new MON_Orc(gp);
        gp.monster[mapNum][11].worldX = gp.tileSize * 10;
        gp.monster[mapNum][11].worldY = gp.tileSize * 13;
        
        mapNum = 2;
        gp.monster[mapNum][11] = new MON_Bat(gp);
        gp.monster[mapNum][11].worldX = gp.tileSize * 10;
        gp.monster[mapNum][11].worldY = gp.tileSize * 18;
        
        gp.monster[mapNum][12] = new MON_Bat(gp);
        gp.monster[mapNum][12].worldX = gp.tileSize * 22;
        gp.monster[mapNum][12].worldY = gp.tileSize * 20;
        
        gp.monster[mapNum][13] = new MON_Orc(gp);
        gp.monster[mapNum][13].worldX = gp.tileSize * 27;
        gp.monster[mapNum][13].worldY = gp.tileSize * 18;
        
        gp.monster[mapNum][14] = new MON_Bat(gp);
        gp.monster[mapNum][14].worldX = gp.tileSize * 28;
        gp.monster[mapNum][14].worldY = gp.tileSize * 10;
        
        gp.monster[mapNum][15] = new MON_Bat(gp);
        gp.monster[mapNum][15].worldX = gp.tileSize * 37;
        gp.monster[mapNum][15].worldY = gp.tileSize * 24;
        
        gp.monster[mapNum][16] = new MON_Bat(gp);
        gp.monster[mapNum][16].worldX = gp.tileSize * 34;
        gp.monster[mapNum][16].worldY = gp.tileSize * 24;
        
        gp.monster[mapNum][17] = new MON_Bat(gp);
        gp.monster[mapNum][17].worldX = gp.tileSize * 33;
        gp.monster[mapNum][17].worldY = gp.tileSize * 25;
        
        gp.monster[mapNum][18] = new MON_Orc(gp);
        gp.monster[mapNum][18].worldX = gp.tileSize * 16;
        gp.monster[mapNum][18].worldY = gp.tileSize * 9;
        
        gp.monster[mapNum][19] = new MON_Orc(gp);
        gp.monster[mapNum][19].worldX = gp.tileSize * 17;
        gp.monster[mapNum][19].worldY = gp.tileSize * 9;
        
        gp.monster[mapNum][20] = new MON_Orc(gp);
        gp.monster[mapNum][20].worldX = gp.tileSize * 19;
        gp.monster[mapNum][20].worldY = gp.tileSize * 9;
        
        mapNum = 3;
        if (gp.event.finalBossOn == false) {
            gp.monster[mapNum][21] = new MON_FinalBoss(gp);
            gp.monster[mapNum][21].worldX = gp.tileSize * 23;
            gp.monster[mapNum][21].worldY = gp.tileSize * 16;
        }


    }

    public void setInteractiveTile() {

        if (gp.objectPickedUp[gp.currentMap][0] == false) {

            int mapNum = 0;
            gp.iTile[mapNum][0] = new InteractTree(gp);
            gp.iTile[mapNum][0].worldX = gp.tileSize * 27;
            gp.iTile[mapNum][0].worldY = gp.tileSize * 10;

            gp.iTile[mapNum][1] = new InteractTree(gp);
            gp.iTile[mapNum][1].worldX = gp.tileSize * 28;
            gp.iTile[mapNum][1].worldY = gp.tileSize * 10;

            gp.iTile[mapNum][2] = new InteractTree(gp);
            gp.iTile[mapNum][2].worldX = gp.tileSize * 29;
            gp.iTile[mapNum][2].worldY = gp.tileSize * 10;

            gp.iTile[mapNum][3] = new InteractTree(gp);
            gp.iTile[mapNum][3].worldX = gp.tileSize * 25;
            gp.iTile[mapNum][3].worldY = gp.tileSize * 27;

            gp.iTile[mapNum][4] = new InteractTree(gp);
            gp.iTile[mapNum][4].worldX = gp.tileSize * 26;
            gp.iTile[mapNum][4].worldY = gp.tileSize * 27;

            gp.iTile[mapNum][5] = new InteractTree(gp);
            gp.iTile[mapNum][5].worldX = gp.tileSize * 27;
            gp.iTile[mapNum][5].worldY = gp.tileSize * 28;

            gp.iTile[mapNum][6] = new InteractTree(gp);
            gp.iTile[mapNum][6].worldX = gp.tileSize * 27;
            gp.iTile[mapNum][6].worldY = gp.tileSize * 29;

            gp.iTile[mapNum][7] = new InteractTree(gp);
            gp.iTile[mapNum][7].worldX = gp.tileSize * 27;
            gp.iTile[mapNum][7].worldY = gp.tileSize * 30;

            gp.iTile[mapNum][8] = new InteractTree(gp);
            gp.iTile[mapNum][8].worldX = gp.tileSize * 28;
            gp.iTile[mapNum][8].worldY = gp.tileSize * 30;

            gp.iTile[mapNum][9] = new InteractTree(gp);
            gp.iTile[mapNum][9].worldX = gp.tileSize * 29;
            gp.iTile[mapNum][9].worldY = gp.tileSize * 30;

            gp.iTile[mapNum][10] = new InteractTree(gp);
            gp.iTile[mapNum][10].worldX = gp.tileSize * 30;
            gp.iTile[mapNum][10].worldY = gp.tileSize * 30;

            gp.iTile[mapNum][11] = new InteractTree(gp);
            gp.iTile[mapNum][11].worldX = gp.tileSize * 19;
            gp.iTile[mapNum][11].worldY = gp.tileSize * 12;

            gp.iTile[mapNum][12] = new InteractTree(gp);
            gp.iTile[mapNum][12].worldX = gp.tileSize * 18;
            gp.iTile[mapNum][12].worldY = gp.tileSize * 12;

            gp.iTile[mapNum][13] = new InteractTree(gp);
            gp.iTile[mapNum][13].worldX = gp.tileSize * 17;
            gp.iTile[mapNum][13].worldY = gp.tileSize * 12;

            gp.iTile[mapNum][14] = new InteractTree(gp);
            gp.iTile[mapNum][14].worldX = gp.tileSize * 17;
            gp.iTile[mapNum][14].worldY = gp.tileSize * 11;

            gp.iTile[mapNum][15] = new InteractTree(gp);
            gp.iTile[mapNum][15].worldX = gp.tileSize * 17;
            gp.iTile[mapNum][15].worldY = gp.tileSize * 10;

            gp.iTile[mapNum][16] = new InteractTree(gp);
            gp.iTile[mapNum][16].worldX = gp.tileSize * 16;
            gp.iTile[mapNum][16].worldY = gp.tileSize * 10;

            gp.iTile[mapNum][17] = new InteractTree(gp);
            gp.iTile[mapNum][17].worldX = gp.tileSize * 15;
            gp.iTile[mapNum][17].worldY = gp.tileSize * 10;

            gp.iTile[mapNum][18] = new InteractTree(gp);
            gp.iTile[mapNum][18].worldX = gp.tileSize * 15;
            gp.iTile[mapNum][18].worldY = gp.tileSize * 9;

            gp.iTile[mapNum][19] = new InteractTree(gp);
            gp.iTile[mapNum][19].worldX = gp.tileSize * 14;
            gp.iTile[mapNum][19].worldY = gp.tileSize * 9;

            gp.iTile[mapNum][20] = new InteractTree(gp);
            gp.iTile[mapNum][20].worldX = gp.tileSize * 13;
            gp.iTile[mapNum][20].worldY = gp.tileSize * 9;

            gp.iTile[mapNum][21] = new InteractTree(gp);
            gp.iTile[mapNum][21].worldX = gp.tileSize * 13;
            gp.iTile[mapNum][21].worldY = gp.tileSize * 8;
            
            gp.iTile[mapNum][22] = new InteractTree(gp);
            gp.iTile[mapNum][22].worldX = gp.tileSize * 29;
            gp.iTile[mapNum][22].worldY = gp.tileSize * 11;
            
            gp.iTile[mapNum][23] = new InteractTree(gp);
            gp.iTile[mapNum][23].worldX = gp.tileSize * 29;
            gp.iTile[mapNum][23].worldY = gp.tileSize * 12;
            
            gp.iTile[mapNum][24] = new InteractTree(gp);
            gp.iTile[mapNum][24].worldX = gp.tileSize * 28;
            gp.iTile[mapNum][24].worldY = gp.tileSize * 12;
            
            gp.iTile[mapNum][25] = new InteractTree(gp);
            gp.iTile[mapNum][25].worldX = gp.tileSize * 28;
            gp.iTile[mapNum][25].worldY = gp.tileSize * 13;
            
            gp.iTile[mapNum][26] = new InteractTree(gp);
            gp.iTile[mapNum][26].worldX = gp.tileSize * 28;
            gp.iTile[mapNum][26].worldY = gp.tileSize * 14;
            
            gp.iTile[mapNum][27] = new InteractTree(gp);
            gp.iTile[mapNum][27].worldX = gp.tileSize * 28;
            gp.iTile[mapNum][27].worldY = gp.tileSize * 15;
            
            gp.iTile[mapNum][28] = new InteractTree(gp);
            gp.iTile[mapNum][28].worldX = gp.tileSize * 29;
            gp.iTile[mapNum][28].worldY = gp.tileSize * 15;
            
            
            mapNum = 2;
            gp.iTile[mapNum][29] = new InteractWall(gp);
            gp.iTile[mapNum][29].worldX = gp.tileSize * 17;
            gp.iTile[mapNum][29].worldY = gp.tileSize * 29;
            
            gp.iTile[mapNum][30] = new InteractWall(gp);
            gp.iTile[mapNum][30].worldX = gp.tileSize * 17;
            gp.iTile[mapNum][30].worldY = gp.tileSize * 30;
            
            gp.iTile[mapNum][32] = new InteractWall(gp);
            gp.iTile[mapNum][32].worldX = gp.tileSize * 17;
            gp.iTile[mapNum][32].worldY = gp.tileSize * 31;
            
            gp.iTile[mapNum][33] = new InteractWall(gp);
            gp.iTile[mapNum][33].worldX = gp.tileSize * 17;
            gp.iTile[mapNum][33].worldY = gp.tileSize * 32;
            
            gp.iTile[mapNum][34] = new InteractWall(gp);
            gp.iTile[mapNum][34].worldX = gp.tileSize * 18;
            gp.iTile[mapNum][34].worldY = gp.tileSize * 32;
            
            gp.iTile[mapNum][35] = new InteractWall(gp);
            gp.iTile[mapNum][35].worldX = gp.tileSize * 18;
            gp.iTile[mapNum][35].worldY = gp.tileSize * 33;
            
            gp.iTile[mapNum][36] = new InteractWall(gp);
            gp.iTile[mapNum][36].worldX = gp.tileSize * 18;
            gp.iTile[mapNum][36].worldY = gp.tileSize * 34;
            
            gp.iTile[mapNum][37] = new InteractWall(gp);
            gp.iTile[mapNum][37].worldX = gp.tileSize * 10;
            gp.iTile[mapNum][37].worldY = gp.tileSize * 22;
            
            gp.iTile[mapNum][38] = new InteractWall(gp);
            gp.iTile[mapNum][38].worldX = gp.tileSize * 30;
            gp.iTile[mapNum][38].worldY = gp.tileSize * 19;
            
            gp.iTile[mapNum][39] = new InteractWall(gp);
            gp.iTile[mapNum][39].worldX = gp.tileSize * 22;
            gp.iTile[mapNum][39].worldY = gp.tileSize * 19;
            
            gp.iTile[mapNum][40] = new InteractWall(gp);
            gp.iTile[mapNum][40].worldX = gp.tileSize * 18;
            gp.iTile[mapNum][40].worldY = gp.tileSize * 13;
            
            gp.iTile[mapNum][41] = new InteractWall(gp);
            gp.iTile[mapNum][41].worldX = gp.tileSize * 18;
            gp.iTile[mapNum][41].worldY = gp.tileSize * 14;
            
            gp.iTile[mapNum][42] = new InteractWall(gp);
            gp.iTile[mapNum][42].worldX = gp.tileSize * 30;
            gp.iTile[mapNum][42].worldY = gp.tileSize * 28;
            
            gp.iTile[mapNum][43] = new InteractWall(gp);
            gp.iTile[mapNum][43].worldX = gp.tileSize * 38;
            gp.iTile[mapNum][43].worldY = gp.tileSize * 21;
            
            gp.iTile[mapNum][44] = new InteractWall(gp);
            gp.iTile[mapNum][44].worldX = gp.tileSize * 37;
            gp.iTile[mapNum][44].worldY = gp.tileSize * 10;
            
            gp.iTile[mapNum][45] = new InteractWall(gp);
            gp.iTile[mapNum][45].worldX = gp.tileSize * 9;
            gp.iTile[mapNum][45].worldY = gp.tileSize * 9;

        }

    }
}