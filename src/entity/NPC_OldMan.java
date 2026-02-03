package entity;

import java.awt.Rectangle;
import java.util.Random;
import object.OBJ_Key;
import rs.ac.singidunum.GamePanel;
import rs.ac.singidunum.GameState;

public class NPC_OldMan extends Entity{
    
    private boolean readyToEnterHome = false;
    private int enterHomeCounter = 0;
    
    public boolean dialogFinished = false;
    public boolean helpAccepted = false;

    public boolean startedFollowing = false;
    public boolean playNewQuestSound = false;
    public boolean playNewQuestSound2 = false;
    public boolean levelUp = false;
    public boolean questOn = false;
    public int levelUpCounter = 0;
    
    public int questStage = 0;
    
    
    public NPC_OldMan(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 1;
        dialogueSet = -1;
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
        up1 = setup("/npcImg/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npcImg/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npcImg/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npcImg/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npcImg/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npcImg/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npcImg/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npcImg/oldman_right_2", gp.tileSize, gp.tileSize);
        

        setDialogue();
                
    }

    public void setDialogue() {

        if (questStage == 0) {
            dialogues[0][0] = "Hello young lad, can you help me?";
            dialogues[0][1] = "I want to go back home,\nbut I can't because of those Green slimes...";
        } 
        else if (questStage == 1) {
            dialogues[1][0] = "My house is straight down and on the left.";
        }
        else if (questStage == 2) {
            dialogues[2][0] = "Thank you for bringing me home safely!";
        }
        else if (questStage == 3) {
            dialogues[3][0] = "There's one last thing...";
            dialogues[3][1] = "An evil presence has taken over the island...";
            dialogues[3][2] = "Please, defeat him, there are three keys needed to\nget to him, I only have one, take it, you'll need it\nto reach the dungeon.";
            
            gp.player.inventory.add(new OBJ_Key(gp));
        } 
        
        else if (questStage == 4) {
            dialogues[4][0] = "Good luck, young warrior.";
           
        }

    }
    
    @Override
    public void update() {
        super.update();

        if (startedFollowing == true && gp.gameState == GameState.PLAY) {
            playNewQuestSound = true;
            startedFollowing = false;
        }
        if (playNewQuestSound == true) {
            gp.playSE(14); //cuje se tek kad se izadje iz dijaloga
            playNewQuestSound = false;
        }

        if (readyToEnterHome == true) {
            enterHomeCounter++;
            if (enterHomeCounter >= 50) {

                //Prebaci NPC na drugu mapu
                gp.npc[0][0] = null; //skloni npc sa stare mape
                gp.npc[1][0] = this; //prebaci istu instancu na novu mapu
                this.map = 1;
                this.worldX = 11 * gp.tileSize;
                this.worldY = 10 * gp.tileSize;
               

                //Skloni ga sa trenutne mape
                readyToEnterHome = false;
                enterHomeCounter = 0;

                gp.ui.showMessage("QUEST COMPLETED");
                gp.playSE(15);
                
                gp.player.exp += 1;
                
            }
        }
    }
    
    @Override
    public void setAction() {

        if(onPath == true && questOn == true){
            followPlayer();
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

    }
    
    public void followPlayer() {

        //Koordinate ciljnog tile-a
        int stopCol = 40;
        int stopRow = 11;

        //NPC-ove trenutne tile koordinate
        int npcRow = (worldX + solidArea.x) / gp.tileSize;
        int npcCol = (worldY + solidArea.y) / gp.tileSize;

        //Ako je stigao do cilja, prestaje da prati
        if (npcCol == stopCol && npcRow == stopRow) {
            onPath = false;

            //Update quest stage
            questStage = 2;

            // Osveži dijalog
            setDialogue();

            //Pokreni zavrsni dijalog
            gp.gameState = GameState.DIALOGUE;
            dialogueSet = 2;
            dialogueIndex = 0;
            startDialogue(this, dialogueSet);
            
            readyToEnterHome = true;
            return; //prekini odmah da ne nastavi
        }

        //Inace, nastavi da prati igrača
        int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
        int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
        searchPath(goalCol, goalRow);

    }
    
    @Override
    public void speak() {
        facePlayer();
        setDialogue();

        if (questStage == 0) {
            startDialogue(this, 0);
            questStage = 1;
            onPath = true;
            questOn = true;
            startedFollowing = true;
            gp.ui.showMessage("NEW QUEST: HELP THE ELDERLY");

        } else if (questStage == 1) {
            startDialogue(this, 1);

        } else if (questStage == 2) {
            startDialogue(this, 3);
            //Dijalog nakon pracenja
            questStage = 3;

        } else if (questStage == 3) {
                startDialogue(this, 3);
                questStage = 4;
                gp.ui.showMessage("NEW QUEST: FREE THE ISLAND");
        }
        else if (questStage == 4) {
            startDialogue(this, 4);
        }
        
    }
    
}