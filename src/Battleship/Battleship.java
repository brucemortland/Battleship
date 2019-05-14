package Battleship;

import java.awt.*;

/*
 * Battleship - version of traditional Battleship game
 * 
 * created by Bruce Mortland
 * 
 * 2019-Mar-26	BLM	Created RowBoat, Ocean and Boat classes 							1 hr
 * 2019-Mar-27	BLM	Added snapTo function to force boats to align with ocean squares	.5 hr
 * 					Added boatrack and all five boats, lengths: 5, 4, 3, 3, 2			.25 hr
 * 					Added code to drag boats from boatrack to playing ocean				.5 hr	
 * 					Added code to switch boat horizontal/vertical with a mouseClick, 
 * 						but vertical boat doesnt draw 									.5 hr
 * 2019-Mar-28	BLM	Can now draw a vertical boat.										.5 hr
 * 2019-Mar-28	BLM	Began research on network connection								.25 hr
 * 2019-Mar-28	BLM Chat window installed, needs aligned to font.						1 hr
 * 2019-Apr-02	BLM	Created Ocean background											1.5 hr
 * 2019-Apr-02	BLM Chat window now wrapping text and aligning correctly.				2.0 hr
 * 2019-Apr-03	BLM	Renamed from RowBoat to Battleship
 * 2019-Apr-03	BLM	Found and extracted battleship images from web						1.5 hr.
 * 2019-Apr-03	BLM	Integrated wire and Connection classes, created ip entry screen		2.0 hr.
 * 2019-Apr-04	BLM	finished networking games											.5 hr.
 * 2019-Apr-04	BLM	added explosion and FI (fire missile) command						2 hr.
 * 2019-Apr-07	BLM added HI and MI commands, explosions to enemyOcean					2 hr.
 * 					added Thread to wait on port so that animation works
 * 2019-Apr-08	BLM made BoatRack disappear after all ships moved to ocean				.5 hr.
 * 2019-Apr-09 	BLM added code that sends hit or miss after receiving fire command		2 hr
 * 					added code to determine that ship is sunk
 * 2019-Apr-10	BLM	added code to make sunk boat gradually disappear					.5hr.
 * 2019-Apr-10	BLM	added status message to game										1.5hr
 * 2019-Apr-11	BLM worked on setting screen and color picker							3hr
 * 2019-Apr-13	BLM worked on setting screen and color picker 							2hr
 * 2019-Apr-13	BLM	created settings icon for button									1hr
 * 2019-Apr-14	BLM worked on buttons to move between settings and game screens			1hr
 * 2019-Apr-14	BLM	created splash screen												2hr
 * 2019-Apr-15	BLM	added ClickableOcean to select square to fire missile				2hr
 * 2019-Apr-17	BLM	modified game background and status message color					.5hr
 * 2019=Apr-18	BLM modified to allow buffering of messages sent to other 
 * 					user (GameConnection). Added code to only allow a thread 
 * 					to be started once. 												1hr
 * 2019-Apr-18	BLM	fixed problem with clicking on enemyOcean where it locked up
 * 					Array was being indexed improperly 								1hr
 * 2019-Apr-19	BLM	fixed problem where boats would not snap into place on ocean
 * 2019-Apr-20	BLM	created ship descriptions and pop-ups when you hover				2hr
 * 2019-Apr-26	BLM	added enemyShipsSunk counter in space where boatRack disappears	
 * 					worked on modifying ServerProcess and Connection code to get
 * 					MultiUserServer code working										2.5hr
 * 
 * one side needs to be server, another needs to be client. Is it possible to randomly switch between server and
 * and client on both sides and 'catch' each other when one is server and one is client? Especially if we spend a
 * longer period on server side and only a single or few tries on client 
 * 
 * Actually, the above problem was resolved much more easily. Connection 'tries' to connect as a client and fails, which 
 * which means there is no server. It then starts up as a server, waiting for the other sides.
 * 
 * 
 * To do 
 * 1. need to color and scale ship images for game. Need to integrate images into game.
 * 2. jar file did not work on student workstations
 * 3.three playing modes: 
 * 			direct mode - direct connect with another player using IP address
 * 			server mode- will pair player with another player on the server
 * 			single player mode - user will play the computer
 * 4. sometimes enemyOcean outline is in red.
 * 5. add code to GitHub
 * 6. create user documentation
 * 7. create UML documentation
 * 
 * 
 */

public class Battleship extends GameSkeleton
	{
	static final long serialVersionUID = 0;
	static final int DIRECTMODE=0;
	static final int SERVERMODE=1;
	static final int SINGLEPLAYERMODE=2;
	static int gameMode=DIRECTMODE;
	int enemyShipsSunk=0;
	int whoseTurn=0;
	Ocean heroOcean = new Ocean(10, 35, 30, Color.yellow, true);
	ClickableOcean enemyOcean = new ClickableOcean(360, 35, 20, Color.yellow, false);
	Ocean boatRack = new Ocean(10, 400, 30, Color.white, false, 5, 5);
	
	boolean enemyShipsPlaced=false;
	static Color statusColor = new Color(255,128,0);
	boolean gameOver=false;
	String[][] boatDescriptions = {
			{"USS Warrington","Cmdr. William Hagy", "1000 feet in length"},
			{"USS Texas","Cmdr. Jon Bayer", "800 feet in length"},
			{"USS Yorktown", "Capt. Josten Thippavong", "600 feet in length"},
			{"USS Nautilus", "Capt. Alexandr Larchenko", "600 feet in length"},
			{"USS Indianapolis", "Capt. David Corey", "400 feet in length"}
	};
	Boat[] boats = {
					new Boat(0, 0, 5, boatRack, boatDescriptions[0]),
					new Boat(0, 1, 4, boatRack, boatDescriptions[1]),
					new Boat(0, 2, 3, boatRack, boatDescriptions[2]),
					new Boat(0, 3, 3, boatRack, boatDescriptions[3]),
					new Boat(0, 4, 2, boatRack, boatDescriptions[4])
	};
	GameCommunication conn = new GameCommunication();
	Chat chat = new Chat(300, 400, 250, 180, 14);
	Explosion explosion = new Explosion(); // animation that executes when a missile hits a ship.
	boolean thereAreBoatsOnTheBoatRack=true;
	static final int NOMESSAGE=0;
	static final int PLACEYOURSHIPS=1;
	static final int ENEMYPLACINGSHIPS=2;
	static final int YOURTURN=3;
	static final int ENEMYTURN=4;
	static final int YOUSUNKENEMYSHIP=5;
	static final int ENEMYSUNKYOURSHIP=6;
	static final int GAMEOVERYOUWIN=7;
	static final int GAMEOVERYOULOSE=8;
	static final int ENTERIPADDRESS=9;
	static int currentStatus=ENTERIPADDRESS;
	
	public void start()
		{
		explosion.start(-100,-100, 30);
		Settings.define(heroOcean, enemyOcean, boatRack, chat);
		}
	
	public void tick()
		{
		if(conn.isWireReady()==false) //need an ip address
			currentStatus = ENTERIPADDRESS;
		else if(areThereBoatsOnTheBoatRack() == true) // need to place boats
			currentStatus = PLACEYOURSHIPS;
		else if(enemyShipsPlaced== false && whoseTurn==0)
			currentStatus = ENEMYPLACINGSHIPS;
		else
			currentStatus = NOMESSAGE;
		
		String s=null;
		if(conn != null)
			s = conn.read();
	
		if(s != null) // we received something
			{
			if(		s.startsWith("FI=")) 	fireCommand(s);
			else if(s.startsWith("HI=")) 	hitCommand(s);
			else if(s.startsWith("MI=")) 	missCommand(s);
			else if(s.startsWith("CH=")) 	chat.addMessageToList(s.substring(3));
			else if(s.startsWith("SP" )) 	enemyShipsArePlaced();
			else if(s.startsWith("IG"))      enemyGoesFirst();
			else if(s.compareTo ("SU")==0) 	shipIsSunk();
			else if(s.compareTo ("YW")==0) 	currentStatus=GAMEOVERYOUWIN;
			else 							chat.addMessageToList(s);
			}
		
		//check to see if there are any of my ships left
		gameOver=true;
		for(int t=0;t<boats.length;t++)
			{
			if(boats[t].sunk == false)
				gameOver = false;
			}
		if(gameOver) 
			{
			currentStatus = GAMEOVERYOULOSE;
			conn.send("YW");
			}
		explosion.tick();
		}

	public void enemyGoesFirst()
		{
		whoseTurn=2;
		}
	public boolean areThereBoatsOnTheBoatRack()
		{
		for(int t=0;t<boats.length;t++)
			{
			if(boats[t].getOcean() == boatRack)
				return true;
			}
		return false;
		}

	public void enemyShipsArePlaced()
		{
		enemyShipsPlaced=true;
		if(thereAreBoatsOnTheBoatRack==true)
			{
			currentStatus=NOMESSAGE;
			}
		}
	
	public void shipIsSunk()
		{
		currentStatus=YOUSUNKENEMYSHIP;
		enemyShipsSunk++;
		}
	
	public void fireCommand(String s)
		{
		int r = getRow(s);
		int c = getColumn(s);
		Point p = heroOcean.getPoint(r, c);
		
		/* Check to see if we hit a boat
		 * if we hit a boat, send a 'HI' (hit) command
		 * if boat is sunk, send 'SU' (sunk) command 
		 * if we missed, send a 'MI' (miss) command
		 */
		boolean boatHit=false;
		for(int t=0;t<boats.length;t++)
			{
			if(boats[t].collision(p))
				{
				boatHit=true;
				heroOcean.setHit(r, c);
				explosion.start((int)p.getX(), (int)p.getY(), heroOcean.size);
				conn.send("HI"+s.substring(2));
				if(boats[t].isSunk()) // check to see if boat is sunk!
					{
					currentStatus=ENEMYSUNKYOURSHIP;
					conn.send("SU");
					}
				}
			}
		if(boatHit == false) // did not hit a boat!
			{
			conn.send("MI"+s.substring(2));
			}
		whoseTurn=1;
		}

	public void hitCommand(String s)
		{
		int r = getRow(s);
		int c = getColumn(s);
		Point p = enemyOcean.getPoint(r, c);
		enemyOcean.setHit(r, c);
		explosion.start((int)p.getX(), (int)p.getY(), enemyOcean.size);
		}
	
	public void missCommand(String s)
		{
		int r = getRow(s);
		int c = getColumn(s);
		enemyOcean.setMiss(r, c);
		}
	
	public int getRow(String s)
		{
		return Integer.parseInt(s.substring(3,5));
		}
	

	public int getColumn(String s)
		{
		return Integer.parseInt(s.substring(5,7));
		}
	
	public void drawBackground(Graphics g)
		{
		int dC=1;
		int color=0;
		for(int y=0;y<600;y=y+2)
			{
			g.setColor(new Color(color, 0, color));
			g.drawLine(0, y, 600, y);
			g.drawLine(0, y+1, 600, y+1);
			color=color+dC;
			if(color == 155 || color== 0)
				dC=dC*-1;
			}
		}
	
	public void draw(Graphics g)
		{
		if(Splash.showSplashScreen)
			{
			Splash.draw(g);
			return;
			}
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawBackground(g);
		heroOcean.draw(g);
		enemyOcean.draw(g);
		if(thereAreBoatsOnTheBoatRack)
			{
			boatRack.draw(g);
			}
		else
			{
			// draw how many enemy ships have been sunk
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.setColor(statusColor);
			g.drawString("Enemy", 35, 435);
			g.drawString("Ships", 35, 455);
			g.drawString("Sunk", 35, 475);
			g.setFont(new Font("Arial", Font.BOLD, 64));
			g.drawString(""+enemyShipsSunk, 107, 470);
			for(int t=0;t<5;t++)
				{
				g.drawRect(23+t,410+t, 130-(t*2), 80-(t*2));
				}
			g.drawLine(95, 410, 95, 490);
			
			
			}

		chat.draw(g);
		for(int t=0;t<boats.length;t++)
			{
			boats[t].draw(g);
			}
		heroOcean.drawHitOrMiss(g);
		enemyOcean.drawHitOrMiss(g);
		explosion.draw(g);

		// draw whose turn
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.setColor(statusColor);

		String[] turn = {"",""};
		if(whoseTurn == 1)  
			{
			turn[0]="Your";
			turn[1]="Turn.";
			}
		if(whoseTurn == 2)
			{
			turn[0]="Enemy";
			turn[1]="Turn.";
			}
		g.drawString(turn[0], 200, 400);
		g.drawString(turn[1], 200, 430);
		
		// put the status on the screen
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.setColor(statusColor);
		String[] msg = getStatus();
		for(int t=0;t<msg.length;t++)
			{
			g.drawString(msg[t], 400, 300+t*20);
			}
		Settings.draw(g, 520, 290, conn);
		}

	String statmsgs[][] = 	{	{"","",""}, // no message
								{"Please drag your", "ships on the", "blue ocean."},
								{"Your enemy is ", "placing their", "ships."},
								{"Your turn.", "", ""},
								{"Enemy's turn.", "", ""},
								{"You sunk an", "enemy ship!", ""},
								{"Enemy sunk", "your ship!", ""},
								{"Game Over!", "You win!", ""},
								{"Game Over!", "You lose!", ""},
								{"Go to settings", "Enter IP address."}
							};
	
	public String[] getStatus()
		{
		return statmsgs[currentStatus];	
		}
	public void keyDown(int inKey)
		{
		Splash.keyDown(inKey);
		if(Splash.showSplashScreen == true) return;
			Settings.keyDown(inKey, conn);
		if(!Splash.showSplashScreen && !Settings.showSettings)	
			{
			String s = chat.keyDown(inKey); // if null returned no message to send (user didn't hit enter key)
			if(s != null)
				{
				conn.send(s);
				}
			}
		repaint();
		}
	public void mouseDown(int x, int y)
		{
		
		// boats have all been placed, they are now locked in place
		Settings.mouseDown(x, y, conn);
		if(!thereAreBoatsOnTheBoatRack && whoseTurn==1) // only during my turn
			{
			if(enemyOcean.oceanRect.contains(x, y))
				{
				String s = enemyOcean.mouseDown(x, y);
				conn.send(s);
				whoseTurn=2;
				}
			}
		else
			{
			for(int t=0;t<boats.length;t++)
				{
				boats[t].mouseDown(x, y);
				}
			}
		Splash.mouseDown(x, y);
		}
	
	public void mouseMove(int x, int y)
		{
		for(int t=0;t<boats.length;t++)
			boats[t].mouseMove(x, y);
		}
	
	public void mouseDrag(int x, int y)
		{
		for(int t=0;t<boats.length;t++)
			boats[t].mouseDrag(x, y);
		}
	
	public void mouseUp(int x, int y)
		{
		// are we mouseUpping because of a drag or a click? 
		// isMouseClick = true then it's a click, 
		// isMouseClick = false then it's a drag

		for(int t=0;t<boats.length;t++)
			boats[t].mouseUp(heroOcean, boats);
			
		thereAreBoatsOnTheBoatRack = areThereBoatsOnTheBoatRack();
		if(thereAreBoatsOnTheBoatRack== false && whoseTurn==0)
			{
			conn.send("IG");
			whoseTurn=1;
			}
		}
		
		
	
	public static void main(String args[])
		{
		new Battleship().playGame();
		}
}