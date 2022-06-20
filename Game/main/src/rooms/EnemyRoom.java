package rooms;


import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import enemies.Enemy;
import enemies.EnemyManager;
import enemies.Skunk;
import enemies.Wolf;
import items.Item;
import items.ItemManager;
import objects.Bridge;
import objects.Bullet;
import objects.Entrance;
import objects.Gas;
import objects.Land;
import objects.TreasureEntrance;
import objects.Water;
import util.*;
/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 */ 
public class EnemyRoom extends Room {
	
	private File texture;
	private CopyOnWriteArrayList<Enemy> EnemiesList  = new CopyOnWriteArrayList<Enemy>();
	private CopyOnWriteArrayList<Bullet> BulletList  = new CopyOnWriteArrayList<Bullet>();
	private CopyOnWriteArrayList<Gas> GasList  = new CopyOnWriteArrayList<Gas>();
	private int difficulty;
	private int maxEnemies;
	private int totalEnemies;
	private boolean disabled = false;
	private HashMap<String, Bridge> bridges = new HashMap<>();
	private Water water = new Water();
	private String type = "ENEMY";
	private Land land = new Land(700, 700, 50, 50, this.type);
	private Entrance entrance;
	private TreasureEntrance treasureEntrance;
	private ItemManager itemManager;
	private ArrayList<Item> items = new ArrayList<Item>();
	private EnemyManager enemyManager = new EnemyManager();
	
    public EnemyRoom(int difficulty, ItemManager itemManager) { 
    	this.texture = new File("res/island.png"); //Randomize
    	 
    	//Set Difficulty
    	this.difficulty = difficulty;
    	
    	this.itemManager = itemManager;
    	
    	//Setup enemies
    	setupRoom(difficulty);
   
	}
    
    public File getTexture() {
    	return texture;
    }
    
    public void generateEnemies(Player player, Rooms rooms) {
    	for(int i = 0; i < this.maxEnemies; i++) {
    		this.EnemiesList.add(this.enemyManager.getRandom());
    		this.EnemiesList.get(this.EnemiesList.size()-1).spawn(this, player, rooms);
    	}
    }
    
    public EnemyManager getEnemyManager() {
    	return this.enemyManager;
    }
    
    public void generateBridges(Rooms rooms, int currentRoomIndex) {
    	if(rooms.canMoveLeft(currentRoomIndex)) {
    		this.bridges.put("WEST", new Bridge(0, 417, "WEST"));
    	}
    	
    	if(rooms.canMoveRight(currentRoomIndex)) {
    		this.bridges.put("EAST", new Bridge(651, 417, "EAST"));
    	}
    	
    	if(rooms.canMoveUp(currentRoomIndex)) {
    		this.bridges.put("NORTH", new Bridge(417, 0, "NORTH"));
    	}
    	
    	if(rooms.canMoveDown(currentRoomIndex)) {
    		this.bridges.put("SOUTH", new Bridge(417, 651, "SOUTH"));
    	}
    }
    
    public CopyOnWriteArrayList<Enemy> getEnemiesList(){
    	return EnemiesList;
    }
    
    public Boolean getDisabled() {
    	return disabled;
    }
    
    public void setDisabled(boolean disabled) {
    	this.disabled = disabled;
    }
    
    public int getDifficulty() {
    	return difficulty;
    }
    
    public void setDifficulty(int difficulty) {
    	this.difficulty = difficulty;
    }
    
    public void createPlayerBullet(Player player) {
    	PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
    	BulletList.add(new Bullet(player, player.getBody().getCentre(), new Point3f(x, y, 0)));
    }
    
    public void createEnemyBullet(Player player, Enemy enemy) {
		int playerX = (int) player.getBody().getCentre().getX() + (int)player.getBody().getWidth()/2;
		int playerY = (int) player.getBody().getCentre().getY() + (int)player.getBody().getHeight()/2;
		
		int enemyX = (int) enemy.getBody().getCentre().getX() + (int)enemy.getBody().getWidth()/2;
		int enemyY = (int) enemy.getBody().getCentre().getY() + (int)enemy.getBody().getHeight()/2;
		
    	BulletList.add(new Bullet(player, new Point3f(enemyX, enemyY, 0), new Point3f(playerX, playerY, 0)));
    }
    
    public CopyOnWriteArrayList<Bullet> getBulletList(){
    	return BulletList;
    }
    
    public CopyOnWriteArrayList<Gas> getGasList(){
    	return GasList;
    }
    
    public HashMap<String, Bridge> getBridges(){
    	return this.bridges;
    }
    
    public Water getWater() {
    	return this.water;
    }
    
    public Land getLand() {
    	return this.land;
    }
    
    public int getMaxEnemies() {
    	return this.maxEnemies;
    }
    
    public void spawnRandomItem(int x, int y) {
    	Item temp = this.itemManager.getRandom();
    	temp.setXY(x, y);
    	items.add(temp);
    }
    
    private void setupRoom(int difficulty) {
    	switch(difficulty) {
    		case 0:
    			this.maxEnemies = 0;
    			this.totalEnemies = 0;
    			this.spawnRandomItem(150, 150);
    			break;
    		case 1:
    			this.maxEnemies = 2;
    			this.totalEnemies = (int)((Math.random() * 5) + this.maxEnemies);
    			break;
    		case 2:
    			this.maxEnemies = 3;
    			this.totalEnemies = (int)((Math.random() * 5) + this.maxEnemies);
    			break;
    		case 3:
    			this.maxEnemies = 4;
    			this.totalEnemies = (int)((Math.random() * 5) + this.maxEnemies);
    			break;
    	
    	}
    }
    
    public int getTotalEnemies() {
    	return this.totalEnemies;
    }
    
    public void setTotalEnemies(int total) {
    	this.totalEnemies = total;
    }
    
    public boolean hasWestBridge() {
    	if(this.bridges.containsKey("WEST")) {
    		return true;
    	}
    	return false;
    }
    
    public boolean hasEastBridge() {
    	if(this.bridges.containsKey("EAST")) {
    		return true;
    	}
    	return false;
    }
    
    public boolean hasNorthBridge() {
    	if(this.bridges.containsKey("NORTH")) {
    		return true;
    	}
    	return false;
    }
    
    public boolean hasSouthBridge() {
    	if(this.bridges.containsKey("SOUTH")) {
    		return true;
    	}
    	return false;
    }
    
    public String getType() {
    	return this.type;
    }
    
    public Rectangle getExit() {
    	return new Rectangle(0, 0, 0, 0);
    }
    
    public void addEntrance() {
    	this.entrance = new Entrance();
    }
    
    public Entrance getEntrance() {
    	return this.entrance;
    }
    
    public boolean hasEntrance() {
    	if(this.entrance != null) {
    		return true;
    	}
    	
    	return false;
    }
    
    public void addTreasureEntrance() {
    	this.treasureEntrance = new TreasureEntrance();
    }
    
    public TreasureEntrance getTreasureEntrance() {
    	return this.treasureEntrance;
    }
    
    public boolean hasTreasureEntrance() {
    	if(this.treasureEntrance != null) {
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean hasItem() {
    	if(this.items.size() != 0) {
    		return true;
    	}
    	return false;
    }
    
    public Item getItem(int index) {
    	return this.items.get(index);
    }
    
    public void removeItem(int index) {
    	this.items.remove(index);
    }
    
    public ArrayList<Item> getItems(){
    	return this.items;
    }
    
    public ItemManager getItemManager() {
    	return this.itemManager;
    }
    
    public void spawnItem(String item, int x, int y) {
    	Item temp = this.itemManager.get(item);
    	temp.setXY(x, y);
    	items.add(temp);
    }

	@Override
	public void createEnemyGas(Player player, Enemy enemy) {
    	GasList.add(new Gas((int)enemy.getBody().getCentre().getX(), (int)enemy.getBody().getCentre().getY()));	
	}

	@Override
	public Rectangle getStore() {
		// TODO Auto-generated method stub
		return null;
	}
}

/*
 *  Game Object 
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::c:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::clc::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::lol:;::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::;;cool:;::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::codk0Oxolc:::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::coxk0XNWMMWWWNK0kxdolc::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::loxO0XWMMMMMMMWWWMMMMMMWWNK0Oxdlc::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::cldkOKNWMMMMMMMMMMMMMWWMMMMMMMMMMMMMWNX0Okdolc:::::::::::::::::::::::::::::::::
::::::::::::::::codk0KNWMMMMMMMMMMMMMMMMMMWMMMMMMMMMMMMMMMMMMMMWWXKOkxo:::::::::::::::::::::::::::::
::::::::::::;cdOXNWMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMMMMMMMMMMMMMNKOdc::::::::::::::::::::::::::::
:::::::::::::cxKXXNWWMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMMMMMMMMMWX0kdolc:::::::::::::::::::::::::::::
::::::::::::::d0000KKXNNWMMMMMMMMMMMMMMMMWWMMMMMMMMMMMMMMMMWNKOxolllllc:::::::::::::::::::::::::::::
::::::::::::::oO00000000KXXNWWMMMMMMMMMMMMWMMMMMMMMMMMMMWX0kdollllllllc:::::::::::::::::::::::::::::
::::::::::::::lk00000O000000KKXNWWMMMMMMMWWWMMMMMMMMWNKOxollllllllllll::::::::::::::::::::::::::::::
::::::::::::::cx0000000000O000000KXXNWMMMWWWMMMMWXK0kdlllllllllllllllc::::::::::::::::::::::::::::::
:::::::::::::::dO00000000000000000000KKXNNNWWNKOxolllllllllllllllllllc::::::::::::::::::::::::::::::
:::::::::::::::lO000000000000000OOOO0000000Kkdlllllllllllllllllllllllc::::::::::::::::::::::::::::::
:::::::::::::::ck00000000000000000OOOOOOOOkxollllllllllllllllllllllll:::::::::::::::::::::::::::::::
:::::::::::::;;cx00000000000000000000OOOOOOxocllllllllllllllllllllllc:;;;;;;;;;;::::::::::::::::::::
;;;;;;;;;;;;;;;:oO00000000000000000OOOO0000kdllllcclllllllllllllllllc:;;;;;;;;;;;;;;;;;;::::::::::::
;;;;;;;;;;;;;;;:lO00000000000000OOO00000000Oolllllllllllllllllllllllc:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;ck0000000000OOO000000000000kolllllllllllllllllllllll:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;:dOO0000OOO0000000000000000kolllllllllllllllllllllllc:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;:lxkOOOO0000000000000000000kolllllllllllllllllllllooool::;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;::;;::cokOkkO00000000000000000000kolllllllllllllllllllllccccllcc::::;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;:;;:;::ccllodxkO00000000000000000000kolllllllllllllllllllcc:;;;;:::::;;:;;;;;;;;;;;;;;;;;;;;;
;;;;;;;::::::::::;;:ldkO0000000000000000000kolllllllllllllllllc::;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;::;;:::;;;;;:;::ldkO0000000000000000kollllllllllllllcc::;;;;:;;:;;;;;:;;;:;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;:;;;;:cldkO0000000000000kollllllllllllc:::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;::ldkO0000000000kolllllllllcc::;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;::;:;;::ldkO0000000kolllllllc::::;;;;:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;::ldkO0000kolllcc:::;;;;;;;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;;;;;:ldkO0kolcc::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;;;::lodl:::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;::;:::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;::;;;;;;;;;;;:::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
*/