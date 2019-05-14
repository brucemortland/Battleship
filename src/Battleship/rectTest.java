package Battleship;

import java.awt.Rectangle;

public class rectTest {
public static void main(String[] args)
{
	Rectangle r = new Rectangle(50,50,50,50);
	Rectangle s = new Rectangle(50,50,200,200);
	
	System.out.println(s.contains(r));
}
}
