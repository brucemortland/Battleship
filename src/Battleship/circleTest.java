
package Battleship;

import java.awt.*;
import java.awt.event.*;

public class circleTest extends GameSkeleton
	{
	double theta=0;
	double r = 100;
	double h = 300;
	double k = 300;
	static final long serialVersionUID = 0;
	
	
	public void start()
		{
		
		}
	
	public void tick()
		{
		theta++;
		
		}
	
	boolean drawBackground=false;
	public void draw(Graphics g)
		{
		if(drawBackground == false)
		{
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 600);;
		drawBackground=true;
		}
		g.setColor(Color.red);
		int x  = (int)( h + r *Math.cos(Math.toRadians(theta)));
		int y  = (int)( k + r*Math.sin(Math.toRadians(theta)));
		g.fillRect(x,  y,  5,  5);
		
		for(double a=theta,b=0;a<theta+255;a++,b++)
			{
			System.out.println(theta);
			x  = (int)( h + r *Math.cos(Math.toRadians(theta)));
			y  = (int)( k + r*Math.sin(Math.toRadians(theta)));
			g.setColor(new Color(0,255,0,(int)b));
			g.drawLine((int)h,(int) k, x, y);
			}
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
		
		}
	
	public static void main(String args[])
		{
		new circleTest().playGame();
		}
	}