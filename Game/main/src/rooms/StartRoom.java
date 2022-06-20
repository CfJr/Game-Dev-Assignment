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
import items.Item;
import items.ItemManager;
import objects.Bridge;
import objects.Bullet;
import objects.Entrance;
import objects.Gas;
import objects.Land;
import objects.LoadButton;
import objects.StartButton;
import objects.TreasureEntrance;
import objects.TreasureSpot;
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
public class StartRoom extends Room {
	
	private File texture;
	private CopyOnWriteArrayList<Enemy> EnemiesList  = new CopyOnWriteArrayList<Enemy>();
	private CopyOnWriteArrayList<Bullet> BulletList  = new CopyOnWriteArrayList<Bullet>();
	private CopyOnWriteArrayList<Gas> GasList  = new CopyOnWriteArrayList<Gas>();
	private int difficulty;
	private int maxEnemies = 0;
	private int totalEnemies = 0;
	private boolean disabled = false;
	private HashMap<String, Bridge> bridges = new HashMap<>();
	private Water water = new Water();
	private String type = "START";
	private Land land = new Land(800, 800, 0, 0, this.type);
	private Rectangle exit = new Rectangle(300, 0, 200, 50);
	private Rectangle store = new Rectangle(35, 300, 50, 200);
	private ArrayList<Item> items = new ArrayList<Item>();
	private ItemManager itemManager;
	private EnemyManager enemyManager = new EnemyManager();
	private TreasureEntrance treasureEntrance;
	private ArrayList<TreasureSpot> treasureSpots = new ArrayList<TreasureSpot>();
	private HashMap<String, Item> treasureItems = new HashMap<String, Item>();
	private StartButton startButton = new StartButton(300, 200);
	private LoadButton loadButton = new LoadButton(300, 350);

    public StartRoom(ItemManager itemManager) { 
    	this.texture = new File("res/Den.png"); //Randomize
    	this.itemManager = itemManager;
    	
    	this.generateTreasureSpots();
   
	}
    
    public File getTexture() {
    	return texture;
    }
    
    public void generateEnemies(Player player, Rooms rooms) {
    	//TODO
    }
    
    public void generateTreasureSpots() {
    	treasureSpots.add(new TreasureSpot(270, 330));
    	treasureSpots.add(new TreasureSpot(370, 330));
    	treasureSpots.add(new TreasureSpot(470, 330));
    	treasureSpots.add(new TreasureSpot(270, 430));
    	treasureSpots.add(new TreasureSpot(370, 430));
    	treasureSpots.add(new TreasureSpot(470, 430));
    }
    
    public ArrayList<TreasureSpot> getTreasureSpots(){
    	return this.treasureSpots;
    }
    
    public EnemyManager getEnemyManager() {
    	return this.enemyManager;
    }
    
    public void generateBridges(Rooms rooms, int currentRoomIndex) {
    	return;
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
    
    public StartButton getStartButton() {
    	return this.startButton;
    }
    
    public LoadButton getLoadButton() {
    	return this.loadButton;
    }
    
    public void createPlayerBullet(Player player) {
    	PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
    	BulletList.add(new Bullet(player, player.getBody().getCentre(), new Point3f(x, y, 0)));
    }
    
    public void createEnemyBullet(Player player, Enemy enemy) {
		int x = (int) player.getBody().getCentre().getX() + (int)player.getBody().getWidth()/2;
		int y = (int) player.getBody().getCentre().getY() + (int)player.getBody().getHeight()/2;
    	BulletList.add(new Bullet(player, enemy.getBody().getCentre(), new Point3f(x, y, 0)));
    }
    
    public CopyOnWriteArrayList<Bullet> getBulletList(){
    	return BulletList;
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
    	return this.exit;
    }
    
    public void addEntrance() {
    	return;
    }
    
    public Entrance getEntrance() {
    	return new Entrance();
    }
    
    public boolean hasEntrance() {
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
    
    public void spawnRandomItem(int x, int y) {
    	Item temp = this.itemManager.getRandom();
    	temp.setXY(x, y);
    	items.add(temp);
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
    
    public void addTreasure(Item treasure) {
    	if(this.treasureItems.size() == 0) {
    		treasure.setXY(287, 350);
    	}
    	
    	if(this.treasureItems.size() == 1) {
    		treasure.setXY(387, 350);
    	}
    	
    	if(this.treasureItems.size() == 2) {
    		treasure.setXY(487, 350);
    	}
    	
    	if(this.treasureItems.size() == 3) {
    		treasure.setXY(287, 450);
    	}
    	
    	if(this.treasureItems.size() == 4) {
    		treasure.setXY(387, 450);
    	}
    	
    	if(this.treasureItems.size() == 5) {
    		treasure.setXY(487, 450);
    	}
    	
    	this.treasureItems.put(treasure.getName(), treasure);
    }
    
    public boolean hasTreasureItem(String treasureName) {
    	if(this.treasureItems.containsKey(treasureName)) {
    		return true;
    	}
    	
    	return false;
    }
    
    public ArrayList<Item> getTreasureItems(){
  
        return new ArrayList<>(this.treasureItems.values());
    }

	@Override
	public void createEnemyGas(Player player, Enemy enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CopyOnWriteArrayList<Gas> getGasList() {
		return GasList;
	}

	@Override
	public Rectangle getStore() {
		return this.store;
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