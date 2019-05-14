package Battleship;

public class ReceiveThread extends Thread
	{
	String textFromPort=null;
	GameCommunication gc;
	ReceiveThread(GameCommunication a)
		{
		gc = a;
		start();
		}
	
	public String readPort()
		{
		String a;
		a = textFromPort;
		textFromPort=null;
		return a;
		}
	
	public void run()
		{
			while(true)
			{
			if(textFromPort == null)
				{
				textFromPort=gc.receive();
				}
			}
		}
	}
