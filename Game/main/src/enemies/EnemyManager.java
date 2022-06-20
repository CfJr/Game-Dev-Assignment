package enemies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class EnemyManager implements Serializable {
	
	private HashMap<String, Enemy> enemyPool = new HashMap<String, Enemy>();
	private ArrayList<String> forbiddenList = new ArrayList<String>();
	
	public EnemyManager() {
		generateEnemyPool();
		
//		this.forbiddenList.add("Key");
	}
	
	private void generateEnemyPool() {
		this.enemyPool.put("Wolf", new Wolf());
		this.enemyPool.put("Skunk", new Skunk());
		this.enemyPool.put("Raccoon", new Raccoon());
	}
	
	public Enemy getRandom() {
		Object[] values = this.enemyPool.values().toArray();
		int index;
		
		if(values.length == 2) {
			if(Math.random() < .5) {
				index = 0;
			} else {
				index = 1;
			}
		} else {
			index = (int)(Math.random() * (values.length));
		}
		
//		//If Key, pick a different item
//		if(this.forbiddenList.contains(((Enemy)values[index]).getName())) {
//			//Increment index, gives a coin instead
//			index += 1;
//		}
		
		
		//Clean and refill the pool
		enemyPool.clear();
		generateEnemyPool();
		
		return (Enemy)values[index];
		
	}
	
	public Enemy get(String item) {
		return this.enemyPool.get(item);
	}

}
