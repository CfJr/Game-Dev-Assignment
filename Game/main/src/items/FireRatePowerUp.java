package items;

import java.awt.Rectangle;

import util.GameObject;
import util.Player;
import util.Point3f;
import util.Sounds;

public class FireRatePowerUp extends Item{
	
	private GameObject body;
	private Rectangle collider;
	private int value = 50;
	private int cost = 25;
	private String name;
	private Sounds sounds = new Sounds();
	
	public FireRatePowerUp() {
		this.body = new GameObject("res/Meat.png",40,40,new Point3f(0, 0, 0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
		this.name = "FireRatePowerUp";
	}

	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}
	
	public void useAbility(Player player) {
		player.setFireRate(player.getFireRate()-this.value);
		sounds.playSound("eat");
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setXY(int x, int y) {
		this.body.getCentre().setX(x);
		this.body.getCentre().setY(y);
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
	}

	@Override
	public void applyBonus(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	public int getCost() {
		return this.cost;
	}

}
