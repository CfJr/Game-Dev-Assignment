package items;

import java.awt.Rectangle;

import util.GameObject;
import util.Player;
import util.Point3f;
import util.Sounds;

public class Key extends Item{
	
	private GameObject body;
	private Rectangle collider;
	private String name;
	private Sounds sounds = new Sounds();
	
	public Key() {
		this.body = new GameObject("res/Key.png",40,40,new Point3f(0, 0, 0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
		this.name = "Key";
	}

	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}
	
	public void useAbility(Player player) {
		player.setHasKey();
		sounds.playSound("key");
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

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

}
