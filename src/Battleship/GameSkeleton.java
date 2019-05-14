package Battleship;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// this is the line that defines the class
// extends JFrame is a predefinition of a window
// implements KeyListener allows us to use the keyboard.
public class GameSkeleton extends JFrame implements KeyListener, MouseListener, MouseMotionListener
	{
	static final long serialVersionUID = 0;
	Image im;
	int xCoordinate = 300;
	int yCoordinate = 300;
	int windowWidth=600;
	int windowHeight=600;
	int squareSize = 10;
	static int tickWait=100;
	static String NameOfGame="Game Skeleton";
	
	GameSkeleton()
		{
		super(NameOfGame);
		JPanel p = new JPanel();
		this.setSize(windowWidth, windowHeight);
		p.requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
				{
				closeProcess();
				System.exit(0);
				}
		});
		}

	public void closeProcess()
	{
		
	}
	// reset all variables necessary to restart the game
	public void start()
		{
		System.out.println("Please create your own start() method");
		}

	// this routine causes the program to wait n number of milliseconds
	// we never need to modify this method.
	public void makeProgramWait(int milliseconds)
		{
		try
			{
			Thread.sleep(milliseconds);
			}
		catch (Exception e)
			{
			System.out.println("An error in sleep process.");
			}
		} 

	// this is an endless loop that causes things to happen 
	// after a certain amount of time
	public void playGame()
		{
		start();
		this.setSize(windowWidth,windowHeight);
		this.setVisible(true);
		while (true)
			{
			tick();
			makeProgramWait(tickWait);
			repaint();
			}
		}

	public void tick()
		{
		}
	
	public void paint(Graphics g)
		{
		if (im == null)
			{
			im = createImage(this.getWidth(), this.getHeight());
			}
		Graphics tempG = im.getGraphics();
		paintScreen(tempG);
		g.drawImage(im, 0, 0, this);
		}

	// this is where we draw all our graphics
	public void paintScreen(Graphics g)
		{
		draw(g);
		}

	public void draw(Graphics g)
		{
		System.out.println("executing draw from GameSkeleton. draw method not found in game.");
		}
	
	// this method is where we wait for keystrokes
	public void keyPressed(KeyEvent e)
		{
		int inKey = e.getKeyCode();
		keyDown(inKey);
		
		repaint();
		}

	public void keyUp(int inKey)
		{
		System.out.println("please create a keyUp method in your game.");
		}
	
	public void keyDown(int inKey)
		{
		System.out.println("please create a keyDown method in your game.");
		}
	
	public void mouseDown(int x, int y)
		{
		System.out.println("please create a mouseDown method in your game.");
		}
	
	public void mouseUp(int x, int y)
		{
		System.out.println("please create a mouseUp method in your game.");
		}
	
	public void mouseDrag(int x, int y)
		{
		System.out.println("please create a mouseDrag method in your game.");
		}
	
	public void mouseMove(int x, int y)
		{
		System.out.println("please create a mouseMove method in your game.");
		}
	
	public void keyReleased(KeyEvent e)
		{
		}

	public void keyTyped(KeyEvent e)
		{
		}
	
	public void mouseDragged(MouseEvent e) 
		{
		mouseDrag(e.getX(), e.getY());
		repaint();
		}

	public void mouseMoved(MouseEvent e) 
		{
		mouseMove(e.getX(), e.getY());
		}
	
	public void mouseClicked(MouseEvent e) 
		{
		}

	public void mousePressed(MouseEvent e) 
		{
		mouseDown(e.getX(), e.getY());
		repaint();
		}

	public void mouseReleased(MouseEvent e) 
		{
		mouseUp(e.getX(), e.getY());
		repaint();
		}

	public void mouseEntered(MouseEvent e) 
		{
		}
	
	public void mouseExited(MouseEvent e) 
		{
		}

	
	public static Image readImage(String imageName)
		{
	    Image image = Toolkit.getDefaultToolkit().getImage(imageName);
	    MediaTracker imageTracker = new MediaTracker(new JPanel());
	    imageTracker.addImage(image, 0);
	    try
		    	{
		    	imageTracker.waitForID(0);
		    	}
	    catch(InterruptedException e)
		    	{ 
		    	return null;
		    	}
	    return image;
		}


	}