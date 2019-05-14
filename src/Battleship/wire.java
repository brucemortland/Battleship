package Battleship;
// wire.java 

// the wire class allows users with a minimum of overhead set up 
// a server process and a client process. There are only four simple 
// methods: wireOpen, wireClose, wireSend and wireReceive. If two 
// processes are to communicate, one process must open as the server 
// and the other process must open as the client. User is responsible 
// for managing which process speaks to the other first and for 
// managing situations where both sides try to write or both are waiting 
// for a read to happen.
 
// modification history

/* 2002-03-15 	BLM	taken from Deitel & Deitel, Fig. 21.3: Server.java
 * 2002-03-18 	BLM modified to be text only
 * 2002-03-26 	BLM 	modified to exist as callable functions
 * 2019-04-13 	BLM	modified to createa threaded receive so that process 
 * 					doesn't lock 
 */

// ip addresses

//       same machine: 127.0.0.1

import java.io.*;
import java.net.*;
public class wire extends Thread
	{
	String messageTrail[] = new String[1000];
	int messageCount=0;
	PrintWriter output=null;
	BufferedReader input=null;
	ServerSocket server=null;	
	Socket connection=null;
	String errorMessage="";
	String serverOrClient;
	String ip;
	int port;
	boolean connectionOpenedSuccessfully=false;

	 protected wire()
		{
        ip = "127.0.0.1";
        port = 5000;
		}

	public void set(String i, int p)
		{
		ip = i;
		port = p;
		addMessage("wire set ip="+i+", port="+p);
		}

	public boolean open(String type)
      	{
		serverOrClient = type;
		try 
         	{
			// Create a Socket to make connection.
			if(type.equals("server"))
            	{
				server = new ServerSocket(port, 100);
				connection = server.accept();
            	}
			else
            	{
				connection = new Socket(InetAddress.getByName( ip ), port );
            	}
            
			// Get the input and output streams.
			output = new PrintWriter(connection.getOutputStream(),true);
			output.flush();
			input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         	}
		catch ( EOFException eof)
			{
			errorMessage="wire: open: EOF Exception";
			addMessage(errorMessage);
			return(false);
			}
		catch ( IOException e)
			{
			errorMessage="wire: open: IO Exception";
			addMessage(errorMessage);
			return(false);
			}

		connectionOpenedSuccessfully=true;
		addMessage("wire open");
		return(true);   
      	}	  

	public boolean isWireReady()
		{
		return connectionOpenedSuccessfully;	
		}
	
	public void list()
      	{
		for(int x=0;x<messageCount;x++)
			{
			System.out.println(messageTrail[x]);
			}
      	}   

	public boolean send(String text)
      	{
		try
         	{
			output.println(text);
         	}
		catch ( Exception e)
			{
			errorMessage="EOF Exception";
			return(false);
			}
		addMessage("wire send:"+text);
		return(true);
      	}

	public String receive()
      	{
		String text="";
		try
			{
			text = (String) input.readLine();
			}
		catch ( Exception e)
			{
			errorMessage="Exception thrown";
			}
		addMessage("wire received:"+text);
		return(text);
      	}

	String textFromPort=null;
	public void run()
		{
		while(true)
			{
			try // to slow down process
				{
				Thread.sleep(100);
				}
			catch(Exception e)
				{
				System.out.println("wire: Error from Thread.sleep");
				}
			if(textFromPort==null)
				{
				textFromPort = receive();
				}
			}
		}
	public String read()
		{
		String a = textFromPort;
		textFromPort=null;
		return a;
		}
	public void close()
      	{
		try 
			{
			if(output != null)
				output.close();
			if(input != null)
				input.close();
			if(connection != null)
				connection.close();
			if(server != null)
				server.close();
			}
		catch ( IOException e)
			{
			errorMessage="IO Exception";
			return;
			}
		addMessage("wire closed");
      	}

	public void addMessage(String text)
		{
		if(messageCount < 999)
			{
			messageCount++;
			}
		else
			{
			for(int x=0;x<999;x++)
				{
				messageTrail[x]=messageTrail[x];
				}
			}
		messageTrail[messageCount] = text+" ("+errorMessage+")";
		}
	}
