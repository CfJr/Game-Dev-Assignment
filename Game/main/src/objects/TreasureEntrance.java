package objects;

import java.awt.Rectangle;
import java.io.Serializable;

import util.GameObject;
import util.Point3f;

public class TreasureEntrance implements Serializable{
	
	private GameObject body;
	private Rectangle collider;
	
	public TreasureEntrance() {
		this.body = new GameObject("res/TreasureEntrance.png",(int)(Math.random() * 3) + 100, (int)(Math.random() * 3) + 100, new Point3f(200, 500, 0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
	}
	
	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}

}
