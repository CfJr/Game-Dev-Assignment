package rooms;

import java.awt.Rectangle;
import java.io.Serializable;

import items.ItemManager;
import util.Player;

public class Rooms implements Serializable{
	
	private Room[] roomArray;
	private int amount;
	private int width;
	private ItemManager itemManager = new ItemManager();
	private Room currentRoom;
	private StartRoom startRoom = new StartRoom(itemManager);
	private TreasureRoom treasureRoom;
	private boolean keySpawned;
	private boolean treasureOpen;
	private Room treasureEntranceRoom;
	private DenStore denStore = new DenStore(itemManager);
	
	public Rooms(int amount, Player player) {
		this.generateRooms(amount, player);
	}
	
	public void generateRooms(int amount, Player player) {
		this.roomArray = new EnemyRoom[amount];
		this.amount = amount;
		this.width = (int)(Math.random()*amount/2)+2;
		//this.width = 5;
		
		for(int i = 0; i < amount; i++) {
			//Add empty room for room outside of den
			if(i == 0) {
				this.roomArray[i] = new EnemyRoom(0, itemManager);
				this.roomArray[i].addEntrance();
			//Add normal enemy room
			} else {
				this.roomArray[i] = new EnemyRoom(1, itemManager);
			}
		}
		
		this.currentRoom = startRoom;
		//this.currentRoom = roomArray[0];
//		this.currentRoomIndex = 0;
		
		for(int i = 0; i < roomArray.length; i++) {
			roomArray[i].generateBridges(this, i);
			roomArray[i].generateEnemies(player, this);
		}
		
		this.treasureRoom = new TreasureRoom(itemManager, this.startRoom);
		this.keySpawned = false;
		this.treasureOpen = false;
		this.treasureEntranceRoom = null;
	}
	
	public Room[] getRoomArray() {
		return this.roomArray;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
	public void setCurrentRoom(Room room) {
		this.currentRoom.getBulletList().removeAll(this.currentRoom.getBulletList());
		this.currentRoom = room;
	}
	
	public int getCurrentRoomIndex() {
		int currentIndex = 0;
		for(int i = 0; i < this.roomArray.length; i++) {
			if(this.currentRoom == roomArray[i]) {
				currentIndex = i;
			}
		}
		return currentIndex;
		//return this.currentRoomIndex;
	}
	
	public Room get(int index) {
		return this.roomArray[index];
	}
	
	public Room getTreasureEntranceRoom() {
		return this.treasureEntranceRoom;
	}
	
	public void setTreasureEntranceRoom(Room treasureEntranceRoom) {
		this.treasureEntranceRoom = treasureEntranceRoom;
	}
	
	public boolean canMoveLeft(int index) {
		if((index > 0) && (index % this.width != 0)) {
			return true;
		}
		return false;
	}
	
	public boolean canMoveRight(int index) {
		if((index < this.roomArray.length-1) && ((index+1) % this.width != 0)) {
			return true;
		}
		return false;
	}
	
	public boolean canMoveUp(int index) {
		if(((index - this.width) > -1)) {
			return true;
		}
		return false;
	}
	
	public boolean canMoveDown(int index) {
		if(((index + this.width) < (this.roomArray.length))) {
			return true;
		}
		return false;
	}
	
	public void moveLeft(int index) {
		if(canMoveLeft(index)) {
			this.setCurrentRoom(this.roomArray[index-1]);
		}
	}
	
	public void moveRight(int index) {
		if(canMoveRight(index)) {
			this.setCurrentRoom(this.roomArray[index+1]);
		}
	}
	
	public void moveUp(int index) {
		if(canMoveUp(index)) {
			this.setCurrentRoom(this.roomArray[index-this.width]);
		}
	}
	
	public void moveDown(int index) {
		if(canMoveDown(index)) {
			this.setCurrentRoom(this.roomArray[index+this.width]);
		}
	}
	
	public Rectangle getExit() {
		return this.getCurrentRoom().getExit();
	}
	
	public StartRoom getStartRoom() {
		return this.startRoom;
	}
	
	public DenStore getDenStore() {
		return this.denStore;
	}
	
	public TreasureRoom getTreasureRoom() {
		return this.treasureRoom;
	}
	
	public boolean isKeySpawned() {
		return this.keySpawned;
	}
	
	public void setKeySpawned(boolean value) {
		this.keySpawned = value;
	}
	
	public boolean isTreasureOpen() {
		return this.treasureOpen;
	}
	
	public void setTreasureOpen() {
		this.treasureOpen = true;
	}

}
