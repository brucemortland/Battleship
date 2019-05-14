package Battleship;

import java.awt.*;

public class ColorPicker {
	
	Swatch[] colors = {	
			new Swatch("red",		Color.red, 		400,	100),
			new Swatch("orange",	Color.orange, 	400,	130),
			new Swatch("yellow",	Color.yellow, 	400,	160),
			new Swatch("green",		Color.green, 	400,	190),
			new Swatch("blue",		Color.blue, 	400,	220),
			new Swatch("magenta",	Color.magenta, 	400,	270),
			new Swatch("gray",		Color.gray, 	400,	300),
			new Swatch("black",		Color.black, 	400,	330),
			new Swatch("white",		Color.white, 	400,	360),
			new Swatch("pink",		Color.pink, 	400,	390)
		};
	boolean showColorPicker=false;
	
	public void start()
		{
		showColorPicker=true;
		}
	
	public void draw(Graphics g)
		{
		if(showColorPicker)
			{
			for(int t=0;t<colors.length;t++)
				{
				colors[t].draw(g);
				}
			}
		}
	
	public Color mouseDown(int x, int y)
		{
		for(int t=0;t<colors.length;t++)
			{
			boolean selected = colors[t].mouseDown(x, y);
			if(selected==true)
				{
				showColorPicker=false;
				return colors[t].swatch;
				}
			}
		return null;
		}
	}
