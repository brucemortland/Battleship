package Battleship;

import java.awt.*;

public class Swatch 
	{
	String label;
	Color swatch;
	Rectangle rect;
	boolean selected=false;
	
	Swatch(String a, Color b, int c, int d)
		{
		label=a;
		swatch=b;
		rect = new Rectangle(c, d, 20, 20);
		}
	
	public void draw(Graphics g)
		{
		if(selected)
			{
			g.setColor(Color.white);
			g.fillRect(rect.x-2, rect.y-2, rect.width+4, rect.height+4);
			}
		g.setColor(swatch);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.setColor(Color.white);
		g.drawString(label, rect.x+rect.width+10, rect.y+14);

		}
	
	public boolean mouseDown(int x, int y)
		{
		selected=false;
		if(rect.contains(x, y))
			{
			selected=true;
			}
		return selected;
		}
	}
