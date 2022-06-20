package objects;

import java.awt.Rectangle;
import java.io.Serializable;

import util.GameObject;
import util.Point3f;

public class Gas implements Serializable{
	
	private GameObject body;
	private Rectangle collider;
	private long creationTime;
	
	public Gas(int x, int y) {
		this.body = new GameObject("res/Gas.png",100,100, new Point3f(x, y, 0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
		this.creationTime = System.currentTimeMillis();
	}
	
	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}
	
	public long getCreationTime() {
		return this.creationTime;
	}

}
