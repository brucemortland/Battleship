package Battleship;

public class BothSides
    {
    public static void main(String args[])
        {
        //create aPrintThread objects
    	Process thread1 = new Process();
        Process thread2 = new Process();
       
        System.out.println("\nStarting threads");
        
        // start executing PrintThreads
        thread1.start();
        thread2.start();
        
        System.out.println("Threads started\n");
        }
    
    } // end of class ThreadTester
    
// Each object of this class picks a random sleep interval. 
// When a PrintThread executes, it prints its name, sleeps, 
// prints its name again and terminates.

class Process extends Thread 
   {

       
   public void run()
       {
      new Battleship().playGame();
      
      }
   }
   

