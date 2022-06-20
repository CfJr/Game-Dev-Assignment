import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.UnitTests;

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



public class MainWindow {
	 private static  JFrame frame = new JFrame("Game");   // Change to the name of your game 
	 private static   Model gameworld= new Model();
	 private static   Viewer canvas = new  Viewer(gameworld);
	 private KeyListener keyboardController = new Controller();
	 private MouseListener mouseController = new Controller();
	 private static   int TargetFPS = 60;
	 private static boolean startGame= false;
	 //private   JLabel BackgroundImageForStartMenu ;
	 private static JLabel healthLabel = new JLabel();
	 private static JLabel coinLabel = new JLabel();
	 private static JLabel luckLabel = new JLabel();
	 private static JLabel storedCoinsLabel = new JLabel();
	 
	 //Store items
	 private static JLabel healthPriceLabel = new JLabel();
	 private static JLabel luckPriceLabel = new JLabel();
	 private static JLabel speedPriceLabel = new JLabel();
	 private static JLabel meatPriceLabel = new JLabel();
	 
	  
	public MainWindow() {
	        frame.setSize(812, 835);  // you can customise this later and adapt it to change on size.  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //If exit // you can modify with your way of quitting , just is a template.
	        frame.setLayout(null);
	        frame.add(canvas);
	        setupLabels();
	        canvas.setBounds(0, 0, 812, 835); 
	        canvas.setBackground(new Color(255,255,255)); //white background  replaced by Space background but if you remove the background method this will draw a white screen 
	        canvas.setVisible(false);   // this will become visible after you press the key. 
	        frame.setVisible(true);   
	        canvas.setVisible(true); 
			canvas.addKeyListener(keyboardController);    //adding the controller to the Canvas 
			canvas.addMouseListener(mouseController);
			canvas.requestFocusInWindow();   // making sure that the Canvas is in focus so keyboard input will be taking in .
			startGame=true;
	}
	
	public void setupLabels() {
		canvas.setLayout(null);
		canvas.add(healthLabel);
        healthLabel.setSize(new Dimension(200, 200));
        //scoreBoard.setBackground(Color.BLACK);
        //scoreBoard.setOpaque(true);
        healthLabel.setFont(new Font("Purisa", Font.ITALIC, 20));
        healthLabel.setLocation(50, -75);
        healthLabel.setVisible(false);
        healthLabel.setForeground(Color.WHITE);
        
        canvas.add(coinLabel);
        coinLabel.setSize(new Dimension(200, 200));
        coinLabel.setFont(new Font("Purisa", Font.ITALIC, 20));
        coinLabel.setLocation(200, -75);
        coinLabel.setVisible(false);
        coinLabel.setForeground(Color.WHITE);
        
        canvas.add(luckLabel);
        luckLabel.setSize(new Dimension(200, 200));
        luckLabel.setFont(new Font("Purisa", Font.ITALIC, 20));
        luckLabel.setLocation(510, -75);
        luckLabel.setVisible(false);
        luckLabel.setForeground(Color.WHITE);
        
        canvas.add(storedCoinsLabel);
        storedCoinsLabel.setSize(new Dimension(200, 200));
        storedCoinsLabel.setFont(new Font("Purisa", Font.ITALIC, 20));
        storedCoinsLabel.setLocation(170, -75);
        storedCoinsLabel.setVisible(false);
        storedCoinsLabel.setForeground(Color.WHITE);
        
        canvas.add(healthPriceLabel);
        healthPriceLabel.setSize(new Dimension(200, 200));
        healthPriceLabel.setFont(new Font("Purisa", Font.ITALIC, 20));
        healthPriceLabel.setLocation(158, 100);
        healthPriceLabel.setVisible(false);
        healthPriceLabel.setForeground(Color.WHITE);
        
        canvas.add(luckPriceLabel);
        luckPriceLabel.setSize(new Dimension(200, 200));
        luckPriceLabel.setFont(new Font("Purisa", Font.ITALIC, 20));
        luckPriceLabel.setLocation(308, 100);
        luckPriceLabel.setVisible(false);
        luckPriceLabel.setForeground(Color.WHITE);
        
        canvas.add(speedPriceLabel);
        speedPriceLabel.setSize(new Dimension(200, 200));
        speedPriceLabel.setFont(new Font("Purisa", Font.ITALIC, 20));
        speedPriceLabel.setLocation(458, 100);
        speedPriceLabel.setVisible(false);
        speedPriceLabel.setForeground(Color.WHITE);
        
        canvas.add(meatPriceLabel);
        meatPriceLabel.setSize(new Dimension(200, 200));
        meatPriceLabel.setFont(new Font("Purisa", Font.ITALIC, 20));
        meatPriceLabel.setLocation(608, 100);
        meatPriceLabel.setVisible(false);
        meatPriceLabel.setForeground(Color.WHITE);
	}

	public static void main(String[] args) {
		MainWindow hello = new MainWindow();  //sets up environment 
		while(true)   //not nice but remember we do just want to keep looping till the end.  // this could be replaced by a thread but again we want to keep things simple 
		{ 
			//swing has timer class to help us time this but I'm writing my own, you can of course use the timer, but I want to set FPS and display it 
			
			int TimeBetweenFrames =  1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames; 
			
			//wait till next time step 
		 while (FrameCheck > System.currentTimeMillis()){} 
			
			
			if(startGame)
				 {
				 gameloop();
				 }
			
			//UNIT test to see if framerate matches 
		 UnitTests.CheckFrameRate(System.currentTimeMillis(),FrameCheck, TargetFPS); 
			  
		}
		
		
	} 
	//Basic Model-View-Controller pattern 
	private static void gameloop() { 
		// GAMELOOP  
		
		// controller input  will happen on its own thread 
		// So no need to call it explicitly 
		
		// model update   
		gameworld.gamelogic();
		// view update 
		
		  canvas.updateview(); 
		
		// Both these calls could be setup as  a thread but we want to simplify the game logic for you.  
		//score update  
//		 frame.setTitle("Health =  "+ gameworld.getHealth());
		  frame.setTitle("Fox Ventura");
		
		  healthLabel.setText("Health: " + gameworld.getHealth());
		  coinLabel.setText("Coins: " + gameworld.getPlayer().getCoins());
		  //luckLabel.setText("Luck: " + (int)((0.2 + gameworld.getPlayer().getLuck())*100) + " / 100");
		  storedCoinsLabel.setText("Stored Coins: " + gameworld.getPlayer().getStoredCoins());
		  
		  healthPriceLabel.setText("" + gameworld.getCurrentRoom().getItemManager().get("Heart").getCost());
		  luckPriceLabel.setText("" + gameworld.getCurrentRoom().getItemManager().get("Luck").getCost());
		  speedPriceLabel.setText("" + gameworld.getCurrentRoom().getItemManager().get("Speed").getCost());
		  meatPriceLabel.setText("" + gameworld.getCurrentRoom().getItemManager().get("Meat").getCost());
		  
		  if(gameworld.getStart() != false) {
		  
			  if((gameworld.getCurrentRoom() == gameworld.getStartRoom()) || (gameworld.getCurrentRoom() == gameworld.getDenStore())) {
				  healthLabel.setVisible(true);
				  storedCoinsLabel.setVisible(true);
				  //luckLabel.setVisible(true);
				  coinLabel.setVisible(false);
			  } else {
				  storedCoinsLabel.setVisible(false);
				  coinLabel.setVisible(true);
			  }
			  
			  if(gameworld.getCurrentRoom() == gameworld.getDenStore()) {
				  healthPriceLabel.setVisible(true);
				  luckPriceLabel.setVisible(true);
				  speedPriceLabel.setVisible(true);
				  meatPriceLabel.setVisible(true);
			  } else {
				  healthPriceLabel.setVisible(false);
				  luckPriceLabel.setVisible(false);
				  speedPriceLabel.setVisible(false);
				  meatPriceLabel.setVisible(false);
			  }
		  }
	}

}

/*
 * 
 * 

Hand shake agreement 
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,=+++
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,:::::,=+++????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,:++++????+??
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,:,,:,:,,,,,,,,,,,,,,,,,,,,++++++?+++++????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=++?+++++++++++??????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++?+++?++?++++++++++?????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:::,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++++++++++++++????+++++++???????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,:===+=++++++++++++++++++++?+++????????????????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,~=~~~======++++++++++++++++++++++++++????????????????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,::::,,,,,,=~.,,,,,,,+===~~~~~~====++++++++++++++++++++++++++++???????????????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,~~.~??++~.,~~~~~======~=======++++++++++++++++++++++++++????????????????II
:::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,:=+++??=====~~~~~~====================+++++++++++++++++++++?????????????????III
:::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,++~~~=+=~~~~~~==~~~::::~~==+++++++==++++++++++++++++++++++++++?????????????????IIIII
::::::::::::::::::::::::::::::::::::::::::::::::,:,,,:++++==+??+=======~~~~=~::~~===++=+??++++++++++++++++++++++++?????????????????I?IIIIIII
::::::::::::::::::::::::::::::::::::::::::::::::,,:+????+==??+++++?++====~~~~~:~~~++??+=+++++++++?++++++++++??+???????????????I?IIIIIIII7I77
::::::::::::::::::::::::::::::::::::::::::::,,,,+???????++?+?+++???7?++======~~+=====??+???++++++??+?+++???????????????????IIIIIIIIIIIIIII77
:::::::::::::::::::::::::::::::::::::::,,,,,,=??????IIII7???+?+II$Z77??+++?+=+++++=~==?++?+?++?????????????III?II?IIIIIIIIIIIIIIIIIIIIIIIIII
::::::::::::::::::::::::::::::,,,,,,~=======++++???III7$???+++++Z77ZDZI?????I?777I+~~+=7+?II??????????????IIIIIIIIIIIIIIIIIIIIII??=:,,,,,,,,
::::::::,:,:,,,,,,,:::~==+=++++++++++++=+=+++++++???I7$7I?+~~~I$I??++??I78DDDO$7?++==~I+7I7IIIIIIIIIIIIIIIIII777I?=:,,,,,,,,,,,,,,,,,,,,,,,,
++=++=++++++++++++++?+????+??????????+===+++++????I7$$ZZ$I+=~$7I???++++++===~~==7??++==7II?~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
+++++++++++++?+++?++????????????IIIII?I+??I???????I7$ZOOZ7+=~7II?+++?II?I?+++=+=~~~7?++:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
+?+++++????????????????I?I??I??IIIIIIII???II7II??I77$ZO8ZZ?~~7I?+==++?O7II??+??+=====.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
?????????????III?II?????I?????IIIII???????II777IIII7$ZOO7?+~+7I?+=~~+???7NNN7II?+=+=++,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
????????????IIIIIIIIII?IIIIIIIIIIII????II?III7I7777$ZZOO7++=$77I???==+++????7ZDN87I??=~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIII?II??IIIIIIIIIIIIIIIIIIIIIIIIIII???+??II7777II7$$OZZI?+$$$$77IIII?????????++=+.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII?+++?IIIII7777$$$$$$7$$$$7IIII7I$IIIIII???I+=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII???????IIIIII77I7777$7$$$II????I??I7Z87IIII?=,,,,,,,,,,,:,,::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
777777777777777777777I7I777777777~,,,,,,,+77IIIIIIIIIII7II7$$$Z$?I????III???II?,,,,,,,,,,::,::::::::,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
777777777777$77777777777+::::::::::::::,,,,,,,=7IIIII78ZI?II78$7++D7?7O777II??:,,,:,,,::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
$$$$$$$$$$$$$77=:,:::::::::::::::::::::::::::,,7II$,,8ZZI++$8ZZ?+=ZI==IIII,+7:,,,,:::::::::::::::::,:::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
$$$I~::::::::::::::::::::::::::::::::::::::::::II+,,,OOO7?$DOZII$I$I7=77?,,,,,,:::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::+ZZ?,$ZZ$77ZZ$?,,,,,::::::::::::::::::::::::::,::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::I$:::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
                                                                                                                             GlassGiant.com
 * 
 * 
 */
