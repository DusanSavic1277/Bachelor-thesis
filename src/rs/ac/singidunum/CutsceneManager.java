package rs.ac.singidunum;

import entity.PlayerDummy;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import monster.MON_FinalBoss;
import object.OBJ_Diamond;
import object.OBJ_Door_Iron;

public class CutsceneManager {
    
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;
    String newGameMessage;
    String newGameMessage2;
    
    public final int NA = 0;
    public final int FINALBOSS = 1;
    public final int ENDING = 2;
    public final int NEW_GAME = 3;
    
    public CutsceneManager(GamePanel gp){
        this.gp = gp;
        
        newGameMessage = "Kao osnova za ovaj projekat korišćen je video\n"
                + "tutorijal sa YouTube kanala RyiSnow, koji predstavlja\n"
                + "kompletan proces izrade jednostavne 2D igre u Javi.\n"
                + "Ovaj šasblon je iskorišćen kao tehnički temelj\n"
                + "na kome su vršene adaptacije, proširenja i prilagođavanja\n"
                + "u skladu sa ciljevima ovog rada.";
        
        newGameMessage2 = "Tutorijal je korišćen kao edukativni materijal\n"
                + "tokom izrade projekta. Cilj ovog rada je\n"
                + "da se pored savladanja osnovne arhitekture\n"
                + "2D igara u Javi, primeni i proširi postojeća logika\n"
                + "i na taj način pokaže samostalna primena stečenog znanja.";
        
        endCredit = "Programmer\n"
                + "Dušan Savić"
                + "\n\n\n\n\n\n\n\n\n\n"
                + "Design/Music\n"
                + "YouTube RyiSnow\n\n"
                + "Artwork for Deadpool\n"
                + "Milan Branković\n\n"
                + "Artwork for Wolverine\n"
                + "Milan Branković\n\n"
                + "Sowtware Testing & QA\n"
                + "Dušan Savić\n\n"
                + "Beta testing & Feedback\n"
                + "Uroš Kuprešan\n"
                + "Adam Tanasković\n"
                + "Kosta Bilibajkić\n\n"
                + "Gameplay Mechanics\n"
                + "YouTube RyiSnow\n\n"
                + "Happiness manager\n"
                + "Dog Mina";
    }
    
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        
        switch(sceneNum){
            case FINALBOSS: scene_finalBoss(); break;
            case ENDING: scene_ending(); break;
            case NEW_GAME: scene_newGame(); break;
        }
        
    }
    
    public void scene_newGame() {

        if (scenePhase == 0) {
            drawBlacScreen(1f);
            
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            
            newGame(alpha, 30f, gp.tileSize * 3, newGameMessage, 50);

            if (counetrReached(1200)) {
                scenePhase++;
            }
        }
        else if(scenePhase == 1){
            
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlacScreen(alpha);
            
            if(alpha == 1){
                alpha = 0;
                scenePhase++;
            }
        }
        else if (scenePhase == 2) {
            drawBlacScreen(1f);
            
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            
            newGame(alpha, 30f, gp.tileSize * 4, newGameMessage2, 50);

            if (counetrReached(1200)) {
                scenePhase++;
            }
        } else if (scenePhase == 3) {
            sceneNum = NA;
            scenePhase = 0;
            gp.startNewGame();
        }

    }

    public void scene_finalBoss() {

        if (scenePhase == 0) {
            gp.bossBattleOn = true;

            for (int i = 0; i < gp.obj[1].length; i++) {
                if (gp.obj[gp.currentMap][i] == null) {
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 28;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.stopMusic();
                    gp.playSE(16);
                    break;
                }
            }
            
            for (int i = 0; i < gp.npc[1].length; i++) {
                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                } 
            }
            gp.player.drawing = false;
            scenePhase++;
        }
        
        if (scenePhase == 1) {
            gp.player.worldY -= 2;
            if(gp.player.worldY < gp.tileSize * 16){
                scenePhase++;
            }
        }
        
        if(scenePhase == 2){
            
            for (int i = 0; i < gp.monster[1].length; i++) {
                
                if(gp.monster[gp.currentMap][i] != null && 
                        gp.monster[gp.currentMap][i].name.equals(MON_FinalBoss.bossName)){
                    
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        
        
        if (scenePhase == 3) {
            gp.gameState = GameState.DIALOGUE;
            gp.ui.drawDialogueScreen();
            
            if (gp.ui.npc.dialogues[gp.ui.npc.dialogueSet][gp.ui.npc.dialogueIndex] == null) {
                scenePhase++;
                gp.gameState = GameState.CUTSCENE;
            }
        }
        
        
        if(scenePhase == 4){
            
            for (int i = 0; i < gp.npc[1].length; i++) {
                
                if(gp.npc[gp.currentMap][i] != null && 
                        gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                    
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            
            gp.player.drawing = true;
            
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = GameState.PLAY;
            
            gp.stopMusic();
            gp.playMusic(17);
        }
    }
    
    public void scene_ending(){
        
        if(scenePhase == 0){
            
            gp.stopMusic();
            gp.ui.npc = new OBJ_Diamond(gp);
            scenePhase++;
            
        }
        
        if (scenePhase == 1) {
            gp.gameState = GameState.DIALOGUE;
            gp.ui.drawDialogueScreen();

            if (gp.ui.npc.dialogues[gp.ui.npc.dialogueSet][gp.ui.npc.dialogueIndex] == null) {
                scenePhase++;
                gp.gameState = GameState.CUTSCENE;
            }
        }
        
        if (scenePhase == 2){
            gp.playSE(3);
            scenePhase++;
        }
        
        if (scenePhase == 3){
            
            if(counetrReached(240) == true){
                scenePhase++;
            }
        }
        
        if (scenePhase == 4){
            
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlacScreen(alpha);
            
            if(alpha == 1){
                alpha = 0;
                scenePhase++;
            }
        }
        
        if(scenePhase == 5){
            drawBlacScreen(1f);
            
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            
            String text = "The Skeleton Lord has been defeated.\n"
                    + "Treasure has been claimed...\n"
                    + "But this is not the end of the story.\n"
                    + "The adventure has just begun.";
            drawString(alpha, 38f, 228, text, 60);
            
            if(counetrReached(720) == true){
                gp.playMusic(19);
                scenePhase++;
            }
        }
        
        if(scenePhase == 6){
            drawBlacScreen(1f);
            
            drawString(1f, 38f, gp.screenHeight / 2, "Diplomski rad - Dušan Savić 2021200044", 40);
            if (counetrReached(480) == true) {
                scenePhase++;
            }
        }
        
        if(scenePhase == 7){
            drawBlacScreen(1f);
            
            y = gp.screenHeight / 2;
            drawEndCredit(1f, 38f, y, endCredit, 40);
            
            if (counetrReached(480) == true) {
                scenePhase++;
            }
        }
        
        if(scenePhase == 8){
            drawBlacScreen(1f);
            
            y--;
            drawEndCredit(1f, 38f, y, endCredit, 40);
            if (counetrReached(1670) == true) {
                scenePhase++;
            }
        }
        
        if (scenePhase == 9) {
            drawBlacScreen(1f);

            drawEndCredit2(1f, 48f, gp.screenHeight / 2, "Thank you for playing!", 40);
            if(gp.keyH.enter == true){
                gp.gameState = GameState.QUIT;
            }
        }
        
    }
    
    public boolean counetrReached(int target){
        
        boolean counetrReached = false;
        
        counter++;
        if(counter > target){
            counetrReached = true;
            counter = 0;
        }
        return counetrReached;
    }
    
    public void drawBlacScreen(float alpha){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);

        // Ucitavanje fonta
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf"));
            customFont = customFont.deriveFont(Font.BOLD, fontSize);
            g2.setFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
            g2.setFont(new Font("Arial", Font.BOLD, (int) fontSize)); // Rezervni font
        }

        for (String line : text.split("\n")) {
            int x = gp.ui.getXforCenteredText2(line);
            g2.drawString(line, x - 30, y - 20);
            y += lineHeight;
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
    
    public void drawEndCredit(float alpha, float fontSize, int y, String text, int lineHeight){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        
        // Ucitavanje fonta
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf"));
            customFont = customFont.deriveFont(Font.BOLD, fontSize);
            g2.setFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
            g2.setFont(new Font("Arial", Font.BOLD, (int) fontSize)); // Rezervni font
        }
        
        for (String line : text.split("\n")) {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x - 140, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
    public void drawEndCredit2(float alpha, float fontSize, int y, String text, int lineHeight){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        
        // Ucitavanje fonta
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf"));
            customFont = customFont.deriveFont(Font.BOLD, fontSize);
            g2.setFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
            g2.setFont(new Font("Arial", Font.BOLD, (int) fontSize)); //Rezervni font
        }
        
        for (String line : text.split("\n")) {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x - 200, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
     public void newGame(float alpha, float fontSize, int y, String text, int lineHeight) {

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);

        // Ucitavanje fonta
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf"));
            customFont = customFont.deriveFont(Font.BOLD, fontSize);
            g2.setFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
            g2.setFont(new Font("Arial", Font.BOLD, (int) fontSize)); //Rezervni font
        }

        for (String line : text.split("\n")) {
            int x = gp.tileSize;
            g2.drawString(line, x, y);
            y += lineHeight;
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
    
}