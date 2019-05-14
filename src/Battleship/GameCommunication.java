package Battleship;

import java.awt.*;
import java.awt.event.*;

/* this class is used specifically to interface Connection class with Battleship game.
 * 
 * Commands
 * 
 * UN=<user name> // user name
 * CH=<message>   // send a chat message
 * FI=0208        // missile at row 2, column 8
 * HT=0208        // a hit at row 2, column 8
 * MS=0208		  // a miss at row 2, column 8
 * SU             // You sunk my battleship!
 * YW             // You win!
 * SP			  // Ships placed! I'm ready to play
 * 
 * 
 * 
 * 2019-Apr-03	BLM	created class
 * 2019-Apr-18	BLM	added sent messages buffering for messages attempted 
 * 					to send before connection established.
 * 
 */

public class GameCommunication extends Connection 
	{
	String ipAddress=null; // ip address to connect with
	Color borderColor=new Color(192,192,192);
	Color backgroundColor = new Color(0,0,0,164);
	Color textColor= Color.white;
	String inputText="";
	
	public void draw(Graphics g, int x, int y) 
		{
		if(ipAddress == null)
			{
			g.setFont(new Font("Arial",Font.PLAIN,16));
			g.setColor(textColor);
			g.drawString("Enter IP address of opponent.", x, y);
			g.setColor(Color.white);
			g.fillRect(x+230, y-20, 140, 30);
			g.setColor(Color.black);
			g.fillRect(x+231,y-19,138,28);
			g.setColor(Color.white);
			g.drawString(inputText,x+236,y);
			}
		}
	
	public void keyDown(int inKey)
		{
		if(Character.isDigit((char)inKey))
			{
			inputText=inputText+(char)inKey;
			}
		if(inKey == KeyEvent.VK_PERIOD)
			{
			inputText=inputText+(char)inKey;
			}
		if(inKey == KeyEvent.VK_BACK_SPACE)
			{
			inputText = inputText.substring(0, inputText.length()-1);
			}
		
		}
	
	int cmdCnt=0;
	String[] CommandsGoingOut = new String[1000];
	public void saveCommand(String text)
		{
		CommandsGoingOut[cmdCnt] = text;
		if(cmdCnt < 999)
			{
			cmdCnt++;
			}
		}
	
	public void moveText()
		{
		ipAddress = inputText;
		set(ipAddress, 1000); //ip address and port
		begin();
		Battleship.currentStatus = Battleship.PLACEYOURSHIPS;
		for(int t=0;t<cmdCnt;t++)
			{
			super.send(CommandsGoingOut[t]);
			}
		cmdCnt=0;
		}
	
	public boolean send(String s)
		{
		if(ipAddress == null)
			{
			saveCommand(s);
			}
		return super.send(s);
		}
	}
