package items;

import java.awt.Rectangle;

import util.GameObject;
import util.Player;
import util.Point3f;
import util.Sounds;

public class Heart extends Item{
	
	private GameObject body;
	private Rectangle collider;
	private String name;
	private int cost = 5;
	private int value = 10;
	private Sounds sounds = new Sounds();
	
	public Heart() {
		this.body = new GameObject("res/Heart.png", 28, 28, new Point3f(0, 0, 0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
		this.name = "Heart";
	}

	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}
	
	public void useAbility(Player player) {
		player.setHealth(player.getHealth()+this.value);
		sounds.playSound("heart");
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
