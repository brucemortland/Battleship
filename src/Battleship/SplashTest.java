
package Battleship;

import java.awt.*;

public class SplashTest extends GameSkeleton
	{
	private static final long serialVersionUID = 1L;

	public void draw(Graphics g)
		{
		g.setColor(Color.red);
		g.fillRect(0, 0,600,600);
		Splash.draw(g);
		}
	
	
	public void keyDown(int inKey)
		{
		Splash.keyDown(inKey);
		}
	
	public static void main(String args[])
		{
		new SplashTest().playGame();
		}
	}