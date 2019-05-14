package Battleship;

public class ConnectionTest extends wire
	{
	boolean connected=false;
	public static void main( String args[] ) 
		{
		ConnectionTest ct = new ConnectionTest();
		ct.playGame();
		}
	
	public void playGame()
		{
		// set the ip and port. 
		set("127.0.0.1", 1000);

		// open the connection
		boolean goodConnection = open("client");
		
		if(!goodConnection)
			{
			goodConnection = open("server");
			if(goodConnection)
				{
				System.out.println("opened as server.");
				send("msg to client.");
				connected=true;
				}
			}
		else // connected as client
			{
			System.out.println("opened as client");
			send("msg to server.");
			connected=true;
			}
		String str = receive();
		System.out.println("received:"+str);
					      
		// close the connection
		close();  				
		}
	}
