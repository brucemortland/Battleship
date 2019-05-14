package Battleship;

/* this class provides easy to use ip/port connection to another
 * process.
 * 
 * Only one method is Connection class: start method to open up transmission
 * Other methods: send, receive and close are part of wire class extended by Connection
 */
public class Connection extends wire
	{
	String ipAddress="127.0.0.1";
	int port=1000;
	boolean alreadyConnected=false;
	boolean serverOnly=false;
	boolean serverAndClient=false;
	
	public void beginServer()
	{
	if(alreadyConnected) return;
	// set the ip and port. 
	set(ipAddress, port);
	
	// open the connection
	boolean goodConnection = open("client");
	
	// ok, if opening as a client failed that means the other side is not up yet either, open as server
	if(!goodConnection) 
		{
		goodConnection = open("server");
		if(!goodConnection)
			{
			System.out.println("Connection Failed.");
			}
		}
	
	start(); // this starts a separate thread that read the port
	alreadyConnected=true;
	serverAndClient=false;
	serverOnly=true;
	}
	
	public void begin()
		{
		if(alreadyConnected) return;
		// set the ip and port. 
		set(ipAddress, port);
		
		// open the connection
		boolean goodConnection = open("client");
		
		// ok, if opening as a client failed that means the other side is not up yet either, open as server
		if(!goodConnection) 
			{
			goodConnection = open("server");
			if(!goodConnection)
				{
				System.out.println("Connection Failed.");
				}
			}
		
			start(); // this starts a separate thread that read the port
		alreadyConnected=true;
		serverAndClient=true;
		serverOnly=false;
		}
	
	
	}
