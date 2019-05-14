package Battleship;

import java.awt.*;
import java.awt.event.*;

public class ChatTest extends GameSkeleton
	{
	static final long serialVersionUID = 0;
	Chat chat = new Chat(50, 50, 200, 300, 20);
	
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
		chat.draw(g);
		}
	
	public void keyDown(int inKey)
		{
		if(KeyEvent.VK_Q == inKey)
			{
			System.exit(0);
			}
		chat.keyDown(inKey);
		repaint();
		}
	
	public static void main(String args[])
		{
		new ChatTest().playGame();
		}
	}