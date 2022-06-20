package enemies;

import java.awt.Rectangle;
import java.io.Serializable;

import rooms.Room;
import rooms.Rooms;
import util.GameObject;
import util.Player;
import util.Vector3f;

public abstract class Enemy implements Serializable{
	
	public abstract void move(Player player, Room room);
	
	public abstract void attack(Player player, Room room);
	
	public abstract GameObject getBody();
	
	public abstract Vector3f getSlope(Player player);
	
	public abstract Rectangle getCollider();
	
	public abstract int getHealth();
	
	public abstract void setHealth(int health);
	
	public abstract String getName();
	
	public abstract void spawn(Room room, Player player, Rooms rooms);

}
