package Battleship;

import java.awt.*;

/* BattleshipAI 
 * 
 * This module is meant to connect with a Battleship game and play the game Battleship against the user.
 */

public class BattleshipAI extends Connection
	{
	private static final long serialVersionUID = 1L;
	public void run()
		{
		while(true)
			{
			try {
				System.out.println("executing thread");
				Thread.sleep(5000);
				}
			catch(Exception e)
				{
				System.out.println("Thread failed.");
				}
			}
		}
	}