package Battleship;
public class m15 
	{
	public static void main(String[] args)
		{
		int total=0;
		for(int x=0;x<1000000000;x++)
			{
			if((x%15== 0) && (x%3==0 && x%5==0)) 
				{
				total++;
				};
			}
		System.out.println(total);
		}
	}
