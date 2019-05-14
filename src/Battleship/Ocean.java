package Battleship;

import java.awt.*;

public class Ocean 
	{
	int x=0;
	int y=0;
	int size;
	int cellsAcross=0;
	int cellsDown=0;
	int selectX=0;
	int selectY=0;
	int[][] blue=new int[cellsAcross][cellsDown];
	int[][] deltaBlue = new int[cellsAcross][cellsDown];
	int[][] hitOrMiss;  // -1=miss,0=nothing, 1=hit
	boolean background=false;
	Color gridColor=Color.yellow;
	Rectangle oceanRect=null;
	
	Ocean(int a, int b, int c, Color d, boolean bg) 
		{
		x = a;
		y = b;
		size = c;
		gridColor = d;
		cellsAcross = 10;
		cellsDown = 10;
		background = bg;
		hitOrMiss = new int[cellsAcross][cellsDown];
		oceanRect = new Rectangle(x, y, cellsAcross*size, cellsDown*size);
		setBlueArray();
		}
	
	Ocean(int a, int b, int c, Color d, boolean bg, int ca, int cd)
		{
		this(a, b, c, d, bg);
		cellsAcross = ca;
		cellsDown = cd;
		setBlueArray();
		hitOrMiss = new int[cellsAcross][cellsDown];
		oceanRect = new Rectangle(x, y, cellsAcross*size, cellsDown*size);
		}
	
	public Point getPoint(int r, int c)
		{
		Point p = new Point(x+r*size,y+c*size);
		return p;
		}
	
	public boolean getHit(int r, int c)
		{
		if(hitOrMiss[r-1][c-1] == 1)
			return true;
		return false;
		}
	
	public void setMiss(int r, int c)
		{
		hitOrMiss[r-1][c-1]=-1;
		}
	
	public void setHit(int r, int c)
		{
		hitOrMiss[r-1][c-1]=1;
		}
	
	public void setBlueArray()
		{
		blue=new int[cellsAcross][cellsDown];
		deltaBlue = new int[cellsAcross][cellsDown];
		for(int s=0;s<cellsAcross;s++)
			{
			for(int t=0;t<cellsDown;t++)
				{
				blue[s][t]=(int)(Math.random()*62)+64;
				deltaBlue[s][t] =(int)(Math.random()*2);
				if(deltaBlue[s][t] != 1)
					deltaBlue[s][t]=-10;
				else
					deltaBlue[s][t]=10;
				}
			}
		}
	
	public void drawBlueArray(Graphics g)
		{
		for(int s=0;s<cellsAcross;s++)
			{
			for(int t=0;t<cellsDown;t++)
				{
				blue[s][t] += deltaBlue[s][t];
				if(blue[s][t] >= 128 || blue[s][t] <= 64)
					deltaBlue[s][t] *= -1;
				int width=x+size*cellsAcross;
				int height=y+size*cellsDown;
				for(int a=0;a<cellsAcross;a = a+1)
					{
					for(int b=0;b<cellsDown;b = b+1)
						{
						int c = blue[a][b];
						int d = size;
						for(int f=0;f<size/2;f++)
							{
							g.setColor(new Color(0,0,c));
							g.drawRect(f+x+a*size, f+y+b*size, d, d);
							d-=2;
							c+=5;
							if(c > 255) c = 255;
							}
						}
					}
				}
			}
		}
	
	public void draw(Graphics g)
		{
		if(background)
			{
			drawBlueArray(g);
			}
		int width=x+size*cellsAcross;
		int height=y+size*cellsDown;
		g.setColor(gridColor);
		
		for(int t=y;t<height;t = t + size)
			{
			for(int s=x;s<width;s = s + size)
				{
				g.drawRect(s, t, size, size);
				}
			}
		}
	
	public void drawHitOrMiss(Graphics g)
		{
		for(int t=0;t<hitOrMiss.length;t++)
			{
			for(int s=0;s<hitOrMiss[0].length;s++)
				{
				if(hitOrMiss[t][s] == 0)
					{
					continue;
					}
				if(hitOrMiss[t][s] == 1)
					{
					g.setColor(Color.red);
					}
				if(hitOrMiss[t][s] == -1)
					{
					g.setColor(Color.white);
					}
				g.fillOval(x+t*size+((size-10)/2), y+s*size+((size-10)/2), 10, 10);
				
				}
			}
		}
	
	public int getSquareSize() 
		{
		return size;
		}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void mouseUp(int a, int b)
		{
		selectX=a;
		selectY=b;
		}
	
	public Point snapTo(int a, int b)
		{
		a = a - x;
		b = b - y;
		a = ((int)(a / size))*size;
		b = ((int)(b / size))*size;
		return new Point(a+x, b+y);
		}
	
	public Point selectedSpot()
		{
		return new Point(selectX, selectY);
		}
	
	
	
	}
