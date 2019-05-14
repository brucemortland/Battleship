
package Battleship;

import java.awt.*;

public class SettingsIcon extends GameSkeleton
	{
	double r = 10;
	double h = 300;
	double k = 300;
	double x;
	double y;
	static final long serialVersionUID = 0;
	
	
	
	public void draw(Graphics g, int e, int f)
		{
		double x=e;
		double y=f;
		r=10;
		h = x+((int)(r/2))+9;
		h = y+((int)(r/2))+9;
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 600);
	//	double theta=0;
		for(double theta=0;theta<360;theta+=30)
			{
			int[] a = new int[3];
			int[] b = new int[3];
			//point 1
			a[0]  = (int)( h + r *Math.cos(Math.toRadians(theta)));
			b[0]  = (int)( k + r*Math.sin(Math.toRadians(theta)));

			a[1]  = (int)( h + r *Math.cos(Math.toRadians(theta+120)));
			b[1]  = (int)( k + r*Math.sin(Math.toRadians(theta+120)));

			a[2]  = (int)( h + r *Math.cos(Math.toRadians(theta+240)));
			b[2]  = (int)( k + r*Math.sin(Math.toRadians(theta+240)));

			g.setColor(Color.red);
			Polygon p = new Polygon(a, b, 3);
			
			g.fillPolygon(p);
		
			g.drawRoundRect((int)x, (int)y-10, 30, 30,4,4);
			g.drawRoundRect((int)x-1, (int)y-11, 32, 32,4,4);
			g.setColor(Color.black);
			g.fillOval((int)x+9, (int)y, 10, 10);
			}
		}
	
	public static void main(String args[])
		{
		new SettingsIcon().playGame();
		}
	}