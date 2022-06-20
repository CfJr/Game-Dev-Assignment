package items;

import java.awt.Rectangle;

import util.GameObject;
import util.Point3f;

public class Chest {
	
	private GameObject body;
	private Rectangle collider;
	
	public Chest() {
		this.body = new GameObject("res/chest2.png",40,40,new Point3f((int)((Math.random() * 600) + 100), (int)((Math.random() * 600) + 100),0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
	}
	
	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}

}
