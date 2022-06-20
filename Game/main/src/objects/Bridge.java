package objects;

import java.awt.Rectangle;
import java.io.Serializable;

import util.GameObject;
import util.Point3f;

public class Bridge implements Serializable{
	
	private GameObject body;
	private Rectangle collider;
	
	public Bridge(float x, float y, String position) {
		
		if(position == "NORTH" || position == "SOUTH") {
			this.body = new GameObject("res/bridge_vertical.png",150,150,new Point3f(x-50, y, 0));
			this.collider = new Rectangle((int)body.getCentre().getX()+20, (int)body.getCentre().getY()-20, body.getWidth()-50, body.getHeight());
		}
		
		if(position == "EAST" || position == "WEST") {
			this.body = new GameObject("res/bridge_horizontal.png",150,100,new Point3f(x, y-50, 0));
			this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
		}
	
	}
	
	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}

}
