package enemies;

import java.awt.Rectangle;

import rooms.Room;
import rooms.Rooms;
import util.GameObject;
import util.*;

/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 */ 
public class Raccoon extends Enemy{
	
	private GameObject body;
	private Rectangle collider;
	private int health = 200;
	private long lastBulletTime = System.currentTimeMillis();
	private int fireRate = 1200;
	private String name = "Raccoon";
	
    public Raccoon() {
    	this.body = new GameObject("res/Raccoon.png",66,45,new Point3f(0, 0, 0));
    	this.collider = new Rectangle((int)this.body.getCentre().getX()+22, (int)this.body.getCentre().getY(), this.body.getWidth()-22, this.body.getHeight());
	}

	@Override
	public void move(Player player, Room room) {
		float currentX = this.body.getCentre().getX();
		this.body.getCentre().ApplyVector(getSlope(player));
		this.collider = new Rectangle((int)this.body.getCentre().getX()+22, (int)this.body.getCentre().getY(), this.body.getWidth()-22, this.body.getHeight());
		if(!(checkBoundaries(this, room, player))) {
			this.body.getCentre().ApplyVector(getSlope(player).NegateVector());
			this.collider = new Rectangle((int)this.body.getCentre().getX()+22, (int)this.body.getCentre().getY(), this.body.getWidth()-22, this.body.getHeight());
		} else {
			//If moves left
			if(currentX > this.body.getCentre().getX()) {
				this.body.setTexture("res/RaccoonLeft.png");
			}
			
			//If moves right
			if(currentX < this.body.getCentre().getX()) {
				this.body.setTexture("res/Raccoon.png");
			}
		}
	}

	@Override
	public void attack(Player player, Room room) {
		if(this.canShoot()) {
			room.createEnemyBullet(player, this);
			this.lastBulletTime = System.currentTimeMillis();
		}
	}
	
	public GameObject getBody() {
		return this.body;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Vector3f getSlope(Player player) {
		int enemyX = (int)this.body.getCentre().getX() + (int)this.body.getWidth()/2;
		int enemyY = (int)this.body.getCentre().getY() + (int)this.body.getHeight()/2;
		int playerX = (int)player.getBody().getCentre().getX() + (int)player.getBody().getWidth()/2;
		int playerY = (int)player.getBody().getCentre().getY() + (int)player.getBody().getHeight()/2;
		
		Vector3f slope = new Vector3f(playerX - enemyX, -(playerY - enemyY), 0);
		slope = slope.Normal();
		slope = slope.byScalar((float)0.7);
		
		return slope;
	}
	
	public boolean canShoot() {
		if((System.currentTimeMillis() - this.lastBulletTime) > this.fireRate) {
			return true;
		}
		return false;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	private Boolean checkBoundaries(Raccoon test, Room room, Player player) {
		
		//Enemy Collision
		for(int i = 0; i < room.getEnemiesList().size(); i++) {
			//If checking current enemy
			if(room.getEnemiesList().get(i).equals(test)) {
				continue;
			}
			if(test.getCollider().intersects(room.getEnemiesList().get(i).getCollider())) {
				return false;
			}
		}
		
		//Player Collision
		if(test.getCollider().intersects(player.getCollider())) {
			return false;
		}
		
		//On Land
		if(room.getLand().getCollider().contains(test.getCollider())) {
			return true;
		}
		
		return false;
	}
	
	public void spawn(Room room, Player player, Rooms rooms) {
		while(!(this.checkSpawnBoundaries(this, room, player, rooms))) {
    		this.body = new GameObject("res/Raccoon.png",66,45,new Point3f((int)((Math.random() * 300)+200),(int)((Math.random() * 300)+200),0));
        	this.collider = new Rectangle((int)this.body.getCentre().getX()+22, (int)this.body.getCentre().getY(), this.body.getWidth()-22, this.body.getHeight());
    	}
	}
	
	private Boolean checkSpawnBoundaries(Raccoon test, Room room, Player player, Rooms rooms) {
		
		//Enemy Collision
		for(int i = 0; i < room.getEnemiesList().size(); i++) {
			if(test.getCollider().intersects(room.getEnemiesList().get(i).getCollider())) {
				if(room.getEnemiesList().get(i) == test) {
					continue;
				}
				return false;
			}
		}
		
		if(rooms.getCurrentRoom() == room) {
			//Player Collision
			if(test.getCollider().intersects(player.getCollider())) {
				return false;
			}
		}
		
		//Not On Land
		if(!(room.getLand().getCollider().contains(test.getCollider()))) {
			return false;
		}
		
		return true;
	}
	
	
    
}

/*
 *  Game Object 
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::c:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::clc::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::lol:;::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::;;cool:;::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::codk0Oxolc:::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::coxk0XNWMMWWWNK0kxdolc::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::loxO0XWMMMMMMMWWWMMMMMMWWNK0Oxdlc::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::cldkOKNWMMMMMMMMMMMMMWWMMMMMMMMMMMMMWNX0Okdolc:::::::::::::::::::::::::::::::::
::::::::::::::::codk0KNWMMMMMMMMMMMMMMMMMMWMMMMMMMMMMMMMMMMMMMMWWXKOkxo:::::::::::::::::::::::::::::
::::::::::::;cdOXNWMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMMMMMMMMMMMMMNKOdc::::::::::::::::::::::::::::
:::::::::::::cxKXXNWWMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMMMMMMMMMWX0kdolc:::::::::::::::::::::::::::::
::::::::::::::d0000KKXNNWMMMMMMMMMMMMMMMMWWMMMMMMMMMMMMMMMMWNKOxolllllc:::::::::::::::::::::::::::::
::::::::::::::oO00000000KXXNWWMMMMMMMMMMMMWMMMMMMMMMMMMMWX0kdollllllllc:::::::::::::::::::::::::::::
::::::::::::::lk00000O000000KKXNWWMMMMMMMWWWMMMMMMMMWNKOxollllllllllll::::::::::::::::::::::::::::::
::::::::::::::cx0000000000O000000KXXNWMMMWWWMMMMWXK0kdlllllllllllllllc::::::::::::::::::::::::::::::
:::::::::::::::dO00000000000000000000KKXNNNWWNKOxolllllllllllllllllllc::::::::::::::::::::::::::::::
:::::::::::::::lO000000000000000OOOO0000000Kkdlllllllllllllllllllllllc::::::::::::::::::::::::::::::
:::::::::::::::ck00000000000000000OOOOOOOOkxollllllllllllllllllllllll:::::::::::::::::::::::::::::::
:::::::::::::;;cx00000000000000000000OOOOOOxocllllllllllllllllllllllc:;;;;;;;;;;::::::::::::::::::::
;;;;;;;;;;;;;;;:oO00000000000000000OOOO0000kdllllcclllllllllllllllllc:;;;;;;;;;;;;;;;;;;::::::::::::
;;;;;;;;;;;;;;;:lO00000000000000OOO00000000Oolllllllllllllllllllllllc:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;ck0000000000OOO000000000000kolllllllllllllllllllllll:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;:dOO0000OOO0000000000000000kolllllllllllllllllllllllc:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;:lxkOOOO0000000000000000000kolllllllllllllllllllllooool::;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;::;;::cokOkkO00000000000000000000kolllllllllllllllllllllccccllcc::::;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;:;;:;::ccllodxkO00000000000000000000kolllllllllllllllllllcc:;;;;:::::;;:;;;;;;;;;;;;;;;;;;;;;
;;;;;;;::::::::::;;:ldkO0000000000000000000kolllllllllllllllllc::;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;::;;:::;;;;;:;::ldkO0000000000000000kollllllllllllllcc::;;;;:;;:;;;;;:;;;:;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;:;;;;:cldkO0000000000000kollllllllllllc:::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;::ldkO0000000000kolllllllllcc::;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;::;:;;::ldkO0000000kolllllllc::::;;;;:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;::ldkO0000kolllcc:::;;;;;;;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;;;;;:ldkO0kolcc::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;;;::lodl:::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;::;:::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;::;;;;;;;;;;;:::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
*/