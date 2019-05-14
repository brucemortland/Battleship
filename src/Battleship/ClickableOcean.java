package Battleship;

import java.awt.*;

public class ClickableOcean extends Ocean
	{
	ClickableOcean(int a, int b, int c, Color d, boolean bg) 
		{
		super(a, b, c, d, bg);
		}

	public String mouseDown(int a, int b)
		{
		// which cell was chosen on the ocean
		int cX = (a-x) / size + 1;
		int cY = (b-y) / size + 1;
		
		String s = String.format("FI=%02d%02d", cX,cY);
		return s;
		}
	}
