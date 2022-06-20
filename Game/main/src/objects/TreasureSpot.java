package objects;

import java.io.Serializable;

import util.GameObject;
import util.Point3f;

public class TreasureSpot implements Serializable{
	
	private GameObject body;
	
	public TreasureSpot(int x, int y) {
		this.body = new GameObject("res/TreasureSpot.png", 75, 75, new Point3f(x, y, 0));
	}
	
	public GameObject getBody() {
		return this.body;
	}
	

}
