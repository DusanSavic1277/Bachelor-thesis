package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;
import rs.ac.singidunum.GameState;
import rs.ac.singidunum.UtilityTool;

public class Entity {

    GamePanel gp;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,
            image, image2, image3;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1,
            attackLeft2, attackRight1, attackRight2;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle();
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public String dialogues[][] = new String[15][15];

    //Stanje
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collision = false;
    public boolean inviceble = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBar = false;
    public boolean usePotion = false;
    public boolean stackable = false;
    public boolean regenerating = false;
    public boolean canRegenerate = false;
    public boolean onPath = false;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean boss;
    public boolean temp = false;
    public boolean drawing = true;

    //Brojaci
    public int spriteCounter = 0;
    public int invicebleCounter = 0;
    public int actionLockCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int potionCounter = 0;
    public int strengthBonus = 5;
    public int amount = 1;
    public int regenerationLife = 0;
    public int regenerationCounter = 0;
    public int shotAvailableCounter = 0;
    public int map;

    //Atributi karaktera
    public EntityType type = EntityType.PLAYER; // 0 = player, 1 = npc, 2 = monster
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int level;
    public int strength;
    public int maxAttack;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public Entity currentWeapon;

    //Atributi oruzija
    public int attackValue;
    public String description = "";

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x) / gp.tileSize;
    }

    public int getRow() {
        return (worldY + solidArea.y) / gp.tileSize;
    }
    
    public int getScreenX(){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        return screenX;
    }
    
    public int getScreenY(){
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        return screenY;
    }

    public int getCentarX() {

        int centarX = worldX + left1.getWidth() / 2;
        return centarX;

    }

    public int getCentarY() {

        int centarY = worldY + up1.getHeight() / 2;
        return centarY;

    }

    public int getXdistance(Entity target) {

        int xDistance = Math.abs((getCentarX() - target.getCentarX()));
        return xDistance;

    }

    public int getYdistance(Entity target) {

        int yDistance = Math.abs((getCentarY() - target.getCentarY()));
        return yDistance;

    }

    public void setAction() {}

    public void attackReaction() {}

    public int getAttack() {
        return 0;
    }

    public boolean use(Entity entity) {
        return false;
    }

    public void checkDrop() {}

    public void dropItem(Entity droppedItem) {

        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public void speak() {}
    
    public void facePlayer() {
        
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    
    public void startDialogue(Entity entity, int setNum){
        
        gp.gameState = GameState.DIALOGUE;
        gp.ui.npc = entity;
        dialogueSet = setNum;
        
    }

    public void interact() {}

    public void checkCollision() {

        collisionOn = false;
        gp.check.checkTile(this);
        gp.check.checkObj(this, false);
        gp.check.checkEntity(this, gp.npc);
        gp.check.checkEntity(this, gp.monster);
        gp.check.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.check.checkPlayer(this);

        if (type == EntityType.MONSTER && contactPlayer == true) {
            if (gp.player.inviceble == false) {
                gp.playSE(6);
                int damage = attack - gp.player.defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.player.life -= damage;
                gp.player.inviceble = true;
            }
            if (gp.player.life <= 0) {
                gp.ui.drawGameOverScreen();
            }
        }
    }

    public void update() {

        if (sleep == false) {

            if (attacking) {
                attacking();
            } else {

                setAction();
                checkCollision();

                if (collisionOn == false) {

                    switch (direction) {

                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;

                    }
                }

                spriteCounter++;
                if (spriteCounter > 10) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }

                if (inviceble) {
                    invicebleCounter++;
                    if (invicebleCounter > 40) {
                        inviceble = false;
                        invicebleCounter = 0;
                    }
                }
            }

            //Regeneracija
            if (canRegenerate) {
                if (regenerating == false && life <= maxLife / 2 && life > 0) {
                    regenerating = true;
                    regenerationLife = life + (maxLife / 2);
                    if (regenerationLife > maxLife) {
                        regenerationLife = maxLife;
                    }
                }

                if (regenerating) {
                    regenerationCounter++;

                    if (regenerationCounter >= 60) { //Svakih 60 frejmova (otprilike 1 sekunda)
                        life++;
                        regenerationCounter = 0;

                        if (life >= regenerationLife) {
                            regenerating = false; //Kraj regeneracije
                        }
                    }
                }
            }
            //Regeneracija

        }

    }

    public void checkAttackOrNot(int rate, int straight, int horizontal) {

        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction) {
            case "up":
                if (gp.player.getCentarY() < getCentarY() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;

            case "down":
                if (gp.player.getCentarY() > getCentarY() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;

            case "left":
                if (gp.player.getCentarX() < getCentarX() && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;

            case "right":
                if (gp.player.getCentarX() > getCentarX() && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;

        }

        if (targetInRange == true) {
            //Proveri da li inicira napad
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;

            }
        }

    }


    public void chasingPlayer(int interval){
        
        actionLockCounter++;
        
        if (actionLockCounter > interval){
            
            if(getXdistance(gp.player) > getYdistance(gp.player)){
                if(gp.player.getCentarX() < getCentarX()){
                    direction = "left";
                }
                else{
                    direction = "right";
                }
            }
            else if(getXdistance(gp.player) < getYdistance(gp.player)){
                if(gp.player.getCentarY() < getCentarY()){
                    direction = "up";
                }
                else{
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
        
    }
    
    public void attacking() {

        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == EntityType.MONSTER) {

                if (gp.check.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }

            } else {
                int monsterIndex = gp.check.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex);

                int iTileIndex = gp.check.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);
            }

            worldX = currentWorldX;
            worldY = currentWorldY;

            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }

    public void damagePlayer(int napad) {

        if (gp.player.inviceble == false) {
            gp.playSE(6);

            int damage = attack - gp.player.defense;
            gp.player.life -= damage;
            gp.player.inviceble = true;
        }

    }
    
    public boolean inCamera() {
        boolean inCamera = false;
        
        if (worldX + gp.tileSize * 5 > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize * 5 > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            inCamera = true;
        }
        return inCamera;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if (inCamera() == true) {

            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch (direction) {
                case "up":
                    if (attacking == false) {
                        if (spriteNum == 1) {image = up1;}
                        if (spriteNum == 2) {image = up2;}
                    }
                    if (attacking == true) {
                        tempScreenY -= up1.getHeight();
                        if (spriteNum == 1) {image = attackUp1;}
                        if (spriteNum == 2) {image = attackUp2;}
                    }
                    break;
                case "down":
                    if (attacking == false) {
                        if (spriteNum == 1) {image = down1;}
                        if (spriteNum == 2) {image = down2;}
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {image = attackDown1;}
                        if (spriteNum == 2) {image = attackDown2;}
                    }
                    break;
                case "left":
                    if (attacking == false) {
                        if (spriteNum == 1) {image = left1;}
                        if (spriteNum == 2) {image = left2;}
                    }
                    if (attacking == true) {
                        tempScreenX -= left1.getWidth();
                        if (spriteNum == 1) {image = attackLeft1;}
                        if (spriteNum == 2) {image = attackLeft2;}
                    }
                    break;
                case "right":
                    if (attacking == false) {
                        if (spriteNum == 1) {image = right1;}
                        if (spriteNum == 2) {image = right2;}
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {image = attackRight1;}
                        if (spriteNum == 2) {image = attackRight2;}
                    }
                    break;
            }

            if (inviceble == true) {
                hpBar = true;
                hpBarCounter = 0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
            }

            if (dying == true) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        }

    }

    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pathFinder.search() == true) {

            //Next svetX i svetY
            int nextX = gp.pathFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pathFinder.pathList.get(0).row * gp.tileSize;

            //Pozicija entiteta
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                //levo ili desno
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                //gore ili levo
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                //gore ili desno
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                //dole ili levo
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                //dole ili desno
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            }
            
//            int nextCol = gp.pathFinder.pathList.get(0).col;
//            int nextRow = gp.pathFinder.pathList.get(0).row;
//            if(nextCol == goalCol && nextRow == goalRow){
//                onPath = false;
//            }
        }

    }

    public int getDetected(Entity entity, Entity target[][], String targetName) {

        int index = 999;

        int nextWorldX = entity.getLeftX();
        int nextWorldY = entity.getTopY();

        switch (entity.direction) {
            case "up":
                nextWorldY = entity.getTopY() - 1;
                break;
            case "down":
                nextWorldY = entity.getBottomY() + 1;
                break;
            case "left":
                nextWorldX = entity.getLeftX() - 1;
                break;
            case "right":
                nextWorldX = entity.getRightX() + 1;
                break;
        }

        int col = nextWorldX / gp.tileSize;
        int row = nextWorldY / gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col
                        && target[gp.currentMap][i].getRow() == row
                        && target[gp.currentMap][i].name.equals(targetName)) {

                    index = i;
                    break;
                }
            }

        }
        return index;

    }

    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;

        if (dyingCounter <= 5) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0F));
        }
        if (dyingCounter > 5 && dyingCounter <= 10) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        }
        if (dyingCounter > 10 && dyingCounter <= 15) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0F));
        }
        if (dyingCounter > 15 && dyingCounter <= 20) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        }
        if (dyingCounter > 20 && dyingCounter <= 25) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0F));
        }
        if (dyingCounter > 25 && dyingCounter <= 30) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        }
        if (dyingCounter > 30 && dyingCounter <= 35) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        }
        if (dyingCounter > 35 && dyingCounter <= 40) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0F));
        }
        if (dyingCounter > 40) {
            dying = false;
            alive = false;
        }
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = tool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}