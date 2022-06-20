package objects;

import java.awt.Rectangle;
import java.io.Serializable;

import util.GameObject;
import util.Point3f;

public class LoadButton implements Serializable{
	
	private GameObject body;
	private Rectangle collider;
	
	public LoadButton(int x, int y) {
		this.body = new GameObject("res/LoadButton.png",200,50, new Point3f(x, y, 0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
	}
	
	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}

}
