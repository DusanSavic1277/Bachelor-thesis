package rs.ac.singidunum;

import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import object.OBJ_Heart;

public class UI {
    
    GamePanel gp;

    Font maruMonica;
    Graphics2D g2;
    BufferedImage heart_blank, heart_half, heart_full;
    public boolean messageOn = false;
    public String messageQuest = "";
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    public int subState = 0;
    public Entity npc;
    
    public UI(GamePanel gp){
        
        this.gp = gp;
        
        
        try {
            
            InputStream input = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, input);
            
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        
        OBJ_Heart heart = new OBJ_Heart(gp);
        heart_blank = heart.image;
        heart_half = heart.image2;
        heart_full = heart.image3;
    }
    
    public void showMessage(String text){
        
        message.add(text);
        messageCounter.add(0);
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(maruMonica);
        g2.setColor(Color.white);

         if(gp.gameState == GameState.TITLE){
             drawTitleScreene();
         }
         
         if(gp.gameState == GameState.PLAY){
             drawPlayerLife();
             drawMonsterLife();
             drawMessage();
         }
         
         if(gp.gameState == GameState.PAUSE){
             drawPauseScreen();
         }
         
         if(gp.gameState == GameState.CHARACTER){
             drawCharacterScreen();
             drawInventory();
         }
         
         if(gp.gameState == GameState.DIALOGUE){
             drawDialogueScreen();
         }
         
         if(gp.gameState == GameState.OPTIONS){
             drawOptionScreen();
         }
         
         if(gp.gameState == GameState.GAME_OVER){
             drawGameOverScreen();
         }
    }
    
    public void drawTitleScreene(){
        
        g2.setColor(new Color(205, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        //Naslov
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Diplomski rad";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;
        
        //Senka naslova
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        
        //Boja naslova
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        
        // Postavljanje slike
        x = gp.screenWidth / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.image, x - 80, y - 60, 170, 170, null);
        
        //Meni
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 58F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize + 20;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }
        
    }
    
    public void drawPauseScreen(){
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSE";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }
    
    public void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int iconSize = 32;
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, /*gp.tileSize, gp.tileSize*/ iconSize, iconSize, null);
            i++;
            x += iconSize;
        }

        x = gp.tileSize / 2;
        i = 0;

        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, /*gp.tileSize, gp.tileSize*/ iconSize, iconSize, null);
            i++;

            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, /*gp.tileSize, gp.tileSize*/ iconSize, iconSize, null);
            }
            i++;
            x += iconSize;
        }

    }
    
    public void drawPlayerLife2() {

//        gp.player.life = 4;

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(4));
            g2.drawRect(x - 2, y - 2, gp.tileSize * 6 + 4, gp.tileSize / 2 + 4);

            g2.setColor(Color.red);
            g2.fillRect(x, y, gp.tileSize / gp.tileSize, gp.tileSize / 2);
            i++;
        }

        x = gp.tileSize / 2;
        i = 0;

        while (i < gp.player.life) {
            g2.fillRect(x, y, gp.tileSize, gp.tileSize / 2);
            i++;
            if (i < gp.player.life) {
                g2.fillRect(x, y, gp.tileSize * 2, gp.tileSize / 2);
                i++;
            }
            if (i < gp.player.life) {
                g2.fillRect(x, y, gp.tileSize * 3, gp.tileSize / 2);
                i++;
            }
            if (i < gp.player.life) {
                g2.fillRect(x, y, gp.tileSize * 4, gp.tileSize / 2);
                i++;
            }
            if (i < gp.player.life) {
                g2.fillRect(x, y, gp.tileSize * 5, gp.tileSize / 2);
                i++;
            }
            if (i < gp.player.life) {
                g2.fillRect(x, y, gp.tileSize * 6, gp.tileSize / 2);
            }
            i++;
        }

    }
    
    public void drawMonsterLife() {

        //Monster HP bar
        for (int i = 0; i < gp.monster[1].length; i++) {
            
            Entity monster = gp.monster[gp.currentMap][i];

            if (monster != null && monster.inCamera() == true) {

                if (monster.hpBar == true && monster.boss == false) {

                    double oneScale = (double) gp.tileSize / monster.maxLife;
                    double hpBarValue = oneScale * monster.life;

                    g2.setColor(Color.darkGray);
                    g2.fillRect(monster.getScreenX() - 1, monster.getScreenY() - 11, gp.tileSize + 2, 12);

                    g2.setColor(Color.red);
                    g2.fillRect(monster.getScreenX(), monster.getScreenY() - 10, (int) hpBarValue, 10);

                    monster.hpBarCounter++;

                    if (monster.hpBarCounter > 300) {
                        monster.hpBarCounter = 0;
                        monster.hpBar = false;
                    }

                }
                else if (monster.boss == true) {

                    double oneScale = (double) gp.tileSize * 8 / monster.maxLife;
                    double hpBarValue = oneScale * monster.life;
                    
                    int x = gp.screenWidth / 2 - gp.tileSize * 4;
                    int y = gp.tileSize * 10;

                    g2.setColor(Color.darkGray);
                    g2.fillRect(x - 1, y - 1, gp.tileSize * 8 + 2, 22);

                    g2.setColor(Color.red);
                    g2.fillRect(x, y, (int) hpBarValue, 20);
                    
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
                    g2.setColor(Color.white);
                    g2.drawString(monster.name, x + 4, y - 10);

                }
            }

        }

    }
    
    public void drawMessage() {

        int messageX = 30;
        int messageY = gp.tileSize * 5;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < message.size(); i++) {

            if (message.get(i) != null) {
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;//messageCounter++
                messageCounter.set(i, counter);

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    
    public void drawCharacterScreen(){
        
        int frameX = gp.tileSize;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 5;
        int frameHeight = gp.tileSize * 10;
        
        drawWindow(frameX, frameY, frameWidth, frameHeight);
        
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 48;
        
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Strenght", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Next level", textX, textY);
        textY += lineHeight + 30;
        
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight;
        
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;
        
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY, null);
        textY += gp.tileSize;
   
    }
    
    public void drawInventory() {

        //Ram
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;

        drawWindow(frameX, frameY, frameWidth, frameHeight);

        //Slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //Crtanje stavki igraca
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            
            //Kursor za obelezavanje izabranih stavki u inventaru
            if(gp.player.inventory.get(i) == gp.player.currentWeapon){
                g2.setColor(Color.orange);
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            
            //Prikazivanje kolicine predmeta u inventaru
            if(gp.player.inventory.get(i).amount > 1){
                g2.setFont(g2.getFont().deriveFont(32F));
                int amountX;
                int amountY;
                
                String amount = "" + gp.player.inventory.get(i).amount;
                amountX = getXforAlignToRightText(amount, slotX + 44);
                amountY = slotY + gp.tileSize;
                
                g2.setColor(Color.white);
                g2.drawString(amount, amountX, amountY - 5);
            }
            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //Kursor
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            //Draw cursor
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            //Ram za deskripciju predmeta
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight + 7;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;

            //Crtanje teksta deskripcije
            int textX = dFrameX + 20;
            int textY = dFrameY + 30;
            g2.setFont(g2.getFont().deriveFont(25F));

            int itemIndex = getItemIndexOnSlot();

            if (itemIndex < gp.player.inventory.size()) {
                
                drawWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

                for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);

                    textY += 32;

                }
            }
        }
    

    
    public void drawOptionScreen(){
        
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(38F));
        
        int frameX = gp.tileSize * 4;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        
        drawWindow(frameX, frameY, frameWidth, frameHeight);
        
        switch(subState){
            case 0: options_screen(frameX, frameY); break;
            case 1: options_controle(frameX, frameY); break;
            case 2: options_endGame(frameX, frameY); break;
        }
        
    }
    
    public void options_screen(int frameX, int frameY){
        
        int textX;
        int textY;
        
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        //Kontrole
        textX = frameX + gp.tileSize;
        textY += 124;
        g2.drawString("Controles", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enter == true){
                subState = 1;
                gp.keyH.enter = false;
            }
        }
        
        //Kraj igre
        textY += gp.tileSize;
        g2.drawString("End game", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enter == true){
                subState = 2;
                gp.keyH.enter = false;
            }
        }
        
        //Nazad
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }
        if (gp.keyH.enter == true) {
            gp.gameState = GameState.PLAY;
            gp.keyH.enter = false;
        }
    }
    
    public void options_controle(int frameX, int frameY){
        
        int textX;
        int textY;
        
        String text = "Controles";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        textX = frameX + 30;
        textY += gp.tileSize * 2;
        
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Interact/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Character screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;
        
        textX = frameX + gp.tileSize * 4;
        textY = frameY + gp.tileSize * 3;
        g2.drawString("W/A/S/D", textX + 50, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX + 75, textY); textY += gp.tileSize;
        g2.drawString("C", textX + 95, textY); textY += gp.tileSize;
        g2.drawString("P", textX + 95, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX + 70, textY); textY += gp.tileSize;
        
        //Nazad
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 30, textY);
        }
        if(gp.keyH.enter == true){
            subState = 0;
            gp.keyH.enter = false;
        }
    }
    
    public void options_endGame(int frameX, int frameY){
        
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        
        currentDialogue = "Quit the game, and \nreturn to the title screen?";
        
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, textX - 20, textY);
            textY += 40;
        }
        
        //DA
        String text = "YES";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 30, textY);
            if(gp.keyH.enter == true){
                gp.gameState = GameState.TITLE;
                gp.stopMusic();
            }
        }
        
        //NE
        text = "NO";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY - 100);
        if(commandNum == 1){
            g2.drawString(">", textX - 30, textY - 100);
            if(gp.keyH.enter == true){
                subState = 0;
                gp.keyH.enter = false;
            }
        }
    }
    
    public void drawDialogueScreen() {

        int frameX = gp.tileSize;
        int frameY = gp.tileSize / 2;
        int frameWidth = gp.tileSize * 14;
        int frameHeight = gp.tileSize * 4;

        drawWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        frameX += gp.tileSize / 2;
        frameY += gp.tileSize;
        
        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
            currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            
            if(gp.keyH.enter == true){
                if(gp.gameState == GameState.DIALOGUE || gp.gameState == GameState.CUTSCENE){
                    npc.dialogueIndex++;
                    gp.keyH.enter = false;
                }
            }
        }
        else{
            npc.dialogueIndex = 0;
            
            if(gp.gameState == GameState.DIALOGUE){
                gp.gameState = GameState.PLAY;
            }
            if(gp.gameState == GameState.CUTSCENE){
                gp.cutscene.scenePhase++;
            }
        }

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, frameX, frameY);
            frameY += 30;
        }

    }
    
    public void drawGameOverScreen() {
        
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
        
        text = "Game Over";
        
        //Senka
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        
        //Glavna poruka
        g2.setColor(Color.white);
        g2.drawString(text, x - 5, y - 5);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 58F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }
        text = "Quit";
        x = getXforCenteredText(text);
        y += gp.tileSize + 20;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }
        
    }

    public int getItemIndexOnSlot() {
        int iteamIndex = slotCol + (slotRow * 5);

        return iteamIndex;
    }
    
    public void drawWindow(int x, int y, int width, int height){
        
        Color color = new Color(0, 0, 0, 225);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x, y, width, height, 35, 35);
    }
    
    public int getXforCenteredText(String text) {
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();        
        int x = gp.screenWidth / 2 - length / 2;

        return x;
    }
    
    public int getXforCenteredText2(String tekst) {

        int length = (int) g2.getFontMetrics().getStringBounds(tekst, g2).getWidth();
        int x = gp.screenWidth / 5 - length / 2;
        return x;
    }
    
    public int getXforAlignToRightText(String text, int tailX) {
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();        
        int x = tailX - length;

        return x;
    }
    
}