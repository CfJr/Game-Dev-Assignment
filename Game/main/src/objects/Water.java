package objects;

import java.awt.Rectangle;
import java.io.Serializable;

import util.GameObject;
import util.Point3f;

public class Water implements Serializable{
	
	private GameObject body;
	private Rectangle collider;
	
	public Water() {
		this.body = new GameObject("res/water.png",800,800,new Point3f(0, 0, 0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
	}
	
	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}

}
