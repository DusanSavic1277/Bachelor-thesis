package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import object.OBJ_Key;
import object.OBJ_Sword;
import rs.ac.singidunum.EntityType;
import rs.ac.singidunum.GamePanel;
import rs.ac.singidunum.GameState;
import rs.ac.singidunum.KeyHandler;

public class Player extends Entity{
    
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;
    
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;
        
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        maxLife = 1;
        life = maxLife;
        speed = 4;
        direction = "down";
        
        level = 1;
        strength = 1;
        exp = 0;
        nextLevelExp = 10;
        currentWeapon = new OBJ_Sword(gp);
        defense = 1;
        maxAttack =  getAttack();
        attack = maxAttack;
    }
    
    public void resetAfterDeath() {
        
        gp.currentMap = 0;
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        life = maxLife;
        attack = getAttack();
        direction = "down";
        inviceble = false;
        invicebleCounter = 0;
        potionCounter = 0;
        usePotion = false;
        
    }
    
    public void setItems() {
        inventory.add(currentWeapon);
    }
    
    @Override
    public int getAttack() {

        attackArea = currentWeapon.attackArea;

        if (usePotion == true && currentWeapon != null) {
            return maxAttack = (strength + strengthBonus) * currentWeapon.attackValue;

        } 
        else{
            return maxAttack = strength * currentWeapon.attackValue;
        }
            
    }
    
    public void getPlayerImage() {
        
        up1 = setup("/playerImg/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/playerImg/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/playerImg/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/playerImg/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/playerImg/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/playerImg/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/playerImg/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/playerImg/boy_right_2", gp.tileSize, gp.tileSize);
        
    }
    
    public void getPlayerAttackImage() {

        if (currentWeapon.type == EntityType.SWORAD) {
            attackUp1 = setup("/playerImg/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/playerImg/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/playerImg/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/playerImg/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/playerImg/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/playerImg/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/playerImg/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/playerImg/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == EntityType.AXE) {
            attackUp1 = setup("/playerImg/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/playerImg/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/playerImg/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/playerImg/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/playerImg/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/playerImg/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/playerImg/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/playerImg/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }
        
        if (currentWeapon.type == EntityType.PICAXE) {
            attackUp1 = setup("/playerImg/boy_pick_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/playerImg/boy_pick_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/playerImg/boy_pick_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/playerImg/boy_pick_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/playerImg/boy_pick_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/playerImg/boy_pick_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/playerImg/boy_pick_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/playerImg/boy_pick_right_2", gp.tileSize * 2, gp.tileSize);
        }

    }

    @Override
    public void update() {

        if (attacking == true) {
            attacking();
        } else if (keyH.up == true || keyH.down == true || keyH.left == true
                || keyH.right == true) {

            if (keyH.up == true) {
                direction = "up";
            } else if (keyH.down == true) {
                direction = "down";
            } else if (keyH.left == true) {
                direction = "left";
            } else if (keyH.right == true) {
                direction = "right";
            }

            collisionOn = false;
            gp.check.checkTile(this);

            int objIndex = gp.check.checkObj(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.check.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.check.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            
            gp.check.checkEntity(this, gp.iTile);

            gp.event.checkEvent();

            keyH.enter = false;

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
        } else {
            spriteNum = 1;
        }

        if (inviceble) {
            invicebleCounter++;
            if (invicebleCounter > 60) {
                inviceble = false;
                invicebleCounter = 0;
            }
        }

        if (usePotion) {
            potionCounter++;
            if (potionCounter > 60 * 60) {
                strengthBonus = 0;
                usePotion = false;
                potionCounter = 0;
                attack = getAttack();
                gp.ui.showMessage("Potion effect expired.");
            }
            else if(life <= 0){
                usePotion = false;
            }
        }

        if (life > maxLife) {
            life = maxLife;
        }
        
        if(life <= 0){
            gp.gameState = GameState.GAME_OVER;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(4);
        }
    }
    
    @Override
    public void attacking(){
        
        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
            
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            switch(direction){
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
            
            int monsterIndex = gp.check.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);
            
            int iTileIndex = gp.check.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
        
    }
    
    public void pickUpObject(int i) {

        if (i != 999) {

            Entity obj = gp.obj[gp.currentMap][i];

            if (obj != null) {

                //Oznacava da je objekat pokupljen
                gp.objectPickedUp[gp.currentMap][i] = true;

                if (gp.obj[gp.currentMap][i].type == EntityType.PICK_UP) {
                    gp.obj[gp.currentMap][i].use(this);
                    gp.obj[gp.currentMap][i] = null;

                } 
                else if(gp.obj[gp.currentMap][i].type == EntityType.OBSTACLE){
                    if(keyH.enter == true){
                        gp.obj[gp.currentMap][i].interact();
                    }
                }
                else {

                    if (canObtainItem(gp.obj[gp.currentMap][i]) == true) {
                        
                        gp.playSE(1);
                        gp.obj[gp.currentMap][i] = null;

                    } else {
                        gp.gameState = GameState.DIALOGUE;
                        dialogues[1][0] = "Inventory is full!";
                        startDialogue(this, 1);
                    }
                }
            }
        }
    }
    
    
    public void interactNPC(int i) {

        if (keyH.enter == true) {
            if (i != 999) {
                gp.npc[gp.currentMap][i].speak();
//                checkLevelUp();
            } else {
                attacking = true;
            }
        }
    }
    
    public void contactMonster(int i) {

        if (i != 999) {
            for (Entity entity : gp.monster[1]) {
                if (solidArea.intersects(gp.monster[gp.currentMap][i].solidArea)) {
                    if (inviceble == false) {
                        gp.playSE(6);

                        int damage = gp.monster[gp.currentMap][i].attack - defense;
                        if (damage < 0) {
                            damage = 0;
                        }

                        life -= damage;
                        inviceble = true;
                        invicebleCounter = 0;
                    } else if (life == 0) {}
                }
            }
        }

    }
    
    public void damageMonster(int i) {

        if (i != 999) {
            if (gp.monster[gp.currentMap][i].inviceble == false) {
                
                gp.playSE(7);
                
                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if(damage < 0){
                    damage = 0;
                }
                
                gp.monster[gp.currentMap][i].life -= damage;
                gp.monster[gp.currentMap][i].inviceble = true;

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    inviceble = true;
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }

    }
    
    public void damageInteractiveTile(int i){
        
        if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true && 
                gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].inviceble == false){
            gp.playSE(12);
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].inviceble = true;
            
            if(gp.iTile[gp.currentMap][i].life == 0){
                gp.iTile[gp.currentMap][i] = null;
            }
        }
        
    }
    
    public void checkLevelUp() {

        if (exp >= nextLevelExp) {
            gp.playSE(11);
            level++;
            nextLevelExp *= 2;
            maxLife += 2;

            strength++; //Povecavanje vrednosti

            attack = getAttack(); //Racuna bonus ako je napitak iskoriscen
            
            gp.gameState = GameState.DIALOGUE;
            dialogues[0][0] = "You are level " + level + " now!";
            startDialogue(this, 0);
        }

    }
    
    public void selectItem(){
        
        int itemIndex = gp.ui.getItemIndexOnSlot();
        
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            
            if(selectedItem.type == EntityType.SWORAD || selectedItem.type == EntityType.AXE || selectedItem.type == EntityType.PICAXE){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == EntityType.CONSUMABLE){
                
                if (selectedItem.use(this) == true) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    }
                    else{
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
        
    }
    
    public int stackableItem(String itemName) {

        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }

        }

        return itemIndex;
    }
    
    public boolean canObtainItem(Entity item) {

        boolean canObtain = false;

        if (item.stackable == true) {
            int index = stackableItem(item.name);

            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } 
            else {
                if (inventory.size() != inventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } 
        else {
            if (inventory.size() != inventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }

    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if(attacking == true){
                    tempScreenY -= gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
                
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                }
                if(attacking == true){
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                }
                if(attacking == true){
                    tempScreenX -= gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                }
                if(attacking == true){
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break; 
        }
        
        if(drawing == true){
            g2.drawImage(image, tempScreenX, tempScreenY, null);
        }
    }
}