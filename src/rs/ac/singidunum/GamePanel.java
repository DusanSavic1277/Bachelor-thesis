package rs.ac.singidunum;

import entity.Entity;
import entity.Player;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import pathfinding.PathFinder;
import tile.TileManager;
import tile_intercative.InteractiveTile;

public class GamePanel extends JPanel implements Runnable {

    public final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 4;
    public int currentMap = 0;

    final int FPS = 60;
    
    public BufferedImage image;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public Sound music = new Sound();
    public Sound se = new Sound();
    public EventHandler event = new EventHandler(this);
    Thread gameThread;
    public CheckCollision check = new CheckCollision(this);
    public AssetManagement asset = new AssetManagement(this);
    public UI ui = new UI(this);
    public PathFinder pathFinder = new PathFinder(this);
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][17];
    public Entity npc[][] = new Entity[maxMap][1];
    public Entity monster[][] =  new Entity[maxMap][22];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][46];
    public CutsceneManager cutscene = new CutsceneManager(this);
    ArrayList<Entity> entityList = new ArrayList<>();
    public boolean objectPickedUp[][] = new boolean[maxMap][17];
    
    public GameState gameState = GameState.TITLE;
    
    public boolean bossBattleOn = false;
    

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        asset.setObject();
        asset.setNpc();
        asset.setMonster();
        asset.setInteractiveTile();
        gameState = GameState.TITLE;
        getTitleImage();

    }

    public void startNewGame() {

        gameState = GameState.PLAY;
        player = new Player(this, keyH);
        currentMap = 0;
        removeTempEntity();
        
        for (int i = 0; i < objectPickedUp[1].length; i++) {
        objectPickedUp[currentMap][i] = false; //Restartuje objekte pri New Game
    }
        
        asset.setObject();
        asset.setNpc();
        asset.setMonster();
        asset.setInteractiveTile();
        getTitleImage();
        playMusic(0);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run(){
        
        long lastTime = System.nanoTime();
        double nanoSec = 1000000000 / FPS;
        double delta = 0;
        
        while(gameThread != null){
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / nanoSec;
            lastTime = currentTime;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

        if (gameState == GameState.PLAY) {
            player.update();
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null){
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false){
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
                
            }
            
            for (int i = 0; i < iTile[1].length; i++) {
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].update();
                }
                
            }
        }
        
        if (gameState == GameState.RESTART) {
          
            gameState = GameState.PLAY;
            removeTempEntity();
            bossBattleOn = false;
            
            for (int i = 0; i < objectPickedUp[1].length; i++) {
                objectPickedUp[currentMap][i] = true;
            }
            
            stopMusic();
            event.finalBossOn = false;
            asset.setObject();
            asset.setMonster();
            player.resetAfterDeath();
            asset.setInteractiveTile();
            getTitleImage();
            playMusic(0);
            
        }
        
        if (gameState == GameState.QUIT) {
            System.exit(0);
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState == GameState.TITLE) {
            ui.draw(g2);

        } else {
            
            //TILE
            tileM.draw(g2);
            
            for (int i = 0; i < iTile[1].length; i++) {
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].draw(g2);
                }
                
            }

            entityList.add(player);

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            //Bubble sort
            for (int i = 0; i < entityList.size() - 1; i++) {
                for (int j = 0; j < entityList.size() - 1 - i; j++) {
                    Entity e1 = entityList.get(j);
                    Entity e2 = entityList.get(j + 1);

                    if (e1.worldY > e2.worldY) {
                        //Zamena
                        entityList.set(j, e2);
                        entityList.set(j + 1, e1);
                    }
                }
            }

            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            entityList.clear();
            
            cutscene.draw(g2);

            //TEXT
            ui.draw(g2);
        }

        g2.dispose();
    }
    
    public void playMusic(int i){
        
        music.setFile(i);
        music.play();
        music.loop();
        
    }
    
    public void stopMusic(){
        
        music.stop();
        
    }

    public void playSE(int i) {

        se.setFile(i);
        se.play();

    }

    public void getTitleImage() {
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/titleImg/singidunum.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeTempEntity() {
        
        for (int mapNum = 0; mapNum < maxMap; mapNum++) {
            for (int i = 0; i < obj[1].length; i++) {
                
                if(obj[mapNum][i] != null && obj[mapNum][i].temp == true){
                    obj[mapNum][i] = null;
                }
                
            }
            
        }

    }
}