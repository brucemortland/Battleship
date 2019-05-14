
package Battleship;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Splash 
	{
	static Image image=null;
	static boolean showSplashScreen=true;
	
	public static Image readImage(String imageName)
		{
	    Image image = Toolkit.getDefaultToolkit().getImage(imageName);
	    MediaTracker imageTracker = new MediaTracker(new JPanel());
	    imageTracker.addImage(image, 0);
	    try
	    	{
	    	imageTracker.waitForID(0);
	    	}
	    catch(InterruptedException e)
	    	{ 
	    	return null;
	    	}
	    return image;
		}
	
	public static void draw(Graphics g)
		{
		if(image == null || image.getWidth(null)==-1) 
			{
			image = readImage("BattleshipResources/SplashScreen.jpg");
			return;
			}
		if(!showSplashScreen)
			return;
		g.setColor(Color.black);
		g.fillRect(0, 0,600,600);
	
		g.drawImage(image,  -100,  0, 800,600,null);
		
		int titleX=50;
		int titleY=570;
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		g.drawString("Battleship", titleX-5, titleY-5);
		g.drawString("Battleship", titleX+5, titleY-5);
		g.drawString("Battleship", titleX-5, titleY+5);
		g.drawString("Battleship", titleX+5, titleY+5);
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		g.drawString("Battleship", titleX, titleY);
		}
	
	public static void keyDown(int inKey)
		{
		showSplashScreen=false;
		}
	
	public static void mouseDown(int x, int y)
		{
		showSplashScreen=false;
		}
	

	}