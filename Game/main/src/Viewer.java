import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import items.Item;
import util.GameObject;


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
 
 * Credits: Kelly Charles (2020)
 */ 
public class Viewer extends JPanel {
	private long CurrentAnimationTime= 0; 
	//Chest chest = new Chest();
	
	Model gameworld;
	 
	public Viewer(Model World) {
		this.gameworld=World;
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {
		
		this.repaint();
		// TODO Auto-generated method stub
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 
		
		
		//Draw player Game Object 
		int x = (int) gameworld.getPlayer().getBody().getCentre().getX();
		int y = (int) gameworld.getPlayer().getBody().getCentre().getY();
		int width = (int) gameworld.getPlayer().getBody().getWidth();
		int height = (int) gameworld.getPlayer().getBody().getHeight();
		String texture = gameworld.getPlayer().getBody().getTexture();
		
		//Draw background 
		//drawBackground(g);
		
		//Draw Water
		drawWater((int) gameworld.getWater().getBody().getCentre().getX(), (int) gameworld.getWater().getBody().getCentre().getY(), (int) gameworld.getWater().getBody().getWidth(), (int) gameworld.getWater().getBody().getHeight(), gameworld.getWater().getBody().getTexture(),g);	 
		
		//Draw Land
		drawLand((int) gameworld.getLand().getBody().getCentre().getX(), (int) gameworld.getLand().getBody().getCentre().getY(), (int) gameworld.getLand().getBody().getWidth(), (int) gameworld.getLand().getBody().getHeight(), gameworld.getLand().getBody().getTexture(),g);
		
		//Draw Bridges   
		gameworld.getBridges().forEach((key, value) -> 
		{
			drawBridges((int) value.getBody().getCentre().getX(), (int) value.getBody().getCentre().getY(), (int) value.getBody().getWidth(), (int) value.getBody().getHeight(), value.getBody().getTexture(),g);	 
		 
	    }); 
		
		//Draw Entrance
		if(gameworld.getCurrentRoom().hasEntrance()) {
			drawEntrance((int) gameworld.getCurrentRoom().getEntrance().getBody().getCentre().getX(), (int) gameworld.getCurrentRoom().getEntrance().getBody().getCentre().getY(), (int) gameworld.getCurrentRoom().getEntrance().getBody().getWidth(), (int) gameworld.getCurrentRoom().getEntrance().getBody().getHeight(), gameworld.getCurrentRoom().getEntrance().getBody().getTexture(),g);
		}
		
		//Draw Treasure Entrance
		if(gameworld.getCurrentRoom().hasTreasureEntrance()) {
			drawTreasureEntrance((int) gameworld.getCurrentRoom().getTreasureEntrance().getBody().getCentre().getX(), (int) gameworld.getCurrentRoom().getTreasureEntrance().getBody().getCentre().getY(), (int) gameworld.getCurrentRoom().getTreasureEntrance().getBody().getWidth(), (int) gameworld.getCurrentRoom().getTreasureEntrance().getBody().getHeight(), gameworld.getCurrentRoom().getTreasureEntrance().getBody().getTexture(),g);
		}
		
		//Draw Item
		if(gameworld.getCurrentRoom().hasItem()) {
			for(int i = 0; i < gameworld.getCurrentRoom().getItems().size(); i++) {
				drawItem((int) gameworld.getCurrentRoom().getItem(i).getBody().getCentre().getX(), (int) gameworld.getCurrentRoom().getItem(i).getBody().getCentre().getY(), (int) gameworld.getCurrentRoom().getItem(i).getBody().getWidth(), (int) gameworld.getCurrentRoom().getItem(i).getBody().getHeight(), gameworld.getCurrentRoom().getItem(i).getBody().getTexture(),g);
			}
		}
		
		//Draw Stored Treasure and Treasure Spots or draw start button
		if(gameworld.getCurrentRoom() == gameworld.getStartRoom()) {
			
			if(gameworld.getStart() == false) {
				drawStartButton((int) gameworld.getStartRoom().getStartButton().getBody().getCentre().getX(), (int) gameworld.getStartRoom().getStartButton().getBody().getCentre().getY(), (int) gameworld.getStartRoom().getStartButton().getBody().getWidth(), (int) gameworld.getStartRoom().getStartButton().getBody().getHeight(), gameworld.getStartRoom().getStartButton().getBody().getTexture(),g);
				drawLoadButton((int) gameworld.getStartRoom().getLoadButton().getBody().getCentre().getX(), (int) gameworld.getStartRoom().getLoadButton().getBody().getCentre().getY(), (int) gameworld.getStartRoom().getLoadButton().getBody().getWidth(), (int) gameworld.getStartRoom().getLoadButton().getBody().getHeight(), gameworld.getStartRoom().getLoadButton().getBody().getTexture(),g);
			} else {
			//draw spots
				for(int i = 0; i < gameworld.getStartRoom().getTreasureSpots().size(); i++) {
					drawTreasureSpot((int) gameworld.getStartRoom().getTreasureSpots().get(i).getBody().getCentre().getX(), (int) gameworld.getStartRoom().getTreasureSpots().get(i).getBody().getCentre().getY(), (int) gameworld.getStartRoom().getTreasureSpots().get(i).getBody().getWidth(), (int) gameworld.getStartRoom().getTreasureSpots().get(i).getBody().getHeight(), gameworld.getStartRoom().getTreasureSpots().get(i).getBody().getTexture(),g);
				}
				
				//draw treasure
				for(int i = 0; i < gameworld.getStartRoom().getTreasureItems().size(); i++) {
					drawItem((int) gameworld.getStartRoom().getTreasureItems().get(i).getBody().getCentre().getX(), (int) gameworld.getStartRoom().getTreasureItems().get(i).getBody().getCentre().getY(), (int) gameworld.getStartRoom().getTreasureItems().get(i).getBody().getWidth(), (int) gameworld.getStartRoom().getTreasureItems().get(i).getBody().getHeight(), gameworld.getStartRoom().getTreasureItems().get(i).getBody().getTexture(),g);
				}
			}
		}

		//Draw player
		drawPlayer(x, y, width, height, texture,g);
		  
		//Draw Bullets 
		gameworld.getBullets().forEach((temp) -> 
		{ 
			drawBullet((int) temp.getBody().getCentre().getX(), (int) temp.getBody().getCentre().getY(), (int) temp.getBody().getWidth(), (int) temp.getBody().getHeight(), temp.getBody().getTexture(),g);	 
		}); 
		
		//Draw Gas 
		gameworld.getGas().forEach((temp) -> 
		{ 
			drawGas((int) temp.getBody().getCentre().getX(), (int) temp.getBody().getCentre().getY(), (int) temp.getBody().getWidth(), (int) temp.getBody().getHeight(), temp.getBody().getTexture(),g);	 
		}); 
		
		//Draw Enemies   
		gameworld.getEnemies().forEach((temp) -> 
		{
			drawEnemies((int) temp.getBody().getCentre().getX(), (int) temp.getBody().getCentre().getY(), (int) temp.getBody().getWidth(), (int) temp.getBody().getHeight(), temp.getBody().getTexture(),g);	 
		 
	    }); 
		
		//drawScore();
		
		//drawChest((int) chest.getBody().getCentre().getX(), (int) chest.getBody().getCentre().getY(), (int) chest.getBody().getWidth(), (int) chest.getBody().getHeight(), chest.getBody().getTexture(),g);	 
	}
	
	private void drawEnemies(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%20)/4)*22); //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+22, 15, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	private void drawBackground(Graphics g)
	{
		File TextureToLoad = gameworld.getCurrentRoom().getTexture();  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,0, 800, 800, 0 , 0, 800, 800, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawWater(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 800, 800, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawLand(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 800, 800, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawBullet(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 64 
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%4)/2))*15; //slows down animation so every 10 frames we get another frame so every 100ms 
			 g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation , 0, currentPositionInAnimation+15, 15, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawGas(int x, int y, int width, int height, String texture, Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			// g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 200, 200, null); 
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%20)/4))*200; //slows down animation so every 10 frames we get another frame so every 100ms 
			 g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation , 0, currentPositionInAnimation+200, 200, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g) { 
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%50)/10))*22; //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+22, 30, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer));
		//Lighnting Png from https://opengameart.org/content/animated-spaceships  its 32x32 thats why I know to increament by 32 each time 
		// Bullets from https://opengameart.org/forumtopic/tatermands-art 
		// background image from https://www.needpix.com/photo/download/677346/space-stars-nebula-background-galaxy-universe-free-pictures-free-photos-free-images
		
	}
	
	private void drawChest(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 31, 31, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawEntrance(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 800, 800, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawTreasureEntrance(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 800, 800, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawItem(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//16 by 96 
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%20)/4))*16; //slows down animation so every 10 frames we get another frame so every 100ms 
			 g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation, 0, currentPositionInAnimation+16, 16, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawBridges(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 800, 600, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawTreasureSpot(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 200, 200, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawStartButton(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 800, 200, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawLoadButton(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 800, 200, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


/*
 * 
 * 
 *              VIEWER HMD into the world                                                             
                                                                                
                                      .                                         
                                         .                                      
                                             .  ..                              
                               .........~++++.. .  .                            
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .                        
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..                         
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .                 
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.                      
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...                  
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......            
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..    
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........    
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......   
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....   
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....  
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:..... 
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8..... 
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,.......... 
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........   
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .  
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..    
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........      
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......       
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........       
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............        
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............          
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................             
     .................,,,,,,,,,,,,,,,,.......................                   
       .................................................                        
           ....................................                                 
               ....................   .                                         
                                                                                
                                                                                
                                                                 GlassGiant.com
                                                                 */
