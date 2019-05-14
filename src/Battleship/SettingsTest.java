
package Battleship;

import java.awt.*;
import java.awt.event.*;

public class SettingsTest extends GameSkeleton
	{
	static final long serialVersionUID = 0;
	int xCoordinate;
	int squareSize;
	
	public void start()
		{
		}
	
	public void tick()
		{
		}
	
	public void draw(Graphics g)
		{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Settings.draw(g, 520, 300);
		}
	
	public void mouseDown(int x, int y)
		{
		Settings.mouseDown(x,y);
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
		new SettingsTest().playGame();
		}
	}