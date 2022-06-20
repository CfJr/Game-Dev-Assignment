package items;

import java.awt.Rectangle;
import java.io.Serializable;

import util.GameObject;
import util.Player;

public abstract class Item implements Serializable{

	public abstract GameObject getBody();
	
	public abstract Rectangle getCollider();
	
	public abstract String getName();
	
	public abstract void useAbility(Player player);
	
	public abstract void setXY(int x, int y);
	
	public abstract void applyBonus(Player player);
	
	public abstract int getCost();
}
