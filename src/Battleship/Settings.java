package Battleship;

import java.awt.*;

public class Settings 
	{
	static Ocean myOcean;
	static Ocean enemy;
	static Ocean rack;
	static Chat chat;
	
	final static int MYOCEAN=0;
	final static int ENEMYOCEAN=1;
	final static int BOATRACK=2;
	final static int STATUSMSG=3;
	final static int CHATBACKGROUND=4;
	final static int CHATBORDER=5;
	final static int CHATTEXT=6;
	final static int CHATTYPING=7;
	
	static Swatch[] swatches = new Swatch[8];
	static ColorPicker cp = new ColorPicker();
	static int selectedSwatch;
	static boolean showSettings=false;
	static Selection[] selection = new Selection[3];
	static Selection settingsExit = new Selection(500,550,60,24,Color.white,"to Game");
	static Selection settingsEnter = new Selection(250, 550, 32, 32, Color.black,"");
	
	
	public static void drawSettingsIcon(Graphics g, int e, int f)
	{
	if(showSettings) return;
	double x=e;
	double y=f;
	double r=10;
	double h = x+((int)(r/2))+10;
	double k = y+((int)(r/2))+10;
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
	
		g.drawRoundRect((int)x+1, (int)y+1, 30, 30,4,4);
		g.drawRoundRect((int)x, (int)y, 32, 32,4,4);
		g.setColor(Color.black);
		g.fillOval((int)x+9, (int)y+10, 10, 10);
		}
	}

	public static void define(Ocean a, Ocean b, Ocean c, Chat d)
		{
		myOcean = a;
		enemy = b;
		rack = c;
		chat = d;
		refreshSwatches();
		refreshSelections();
		
		}
	
	public static void refreshSelections()
	{
		selection[Battleship.DIRECTMODE]= new Selection(190,450,90,25, Color.white, "Direct");
		selection[Battleship.SERVERMODE]= new Selection(290,450,90,25, Color.white, "Server");
		selection[Battleship.SINGLEPLAYERMODE]= new Selection(390,450,90,25, Color.white, "Single-Player");
		selection[Battleship.gameMode].selected=true;			
	}
	
	public static void refreshSwatches()
	{
		swatches[0]=	new Swatch("Your Ocean Grid Color",	myOcean.gridColor, 			50, 102);
		swatches[1]=	new Swatch("Enemy Ocean Grid Color",	enemy.gridColor, 			50, 132);
		swatches[2]=	new Swatch("Boat Rack Grid Color",	rack.gridColor, 				50, 162);
		swatches[3]=	new Swatch("Status Message Color",	Battleship.statusColor, 		50, 192);
		swatches[4]=	new Swatch("Chat Background Color",	chat.backgroundColor, 		50,	222);
		swatches[5]=	new Swatch("Chat Border Color",		chat.borderColor, 			50,	252);
		swatches[6]=	new Swatch("Chat Text Color",		chat.textColor, 				50, 282);
		swatches[7]=	new Swatch("Chat Typing Grid Color",	chat.typingBackgroundColor,	50, 312);

	}
	public static void draw(Graphics g, int a, int b, GameCommunication conn)
		{
		if(!showSettings)
			{
			settingsEnter.draw(g);
			drawSettingsIcon(g, settingsEnter.rect.x, settingsEnter.rect.y);
			return;
			}

		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 600);

		for(int t=0;t<swatches.length;t++)
			{
			swatches[t].draw(g);
			}
		
		
		g.setColor(Color.white);
		g.drawString("Game mode: ", 50, 460);
		
		selection[Battleship.gameMode].selected=true;
		for(int t=0;t<3;t++)
			{
			selection[t].draw(g);
			}
		
		cp.draw(g);

		conn.draw(g, 120,500);
		g.setColor(Color.red);
		g.fillOval(	settingsExit.rect.x-10, settingsExit.rect.y-10, 
					settingsExit.rect.width+10, settingsExit.rect.height+10);
		settingsExit.draw(g);
		
		}
	
	public static void keyDown(int inKey, GameCommunication conn)
		{
		if(showSettings)
			conn.keyDown(inKey);
		}
	
	public static void mouseDown(int x, int y, GameCommunication conn)
		{		
		if(!showSettings && settingsEnter.mouseDown(x, y))
			{
			showSettings=!showSettings;
			settingsEnter.selected=false;
			}
		// did the user want to change a color?
		for(int t=0;t<swatches.length;t++)
			{
			if(swatches[t].mouseDown(x, y))
				{
				cp.start(); // show colors now!
				selectedSwatch = t;
				}
			}
		Color selectedColor = cp.mouseDown(x, y);
		if(selectedColor != null)
			{
			assignNewColor(selectedSwatch, selectedColor);
			refreshSwatches();
			}
		
		// did they want to change a game mode
		for(int t=0;t<selection.length;t++)
		{
		if(selection[t].mouseDown(x, y))
			{
			Battleship.gameMode=t;
			}
		}
		if(showSettings && settingsExit.mouseDown(x, y))
			{
			
			conn.moveText();
			showSettings=!showSettings;
			settingsExit.selected=false;
			}
		
		
		
		}
	
	public static void assignNewColor(int ss, Color c)
		{
		// figure out what was selected
		if(ss == MYOCEAN)  			
			myOcean.gridColor=c;
		if(ss == ENEMYOCEAN)  		
			enemy.gridColor=c;
		if(ss == BOATRACK)  			
			rack.gridColor=c;
		if(ss == STATUSMSG)  		
			Battleship.statusColor=c;
		if(ss == CHATBACKGROUND)  	
			chat.backgroundColor=c;
		if(ss == CHATBORDER)  		
			chat.borderColor=c;
		if(ss == CHATTEXT)  			
			chat.textColor=c;
		if(ss == CHATTYPING)  		
			chat.typingBackgroundColor=c;
		}
	}