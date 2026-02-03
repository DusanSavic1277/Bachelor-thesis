package rs.ac.singidunum;

import entity.Entity;
import java.awt.Rectangle;

public class EventHandler extends Entity{
    
    GamePanel gp;
    Rectangle eventRect[][][];
    boolean canTouchEvent = false;
    boolean finalBossOn = false;
    int previousEventX, previousEventY;
    int eventRectDefaultX, eventRectDefaultY;
    
    
    public EventHandler(GamePanel gp) {
        super(gp);
        this.gp = gp;

        eventRect = new Rectangle[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new Rectangle();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRectDefaultX = eventRect[map][col][row].x;
            eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
        
        setDialogue();

    }
    
    public void checkEvent() {
        
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distanca = Math.max(xDistance, yDistance);
        if (distanca > gp.tileSize) {
            canTouchEvent = true;
        }
        
        if (canTouchEvent == true) {
            if (event(0, 10, 9, "any") == true) {teleport(3, 26, 41, 18); previousEventX = gp.player.worldX; previousEventY = gp.player.worldY;}//U tamnicu/boss
            else if (event(3, 26, 41, "any") == true) {teleport(0, 10, 9, 0); previousEventX = gp.player.worldX; previousEventY = gp.player.worldY;}//Iz tamnice/boss
            else if (event(0, 32, 15, "any") == true) {teleport(2, 9, 41, 18); previousEventX = gp.player.worldX; previousEventY = gp.player.worldY;}//U tamnicu/kljuc
            else if (event(2, 9, 41, "any") == true) {teleport(0, 32, 15, 0); previousEventX = gp.player.worldX; previousEventY = gp.player.worldY;}//Iz tamnice/kljuc
            else if (event(0, 23, 12, "up") == true) {restoreHealth(); previousEventX = gp.player.worldX; previousEventY = gp.player.worldY;}
            else if (event(0, 11, 38, "any") == true) {teleport(1, 12, 13, 18); previousEventX = gp.player.worldX; previousEventY = gp.player.worldY;}//U kucu
            else if (event(1, 12, 13, "any") == true) {teleport(0, 11, 38, 0); previousEventX = gp.player.worldX; previousEventY = gp.player.worldY;}//Iz kuce
            else if (event(3, 25, 27, "any") == true) {finalBoss(); finalBossOn = true; previousEventX = gp.player.worldX; previousEventY = gp.player.worldY;}//Final boss
        }
    }
    
    public boolean event(int map, int col, int row, String eventDirection) {

        boolean event = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row])) {
                if (gp.player.direction.contentEquals(eventDirection) || eventDirection.contentEquals("any")) {
                    event = true;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;

            eventRect[map][col][row].x = eventRectDefaultX;
            eventRect[map][col][row].y = eventRectDefaultY;
        }

        return event;

    }
    
    public void setDialogue(){
        dialogues[0][0] = "Your HP has been restored!";
    }
    
    public void teleport(int map, int col, int row, int i){
        
        gp.stopMusic();
        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        canTouchEvent = false;
        gp.asset.setMonster();
        gp.playMusic(i);
        gp.playSE(13);
        
    }
    
    public void restoreHealth(){
        
        if (gp.keyH.enter == true) {
            gp.playSE(9);
            gp.player.attacking = false;
            startDialogue(this, 0);
            gp.player.life = gp.player.maxLife;
            gp.asset.setMonster();
        }  
    }
    
    public void finalBoss(){
        
        if(gp.bossBattleOn == false && finalBossOn == false){
            
            gp.gameState = GameState.CUTSCENE;
            gp.cutscene.sceneNum = gp.cutscene.FINALBOSS;
        }
        
    }
}