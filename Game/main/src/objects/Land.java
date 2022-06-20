package objects;

import java.awt.Rectangle;
import java.io.Serializable;

import util.GameObject;
import util.Point3f;

public class Land implements Serializable{
	
	private GameObject body;
	private Rectangle collider;
	private String[] landTextures = {"grass_island", "bare_island", "blue_island", "red_island", "purple_island", "grey_island"};
	
	public Land(int width, int height, int x, int y, String type) {
		if(type=="ENEMY") {
			//Generate random land texture
			int index = (int)(Math.random() * this.landTextures.length);
			this.body = new GameObject("res/" + this.landTextures[index] + ".png",width,height,new Point3f(x, y, 0));
			this.collider = new Rectangle((int)body.getCentre().getX()+32, (int)body.getCentre().getY(), body.getWidth()-40, body.getHeight()-45);
		}
		
		if(type=="START") {
			this.body = new GameObject("res/PreDen.png",width,height,new Point3f(x, y, 0));
			this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
		}
		
		if(type=="TREASURE") {
			this.body = new GameObject("res/TreasureRoom.png",width,height,new Point3f(x, y, 0));
			this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
		}
		
		if(type=="STORE") {
			this.body = new GameObject("res/Store.png",width,height,new Point3f(x, y, 0));
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
