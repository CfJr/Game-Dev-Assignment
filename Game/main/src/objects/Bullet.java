package objects;

import java.awt.Rectangle;
import java.io.Serializable;

import rooms.Rooms;
import util.GameObject;
import util.Player;
import util.Point3f;
import util.Vector3f;

public class Bullet implements Serializable{
	
	private GameObject body;
	private Point3f target;
	private Vector3f slope;
	private Rectangle collider;
	private boolean isFriendly = false;
	private int velocity = 5;
	private boolean bounced = false;
	
	public Bullet(Player player, Point3f origin, Point3f target) {
		this.target = target;
		
		//If player created
		if(origin == player.getBody().getCentre()) {
			this.body = new GameObject("res/Bone.png",30,30,new Point3f(origin.getX()+10, origin.getY()+45, 0.0f));
			this.isFriendly = true;
		} else {
			this.body = new GameObject("res/EnemyBone.png",30,30,new Point3f(origin.getX(), origin.getY(), 0.0f));
		}
		
		this.collider = new Rectangle((int)this.body.getCentre().getX(), (int)this.body.getCentre().getY(), this.body.getWidth(), this.body.getHeight());
		this.calculateSlope();
	}
	
	public GameObject getBody() {
		return this.body;
	}
	
	public void move(Rooms rooms) {
		if(!(rooms.getCurrentRoom().getType().equals("ENEMY")) && this.bounced == false) {
			this.body.getCentre().ApplyVector(this.slope);
			this.collider = new Rectangle((int)this.body.getCentre().getX(), (int)this.body.getCentre().getY(), this.body.getWidth(), this.body.getHeight());
			if(!(this.checkBoundaries(this, rooms))) {
				this.body.getCentre().ApplyVector(this.slope.NegateVector());
				this.collider = new Rectangle((int)this.body.getCentre().getX(), (int)this.body.getCentre().getY(), this.body.getWidth(), this.body.getHeight());
				this.bounced = true;
			} 
		} else {
			this.body.getCentre().ApplyVector(this.slope);
			this.collider = new Rectangle((int)this.body.getCentre().getX(), (int)this.body.getCentre().getY(), this.body.getWidth(), this.body.getHeight());
		}
	}
	
	private void calculateSlope() {
		//Get co-ordinates of bullet centre and target centre
		int bulletX = (int)this.body.getCentre().getX() + (int)this.body.getWidth()/2;
		int bulletY = (int)this.body.getCentre().getY() + (int)this.body.getHeight()/2;
		int targetX = (int)target.getX();
		int targetY = (int)target.getY();
		
		//Create a Vector3f by calculating the slope
		this.slope = new Vector3f(targetX - bulletX, -(targetY - bulletY), 0);
		//Normalize the slope
		this.slope = this.slope.Normal();
		//Set the velocity
		this.slope = this.slope.byScalar(this.velocity);
	}
	
	public boolean isFriendly() {
		return this.isFriendly;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}
	
private Boolean checkBoundaries(Bullet test, Rooms rooms) {
		
		if(!(rooms.getCurrentRoom().getType().equals("ENEMY"))) {		
			
			if((test.getBody().getCentre().getX() > 735) || (test.getBody().getCentre().getX() < 40)) {
				this.slope.setX(this.slope.getX()*-1);
				return false;
			}
			
			if((test.getBody().getCentre().getY() > 735) || (test.getBody().getCentre().getY() < 40)) {
				this.slope.setY(this.slope.getY()*-1);
				return false;
			}
		}

		return true;
	}

}
