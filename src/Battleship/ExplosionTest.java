
package Battleship;

import java.awt.*;
import java.awt.event.*;

public class ExplosionTest extends GameSkeleton
	{
	static final long serialVersionUID = 0;
	Explosion e = new Explosion();
	
	public void start()
		{
		e.start(300,300);
		}
	
	public void tick()
		{
		e.tick();
		}
	
	public void draw(Graphics g)
		{
		e.draw(g);
		}
	
	public static void main(String args[])
		{
		new ExplosionTest().playGame();
		}
	}