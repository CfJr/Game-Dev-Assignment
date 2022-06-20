package items;

import java.awt.Rectangle;

import util.GameObject;
import util.Player;
import util.Point3f;

public class LargeCoin extends Item{
	
	private GameObject body;
	private Rectangle collider;
	private String name;
	private int value = 25;
	
	public LargeCoin() {
		this.body = new GameObject("res/Coin.png", 200, 200, new Point3f(0, 0, 0));
		this.collider = new Rectangle((int)body.getCentre().getX(), (int)body.getCentre().getY(), body.getWidth(), body.getHeight());
		this.name = "Large Coin";
	}

	public GameObject getBody() {
		return this.body;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}
	
	public void useAbility(Player player) {
		player.giveTreasure(this);
		player.setCoins(player.getCoins() + this.value);
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
