
package Battleship;

import java.awt.*;
import java.awt.event.*;

public class Explosion extends GameSkeleton
	{
	static final long serialVersionUID = 0;
	int count=30;
	int    dx[] = new int[count];
	int    dy[] = new int[count];
	int    dw[] = new int[count];
	int    mw[] = new int[count];
	int     x[] = new int[count];
	int     y[] = new int[count];
	int     w[] = new int[count];
	int trans[] = new int[count];
	
	
	public void start(int a, int b, int s)
		{
		for(int t=0;t<count;t++)
			{
			 x[t] = (int)(Math.random()*20+a)-s-5;
			 y[t] = (int)(Math.random()*20+b)-s-5;
			 w[t] = (int)(Math.random()*(s-5));
			 trans[t] = (int)(Math.random()*255);
			}
		}
	
	public void tick()
		{
		for(int t=0;t<count;t++)
			{
			trans[t]=trans[t]-5;
			if(trans[t] < 0) trans[t]=0;
			}
		}
	
	public void draw(Graphics g)
		{
//		if(x[0] >0)System.out.println("  x="+x[0]+", y="+y[0]+", w="+w[0]);
		
		
		for(int t=0;t<count;t++)
			{
			int b = (int)(Math.random()*100);
			if(b < 50) g.setColor(new Color(64, 64, 64, trans[t]));
			else       g.setColor(new Color(255, 101, 1, trans[t]));
			g.fillOval(x[t], y[t], w[t], w[t]);
			}
		}
	}