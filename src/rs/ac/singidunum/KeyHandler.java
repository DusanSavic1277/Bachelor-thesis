package rs.ac.singidunum;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    
    public boolean up, down, left, right, enter, character, pause;
    
    public KeyHandler(GamePanel gp){
        
        this.gp = gp;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Stanje titla
        if (gp.gameState == GameState.TITLE) {
            titleState(code);
        }
        
        // Stanje igranja
        else if (gp.gameState == GameState.PLAY) {
            playState(code);
        }
        
        // Stanje pauze
        else if (gp.gameState == GameState.PAUSE) {
            pauseState(code);
        }
        
        // Stanje dijaloga
        else if (gp.gameState == GameState.DIALOGUE || gp.gameState == GameState.CUTSCENE) {
            dialogueState(code);
        }
        
        //Status karaktera
        else if(gp.gameState == GameState.CHARACTER){
            characterState(code);
        }
        
        //Opcije
        else if(gp.gameState == GameState.OPTIONS){
            optionState(code);
        }
        
        //Kraj igre
        else if(gp.gameState == GameState.GAME_OVER){
            gameOverState(code);
        }
    }
    
    public void titleState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
        }
        
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            } 
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = GameState.CUTSCENE;
                gp.cutscene.sceneNum = gp.cutscene.NEW_GAME;
                gp.cutscene.scenePhase = 0;
//                gp.startNewGame();
            }
            if (gp.ui.commandNum == 1) {
                gp.gameState = GameState.QUIT;
            }
        }
    }
    
    public void playState(int code){
        
        if (code == KeyEvent.VK_W) {
            up = true;
        }
        if (code == KeyEvent.VK_S) {
            down = true;
        }
        if (code == KeyEvent.VK_D) {
            right = true;
        }
        if (code == KeyEvent.VK_A) {
            left = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = GameState.PAUSE;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = GameState.CHARACTER;
        }
        if (code == KeyEvent.VK_ENTER) {
            enter = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = GameState.OPTIONS;
        }
        
    }
    
    public void pauseState(int code){
        
        if (code == KeyEvent.VK_P) {
            gp.gameState = GameState.PLAY;
        }
        
    }
    
    public void dialogueState(int code) {

            if (code == KeyEvent.VK_ENTER) {
                enter = true;
            }

    }
    
    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = GameState.PLAY;
        }

        if (code == KeyEvent.VK_W) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(5);
            }

        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
                gp.playSE(5);
            }

        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
                gp.playSE(5);
            }

        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
                gp.playSE(5);
            }
        }
        
        if (code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }

    }
    
    public void optionState(int code) {

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = GameState.PLAY;
        }
        if (code == KeyEvent.VK_ENTER) {
            enter = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxCommandNum = 2; break;
            case 2: maxCommandNum = 1; break;
        }

        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            gp.playSE(5);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            gp.playSE(5);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }

    }
    
    public void gameOverState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(5);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(5);

        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = GameState.RESTART;
            }
            if (gp.ui.commandNum == 1) {
                gp.player.alive = true;
                gp.player.life = gp.player.maxLife;
                gp.gameState = GameState.TITLE;
            }
        }

    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_W){
            up = false;
        }
        
        if(key == KeyEvent.VK_A){
            left = false;
        }
        
        if(key == KeyEvent.VK_S){
            down = false;
        }
        
        if(key == KeyEvent.VK_D){
            right = false;
        }
        
        if(key == KeyEvent.VK_ENTER){
            enter = false;
        }
        
    }
    
}