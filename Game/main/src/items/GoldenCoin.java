package items;

import java.awt.Rectangle;

import util.GameObject;
import util.Player;
import util.Point3f;
import util.Sounds;

public class GoldenCoin extends Item{
	
	private GameObject body;
	private Rectangle collider;
	private String name;
	private Sounds sounds = new Sounds();
	
	public GoldenCoin() {
		this.body = new GameObject("res/GoldenCoin.png", 40, 40, new Point3f(0, 0, 0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
		this.name = "Golden Coin";
	}

	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}
	
	public void useAbility(Player player) {
		player.giveTreasure(this);
		sounds.playSound("luck");
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
		player.setCoinBonus(player.getCoinBonus()+2);
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

}
