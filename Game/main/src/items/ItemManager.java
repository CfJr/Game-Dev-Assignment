package items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import rooms.StartRoom;

public class ItemManager implements Serializable{
	
	private HashMap<String, Item> itemPool = new HashMap<String, Item>();
	private HashMap<String, Item> treasurePool = new HashMap<String, Item>();
	
	public ItemManager() {
		generateItemPool();
		generateTreasurePool();
	}
	
	private void generateItemPool() {
		this.itemPool.put("Meat", new FireRatePowerUp());
		this.itemPool.put("Speed", new SpeedPowerUp());
		this.itemPool.put("Heart", new Heart());
		this.itemPool.put("Coin", new Coin());
		this.itemPool.put("Luck", new Luck());
	}
	
	private void generateTreasurePool() {
		this.treasurePool.put("Golden Bone", new GoldenBone());
		this.treasurePool.put("Golden Coin", new GoldenCoin());
		this.treasurePool.put("Golden Meat", new GoldenMeat());
		this.treasurePool.put("Golden Heart", new GoldenHeart());
		this.treasurePool.put("Golden Luck", new GoldenLuck());
		this.treasurePool.put("Golden Speed", new GoldenSpeed());
	}
	
	public Item getRandom() {
		Object[] values = this.itemPool.values().toArray();
		int index = (int)(Math.random() * (values.length));
		
		//Clear and refill the pool
		itemPool.clear();
		generateItemPool();
		
		return (Item)values[index];
		
	}
	
	public Item getRandomTreasureItem(StartRoom startRoom) {
		Object[] values = this.treasurePool.values().toArray();
		int index = (int)(Math.random() * (values.length));
		
		for(int i = 0; i < values.length; i++) {
			if(startRoom.hasTreasureItem(((Item)values[(index+i)%values.length]).getName())) {
				continue;
			} else {
				//Clear and refill the pool
				treasurePool.clear();
				generateTreasurePool();
				return (Item)values[(index+i)%values.length];
			}
		}
		
		//Give a coin if no new treasure items
		return new LargeCoin();
		
	}
	
	public Item get(String item) {
		
		if(item == "Key") {
			return new Key();
		}
		
		if(itemPool.containsKey(item)) {
			Item temp = this.itemPool.get(item);
			//Clear and refill the pool
			itemPool.clear();
			generateItemPool();
			return temp;
		} 
		
		if(treasurePool.containsKey(item)) {
			Item temp = this.treasurePool.get(item);
			//Clear and refill the pool
			treasurePool.clear();
			generateTreasurePool();
			return temp;
		} else {
			return new Coin();
		}
	}

}
