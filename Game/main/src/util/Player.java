package util;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

import items.Item;
import rooms.Rooms;

public class Player implements Serializable {
	
	private int healthBonus = 0;
	private double luckBonus = 0;
	private int fireRateBonus = 0;
	private int speedBonus = 0;
	private int coinBonus = 0;
	
	private GameObject body;
	private Rectangle collider;
	private int health = 100 + healthBonus;
	private double luck = 0 + luckBonus;
	private long lastBulletTime = System.currentTimeMillis();
	private int fireRate = 500 + fireRateBonus;
	private String location = "Land";
	private int speed = 4 + speedBonus;
	int size = 0;
	private int coins = 0;
	private boolean keyOwned = false;
	private Item treasureItem = null;
	private boolean reset = false;
	private int storedCoins = 0;
	private long lastPurchaseTime = System.currentTimeMillis();
	private Sounds sounds = new Sounds();
	private ArrayList<String> treasureItems = new ArrayList<String>();
	private boolean canSave = false;
	private String[] hats = {"res/FoxSanta", "res/FoxWizard", "res/FoxSombrero", "res/FoxTopHat", "res/FoxRice", "res/FoxParty", "res/Fox"};
	private String texture = "res/Fox";
	private String direction = "RIGHT";
	
	public Player() {
		this.body = new GameObject(this.texture + ".png", 66, 90, new Point3f(350, 550, 0));
		this.setCollider();
	}
	
	public boolean getReset() {
		return this.reset;
	}
	
	public void setReset(boolean value) {
		this.reset = value;
	}
	
	public void resetPlayer() {
		this.coins = 0;
		this.treasureItem = null;
		this.health = 100 + this.healthBonus;
		this.setLuck(0 + this.luckBonus);
		this.fireRate = 500 + this.fireRateBonus;
		this.speed = 4 + this.speedBonus;
		this.keyOwned = false;
		this.getBody().getCentre().setX(350);
		this.getBody().getCentre().setY(550);
		this.reset = false;
	}
	
	public String[] getHats() {
		return this.hats;
	}
	
	public int getHealthBonus() {
		return this.healthBonus;
	}
	
	public double getLuckBonus() {
		return this.luckBonus;
	}
	
	public int getFireRateBonus() {
		return this.fireRateBonus;
	}
	
	public int getSpeedBonus() {
		return this.speedBonus;
	}
	
	public int getCoinBonus() {
		return this.coinBonus;
	}
	
	public void setHealthBonus(int healthBonus) {
		this.healthBonus = healthBonus;
	}
	
	public void setLuckBonus(double luckBonus) {
		this.luckBonus = luckBonus;
	}
	
	public void setFireRateBonus(int fireRateBonus) {
		this.fireRateBonus = fireRateBonus;
	}
	
	public void setSpeedBonus(int speedBonus) {
		this.speedBonus = speedBonus;
	}
	
	public void setCoinBonus(int coinBonus) {
		this.coinBonus = coinBonus;
	}
	
	public int getStoredCoins() {
		return this.storedCoins;
	}
	
	public void setStoredCoins(int storedCoins) {
		this.storedCoins = storedCoins;
	}
	
	public long getLastPurchaseTime() {
		return this.lastPurchaseTime;
	}
	
	public void setLastPurchaseTime(long time) {
		this.lastPurchaseTime = time;
	}
	
	public ArrayList<String> getTreasureItems(){
		return this.treasureItems;
	}
	
	public void addTreasureItem(String item) {
		this.treasureItems.add(item);
	}
	
	public boolean getCanSave() {
		return this.canSave;
	}
	
	public void setCanSave(boolean canSave) {
		this.canSave = canSave;
	}
	
	public void setCollider() {
		this.collider = new Rectangle((int)this.body.getCentre().getX()+22, (int)this.body.getCentre().getY()+40, this.body.getWidth()-22, (this.body.getHeight()/2)+10);
	}
	
	public String getTexture() {
		return this.texture;
	}
	
	public void setTexture(String texture) {
		this.texture = texture;
		if(this.direction.equals("LEFT")) {
			this.body.setTexture(this.texture + "Left.png");
		} else {
			this.body.setTexture(this.texture + ".png");
		}
	}
	
	public void move(String direction, Rooms rooms) {
		
		switch(direction) {
			case "UP" :
				this.body.getCentre().ApplyVector(new Vector3f(0, this.speed, 0));
				this.setCollider();
				if(!(checkBoundaries(this, rooms))) {
					this.body.getCentre().ApplyVector( new Vector3f(0, -this.speed, 0));
					this.setCollider();
				}
				break;
			case "DOWN":
				this.body.getCentre().ApplyVector(new Vector3f(0, -this.speed, 0));	
				this.setCollider();
				if(!(checkBoundaries(this, rooms))) {
					this.body.getCentre().ApplyVector( new Vector3f(0, this.speed, 0));
					this.setCollider();
				}
				break;
			case "LEFT":
				this.direction = "LEFT";
				this.body.setTexture(this.texture + "Left.png");
				this.body.getCentre().ApplyVector(new Vector3f(-this.speed, 0, 0));
				this.setCollider();
				if(!(checkBoundaries(this, rooms))) {
					body.getCentre().ApplyVector( new Vector3f(this.speed, 0, 0));
					this.setCollider();
				}
				break;
			case "RIGHT":
				this.direction = "RIGHT";
				this.body.setTexture(this.texture + ".png");
				this.body.getCentre().ApplyVector(new Vector3f(this.speed, 0, 0));
				this.setCollider();
				if(!(checkBoundaries(this, rooms))) {
					this.body.getCentre().ApplyVector( new Vector3f(-this.speed, 0, 0));
					this.setCollider();
				}
				break;
		}
	
	}
	
	public void attack() {
		//TODO
	}
	
	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider(){
		return this.collider;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setLastBulletTime() {
		this.lastBulletTime = System.currentTimeMillis();
	}
	
	public long getLastBulletTime() {
		return this.lastBulletTime;
	}
	
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	
	public int getFireRate() {
		return this.fireRate;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public int getCoins() {
		return this.coins;
	}
	
	public void setCoins(int coins) {
		this.coins = coins + this.coinBonus;
	}
	
	public boolean canShoot() {
		if((System.currentTimeMillis() - this.lastBulletTime) > this.fireRate) {
			return true;
		}
		return false;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setHasKey() {
		this.keyOwned = true;
	}
	
	public boolean hasKey() {
		return this.keyOwned;
	}
	
	public void giveTreasure(Item treasure) {
		this.treasureItem = treasure;
	}
	
	public boolean hasTreasure() {
		if(this.treasureItem == null) {
			return false;
		}
		
		return true;
	}
	
	public double getLuck() {
		return this.luck;
	}
	
	public void setLuck(double luck) {
		this.luck = luck;
		if(this.luck > 1) {
			this.luck = 1;
		}
	}
	
	private Boolean checkBoundaries(Player test, Rooms rooms) {
		
		if(rooms.getCurrentRoom().getType().equals("START")) {
			//On Start Room Exit
			if(test.getCollider().intersects(rooms.getCurrentRoom().getExit())) {
				test.getBody().getCentre().setX(220);
				test.getBody().getCentre().setY(450);
				rooms.setCurrentRoom(rooms.getRoomArray()[rooms.getCurrentRoomIndex()]);
				return true;
			}
			
			//On Start Room Store
			if(test.getCollider().intersects(rooms.getCurrentRoom().getStore())) {
				test.getBody().getCentre().setX(680);
				test.getBody().getCentre().setY(350);
				rooms.setCurrentRoom(rooms.getDenStore());
				return true;
			}
			
			if((test.getBody().getCentre().getX() > 690) || (test.getBody().getCentre().getX() < 40)) {
				return false;
			}
			
			if((test.getBody().getCentre().getY() > 660) || (test.getBody().getCentre().getY() < 5)) {
				return false;
			}
		}
		
		//On Treasure Room Exit
		if(rooms.getCurrentRoom().getType().equals("TREASURE")) {
			if(test.getCollider().intersects(rooms.getCurrentRoom().getExit())) {
				test.getBody().getCentre().setX(220);
				test.getBody().getCentre().setY(450);
				rooms.setCurrentRoom(rooms.getTreasureEntranceRoom());
				return true;
			}
			
			if((test.getBody().getCentre().getX() > 690) || (test.getBody().getCentre().getX() < 40)) {
				return false;
			}
			
			if((test.getBody().getCentre().getY() > 660) || (test.getBody().getCentre().getY() < 5)) {
				return false;
			}
		}
		
		//On DenStore Exit
		if(rooms.getCurrentRoom().getType().equals("STORE")) {
			if(test.getCollider().intersects(rooms.getCurrentRoom().getExit())) {
				test.getBody().getCentre().setX(70);
				test.getBody().getCentre().setY(350);
				rooms.setCurrentRoom(rooms.getStartRoom());
				return true;
			}
			
			if((test.getBody().getCentre().getX() > 690) || (test.getBody().getCentre().getX() < 40)) {
				return false;
			}
			
			if((test.getBody().getCentre().getY() > 660) || (test.getBody().getCentre().getY() < 5)) {
				return false;
			}
		}
		
		if(rooms.getCurrentRoom().getType().equals("ENEMY")) {
			//Enemy Collision
			for(int i = 0; i < rooms.getCurrentRoom().getEnemiesList().size(); i++) {
				if(test.getCollider().intersects(rooms.getCurrentRoom().getEnemiesList().get(i).getCollider())) {
					return false;
				}
			}
		}
		
		//On Entrance
		if(rooms.getCurrentRoom().hasEntrance()) {
			if(rooms.getCurrentRoom().getEntrance().getCollider().contains(this.collider)) {
				rooms.setCurrentRoom(rooms.getStartRoom());
				this.canSave = true;
				test.getBody().getCentre().setX(380);
				test.getBody().getCentre().setY(75);
				//Store Coins
				this.storedCoins += this.coins;
				this.coins = 0;
				//If player has treasure, add to start room
				if(this.hasTreasure()) {
					if(!this.treasureItem.getName().equals("Large Coin")) {
						rooms.getStartRoom().addTreasure(this.treasureItem);
						this.addTreasureItem(this.treasureItem.getName());
						sounds.playSound("success");
					}
					this.treasureItem.applyBonus(this);
					this.setReset(true);
				}
				return true;
			}
		}
		
		//On Treasure Room Entrance
		if(rooms.getCurrentRoom().hasTreasureEntrance()) {
			if(rooms.getCurrentRoom().getTreasureEntrance().getCollider().contains(this.collider)) {
				rooms.setCurrentRoom(rooms.getTreasureRoom());
				test.getBody().getCentre().setX(380);
				test.getBody().getCentre().setY(75);
				return true;
			}
		}
		
		//On Land
		if(rooms.getCurrentRoom().getLand().getCollider().contains(test.getCollider())) {
			this.location = "Land";
			return true;
		}
		
		//Edge of Start Room/Treasure Room
		if(rooms.getCurrentRoom().getType().equals("START") || rooms.getCurrentRoom().getType().equals("TREASURE")) {
			return false;
		}
		
		if(!rooms.getCurrentRoom().getType().equals("START") || !rooms.getCurrentRoom().getType().equals("TREASURE")) {
		
			//Check if Player is on North Bridge
			if(rooms.getCurrentRoom().hasNorthBridge()) {
				if(checkBridge(test, rooms, "NORTH")) {
					return true;
				}
			}
			
			//Check if Player is on South Bridge
			if(rooms.getCurrentRoom().hasSouthBridge()) {
				if(checkBridge(test, rooms, "SOUTH")) {
					return true;
				};
			}
			
			//Check if Player is on West Bridge
			if(rooms.getCurrentRoom().hasWestBridge()) {
				if(checkBridge(test, rooms, "WEST")) {
					return true;
				}
			}
			
			//Check if Player is on East Bridge
			if(rooms.getCurrentRoom().hasEastBridge()) {
				if(checkBridge(test, rooms, "EAST")) {
					return true;
				};
			}
			
			//Check if Player is at edge of Room
			if(!(rooms.getCurrentRoom().getWater().getCollider().contains(test.getCollider()))){
				checkNextRoom(test, rooms);
				return true;
			}
			
			if((test.getBody().getCentre().getY()+30 > 700)) {
				checkNextRoom(test, rooms);
			}
			
			
		}
		
		return false;
	}
	
	private void checkNextRoom(Player test, Rooms rooms) {
		
		if(this.location.equals("NORTH BRIDGE")) {
			test.getBody().getCentre().setY(780-this.getBody().getHeight());
			this.location = "SOUTH BRIDGE";
			rooms.moveUp(rooms.getCurrentRoomIndex());
		} else if(this.location.equals("SOUTH BRIDGE")) {
			test.getBody().getCentre().setY(2);
			this.location = "NORTH BRIDGE";
			rooms.moveDown(rooms.getCurrentRoomIndex());
		} else if(this.location.equals("EAST BRIDGE")) {
			test.getBody().getCentre().setX(2);
			this.location = "WEST BRIDGE";
			rooms.moveRight(rooms.getCurrentRoomIndex());
		} else if(this.location.equals("WEST BRIDGE")) {
			test.getBody().getCentre().setX(800-this.getBody().getWidth());
			this.location = "EAST BRIDGE";
			rooms.moveLeft(rooms.getCurrentRoomIndex());
		}
	}
	
	private boolean checkBridge(Player player, Rooms rooms, String bridge) {
		if(rooms.getCurrentRoom().getBridges().get(bridge).getCollider().contains(player.getCollider())){
			this.location = bridge + " BRIDGE";
			return true;
		}
		return false;
	}

}
