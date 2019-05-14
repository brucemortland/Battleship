package Battleship;

import java.awt.*;
import java.awt.event.*;

// this class exists only to test the Connection class

public class CT extends GameSkeleton
	{
	static final long serialVersionUID = 0;
	String message;
	String rec="";
	Connection conn = new Connection();
	
	public void start()
		{
		message="";
		conn.start();
		}
	
	public void tick()
		{
		rec = conn.receive();
		}
	
	public void draw(Graphics g)
		{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.yellow);
		g.drawString(message, 30, 300);
		g.drawString(rec, 30, 400);
		}
	
	public void keyDown(int inKey)
		{
		if(KeyEvent.VK_ENTER == inKey)
			{
			conn.send(message);
			}
		else
			{
			message = message + (char)inKey;
			}
		repaint();
		}
	
	public static void main(String args[])
		{
		new CT().playGame();
		}
	}


