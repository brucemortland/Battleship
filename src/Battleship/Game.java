
package Battleship;

import java.awt.*;
import java.awt.event.*;

public class Game extends GameSkeleton
	{
	static final long serialVersionUID = 0;
	int xCoordinate;
	int squareSize;
	
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
		g.setColor(Color.yellow);
		g.fillRect(xCoordinate, 300, squareSize, squareSize);
		}
	
	public void whichKey(int inKey)
		{
		if(KeyEvent.VK_S == inKey)
			{
			start();
			}
		if(KeyEvent.VK_Q == inKey)
			{
			System.exit(0);
			}
		if(KeyEvent.VK_LEFT == inKey)
			{
			xCoordinate = xCoordinate - 10;
			squareSize = squareSize -1;
			}
		if(KeyEvent.VK_RIGHT == inKey)
			{
			xCoordinate = xCoordinate + 10;
			squareSize = squareSize -1;
			}
		repaint();
		}
	
	public static void main(String args[])
		{
		new Game().playGame();
		}
	}