
package Battleship;

import java.awt.*;
import java.awt.event.*;

public class ImageTest extends GameSkeleton
	{
	static final long serialVersionUID = 0;
	int xCoordinate;
	int squareSize;
	Image image = readImage("BattleshipResources/USSNautilusColored.png");
	public void start()
		{
		xCoordinate = 300;
		squareSize = 10;
		}
	
	public void tick()
		{
		squareSize = squareSize + 1;
		}
	
	public void draw(Graphics g)
		{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(image, 50, 50,150,15,0,0,image.getWidth(null),image.getHeight(null),null);
		
		}
	
	public void keyDown(int inKey)
		{
		if(KeyEvent.VK_S == inKey)
			{
			start();
			}
		if(KeyEvent.VK_Q == inKey)
			{
			System.exit(0);
			}
		
		repaint();
		}
	
	public static void main(String args[])
		{
		new ImageTest().playGame();
		}
	}