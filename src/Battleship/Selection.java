package Battleship;

import java.awt.*;

public class Selection 
	{
	Rectangle rect;
	Color color;
	String text;
	boolean selected;
	
	Selection(int a, int b, int c, int d, Color e, String f)
		{
		rect = new Rectangle(a,b,c,d);
		color = e;
		text = f;
		}
	
	public void draw(Graphics g)
		{
		if(selected)
			g.drawRect(rect.x-5, rect.y-5, rect.width, rect.height);
		g.setFont(new Font("Arial", Font.PLAIN,12));
		g.setColor(color);
		
		g.drawString(text, rect.x,rect.y+12);
		}
	
	public boolean mouseDown(int x, int y)
		{
		selected=false;
		if(rect.contains( x,  y))
			{
			System.out.println("Selection:"+rect);
			selected=true;
			}
		return selected;
		}
	
	
	}
