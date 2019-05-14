package Battleship;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class Chat
	{
	int x=0;
	int y=0;
	int width=0;
	int height=0;
	String typing="";
	String[] messages= new String[100]; // maximum number of messages is 100
	int textFontSize = 12;
	Font textFont = new Font("Arial", Font.BOLD, textFontSize);
	Color        textColor = Color.black;
	Color  backgroundColor = Color.white;
	Color      borderColor = Color.red;
	Color typingBackgroundColor = Color.gray;
	
	Chat(int a, int b, int c, int d, int tfs)
		{
		x = a;
		y = b;
		width=c;
		height = d;
		textFontSize = tfs;
		textFont = new Font("Arial", Font.PLAIN,textFontSize);
		for(int x=0;x<100;x++)
			messages[x]="";
		}
	
	// calculates the heights of the line based on the current font. 
	Graphics2D g2d = null;
	public Rectangle2D lineBox(Graphics g, String str)
		{
		if(g != null)
			{
			g2d = (Graphics2D) g;
			}
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS, 
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        Rectangle2D r2d = g2d.getFontMetrics(textFont).getStringBounds(str, g2d);
		return r2d;
		}
	
	
	
	public void draw(Graphics g)
		{
		Rectangle2D tRect = lineBox(g, "That"); // "That" is just sample text to get height of line
		int vert = (int)tRect.getHeight();
		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);
		g.setColor(typingBackgroundColor);
		g.fillRect(x, y+height-vert-2, width, vert+2);
		
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);
		g.drawLine(x, y+height-vert-2, x+width, y+height-vert+2);
		g.setFont(textFont);
		g.setColor(textColor);

		int j = y+height-6;
		j = j - vert; // don't want text in textbox, typing var will go there!
		for(int t=messages.length-1;t>0 && j-vert>y ;t--)
			{
			if(messages[t] != null)
				{
				g.drawString(messages[t], x+5, j);
				}
			j = j - vert;
			}
		g.drawString(typing, x+5, y+height-6);
		}
	
	// make room for a new message at the bottom of the list
	public void shiftMessagesUp()
		{
		for(int t=0; t<messages.length-1; t++)
			{
			messages[t] = messages[t+1];
			}
		messages[messages.length-1]="";
		}
	
	// called from parent program. captures individual keystrokes
	public String keyDown(int inKey) 
		{
		if(inKey == KeyEvent.VK_BACK_SPACE)
			{
			if(typing.length() > 0)
				{
				typing = typing.substring(0,typing.length()-1);
				}
			}
		else if(inKey == KeyEvent.VK_ENTER)
			{
			if(typing.length() > 0)
				{
				addMessageToList(typing);
				String a = typing;
				typing="";
				return a;
				}
			}
		else
			{
			typing = typing + (char) inKey;
			}
		return null;
		}
	
	public void addMessageToList(String msg)
	{
		String stillText = msg;
		while(stillText.length() > 0)
			{
			Rectangle2D w;
			// add characters to fill line
			int idx = stillText.length()+1;
			do
				{
				idx--;
				String s = stillText.substring(0, idx);
				w  = lineBox(null, s);
				} while(w.getWidth() > width);
			
			// ok we have a full width of characters or we have hit end of string
			// let's back up to the previous non-alpha non-numeric char
			if(stillText.length() != idx)
				{
				int i = idx-1;
				while(i > 0)
					{
					if(	Character.isAlphabetic(stillText.charAt(i)) ||
						Character.isDigit     (stillText.charAt(i)) )
						{
						i--;
						}
					else
						{
						break;
						}
					}
				idx=i;	
				}
			msg=stillText.substring(0,idx);
			shiftMessagesUp();
			messages[messages.length-1]=msg;
			stillText = stillText.substring(idx);
			}
		}
	}
