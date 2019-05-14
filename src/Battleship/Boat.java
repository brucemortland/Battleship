package Battleship;

import java.awt.*;

public class Boat 
	{
	// 
	int x=0; 
	int y=0; 
	int xBeforeDragging;
	int yBeforeDragging;
	Ocean oceanBeforeDragging=null;
	
	int length=0; 
	int girth=0; 
	Ocean myOcean=null;
	boolean horizontal=true; // false means vertical
	Color boatTrimColor = Color.green;
	Color boatColor = Color.gray;
	Rectangle boatRect;
	boolean sunk=false;
	int transparency=255;
	boolean selected=false;
	boolean boatText=false;
	String[] description;
	Image shipImage=null;
	Boat(int a, int b, int l, Ocean o, String[] d)
		{
		myOcean = o;
		     x = a*myOcean.getSquareSize()+myOcean.getX();
		     y = b*myOcean.getSquareSize()+myOcean.getY(); // first def of y
		 girth =   myOcean.getSquareSize(); // first def of girth
		length = l*myOcean.getSquareSize();
		boatRect = new Rectangle(x, y, length, girth);
		description = d;
/*		girth = ((int) 
					(length * 
					((double)i.getHeight(null)) / 
					((double)i.getWidth(null))));  // second def of girth
		y = b*myOcean.getSquareSize()+myOcean.getY()+((myOcean.getSquareSize()-girth)/2);
		image = i.getScaledInstance(length, girth, Image.SCALE_DEFAULT);
*/
		}
	
	public boolean collision(Point p)
		{
		
		if(boatRect.contains((int)(p.getX()-5),(int)(p.getY())-5))
			{
			return true;
			}
		return false;
		}
	
	public boolean isSunk()
	{
		/* for length of boat, check ocean to see if 
		 * there is a hit for each of the squares that
		 * the boat sits on.
		 */
		boolean boatIsSunk=true;
		if(horizontal) // boat is horizontal
			{
			// find out length of boat in 'squares'
			int lengthInSquares = length/myOcean.getSquareSize();
			// which column is boat's left edge
			int column = (x-myOcean.getX())/myOcean.getSquareSize()+1;
			// which row is boat in
			int row = (y-myOcean.getY())/myOcean.getSquareSize()+1;
			
			// ok, now check the five squares
			for(int c=column;c<column+lengthInSquares;c++)
				{
				if(myOcean.getHit(c, row) == false) // nope, not sunk yet!
					{
					boatIsSunk=false;
					}
				}
			}
		else // boat is vertical
			{
			// find out length of boat in 'squares'
			int lengthInSquares = length/myOcean.getSquareSize();
			// which column is boat's left edge
			int row = (y-myOcean.getY())/myOcean.getSquareSize()+1;
			// which row is boat in
			int column = (x-myOcean.getX())/myOcean.getSquareSize()+1;
			
			// ok, now check the five squares
			for(int r=row;r<row+lengthInSquares;r++)
				{
				if(myOcean.getHit(column, r) == false) // nope, not sunk yet!
					{
					boatIsSunk=false;
					}
				}
			}
		
		if(boatIsSunk==true) 	sunk=true;
		
		return boatIsSunk;
	}
	
	public static boolean overlap(Boat[] b)
	{
	// check to see if boats overlap
	for(int t=0;t<b.length;t++)
		{
		for(int s=0;s<b.length;s++)
			{
			if(b[t].boatRect.intersects(b[s].boatRect) &&s!=t)
				return true;
			}
		}
	return false;
	}
	
	public void draw(Graphics g)
		{
		Polygon[] p;
		if(horizontal) 
			{
			p = createHorizontalBoatPolygon();
			boatRect = new Rectangle(x, y, length, girth);

			}
		else
			{
			p = createVerticalBoatPolygon();
			boatRect = new Rectangle(x, y, girth, length);
			}
		
		g.setColor(	new Color(	boatTrimColor.getRed(),
								boatTrimColor.getGreen(), 
								boatTrimColor.getBlue(),
								transparency
							));
		g.fillPolygon(p[0]);

		g.setColor(	new Color(	boatColor.getRed(),
								boatColor.getGreen(), 
								boatColor.getBlue(),
								transparency
							));
		g.fillPolygon(p[1]);
		if(sunk ) 	transparency-=5;
		if(transparency < 0)
			transparency=0;
		if(boatText)
			drawBoatDescription(g);
	//	g.drawImage(image, x, y,null);
		
		
		}
	
	public void drawBoatDescription(Graphics g)
		{	
		// draw the line connecting the ship to the text box
		g.setColor(Color.black);
		int bdX = boatRect.x+(boatRect.width/2);
		int bdY = boatRect.y+(boatRect.height/2);
		g.drawLine(	bdX, bdY-1, bdX+100, bdY-100);
		g.drawLine(	bdX, bdY-2, bdX+100, bdY-101);
		g.drawLine(	bdX, bdY-3, bdX+100, bdY-102);
		g.drawLine(	bdX, bdY-3, bdX+100, bdY-103);
		g.drawLine(	bdX, bdY-2, bdX+100, bdY-104);
		g.drawLine(	bdX, bdY-1, bdX+100, bdY-105);
		g.drawLine(	bdX, bdY, bdX+100, bdY-106);
		g.fillOval(bdX-6, bdY-6, 10, 10);
		g.fillRoundRect(bdX+80, bdY-120, 220, 80, 10, 10);
		g.setColor(Color.white);
		g.fillRoundRect(bdX+85, bdY-115, 210, 70, 10, 10);
		g.setColor(Color.black);
		g.setFont(new Font("Times-Roman", Font.PLAIN, 18));
		for(int t=0;t<description.length; t++)
			{
			g.drawString(description[t], bdX+90, bdY-120+(t*18)+27);	
			}
		}
	
	public Polygon[] createHorizontalBoatPolygon()
		{
		Polygon[] p = new Polygon[2];
		int[] px = {x, x+girth, x+length-girth, x+length, x+length-girth, x+girth};
		int[] py = {y+girth/2, y, y, y+girth/2, y+girth, y+girth};
		p[0] = new Polygon(px, py, 6);
		
		int[] bx = {x+2, x+girth, x+length-girth, x+length, x+length-girth, x+girth-2};
		int[] by = {y+girth/2, y+2, y+2, y+girth/2, y+girth-2, y+girth-2};
		p[1] = new Polygon(bx, by, 6);
		return p;
		}
	
	public Polygon[] createVerticalBoatPolygon()
		{
		Polygon[] p = new Polygon[2];
		int[] px = {x+girth/2, x, x, x+girth/2, x+girth, x+girth};
		int[] py = {y, y+girth, y+length-girth, y+length, y+length-girth, y+girth};
		p[0] = new Polygon(px, py, 6);
		

		int[] bx = {x+girth/2, x+2, x+2, x+girth/2, x+girth-2, x+girth-2};
		int[] by = {y+2, y+girth, y+length-girth, y+length, y+length-girth, y+girth-2};
		p[1] = new Polygon(bx, by, 6);
		
		return p;
		}
	
	boolean mouseClick=false;
	public void mouseDown(int a, int b) 
		{
		xBeforeDragging = x;
		yBeforeDragging = y;
		oceanBeforeDragging = myOcean;
		if(boatRect.contains(a,b))
			{
			selected=true;
			mouseClick=true;
			}
		}
	
	public void mouseMove(int a, int b)
		{
		if(boatRect.contains(a,b))
			boatText=true;
		else
			boatText=false;
		}
	public void mouseDrag(int a, int b)
		{
		mouseClick=false;
		if(selected)
			{
			x=a;
			y=b;
			}
		}
	
	public void mouseUp(Ocean h, Boat[] boats)
		{
		
		if(selected)
			{
			if(mouseClick)
				{
				switchDirection();
				}
			snapTo(h);
			myOcean = h;
			System.out.println("Boatrect"+boatRect);
			System.out.println("oceanRect"+h.oceanRect);
			selected=false;
			if(Boat.overlap(boats)==true || !h.oceanRect.contains(boatRect))
				{
				x = xBeforeDragging;
				y = yBeforeDragging;
				myOcean = oceanBeforeDragging;
				}
			}
		mouseClick=false;
		}
	
	public void switchDirection()
		{
		if(selected)
			horizontal = !horizontal;
		}
	
	public void setOcean(Ocean o)
		{
		myOcean = o;
		snapTo(o);
		}
	public void snapTo(Ocean o)
		{
		Point p = o.snapTo(x,  y);
		x = (int) p.getX();
		y = (int) p.getY();
		if(horizontal)
			{
			boatRect = new Rectangle(x, y, length, girth);
			}
		else
			{
			boatRect = new Rectangle(x, y, girth, length);
			}
		}
	public Ocean getOcean()
		{
		return myOcean;
		}
	}