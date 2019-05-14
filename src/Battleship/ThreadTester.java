package Battleship;
/**
 * Write a description of class ThreadTester here.
 * 
 * @author BruceMortland 
 * @version 2003-11-24
 */

// Modification History
//
// 2003-11-24 Example from Deitel and Deitel, Java How to Program, 4th ed., pg. 845

public class ThreadTester
    {
    public static void main(String args[])
        {
        //create aPrintThread objects
        PrintThread thread1 = new PrintThread("thread1");
        PrintThread thread2 = new PrintThread("thread2");
        PrintThread thread3 = new PrintThread("thread3");
        PrintThread thread4 = new PrintThread("thread4");
        System.out.println("\nStarting threads");
        
        // start executing PrintThreads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        
        System.out.println("Threads started\n");
        }
    
    } // end of class ThreadTester
    
// Each object of this class picks a random sleep interval. 
// When a PrintThread executes, it prints its name, sleeps, 
// prints its name again and terminates.

class PrintThread extends Thread 
   {
   private int sleepTime;
   
   // PrintThread constructor assign name to thread 
   // by calling superclass Thread constructor
   
   public PrintThread(String name)
       {
       super(name);
       
       // sleep between 0 and 5 seconds
       sleepTime = (int) (Math.random()*5000);
       
       // display name and sleepTime
       System.out.println("Name: "+getName()+" going to sleep");
       }
       
   public void run()
       {
       // put thread to sleep for a random interval
       try 
           {
           System.out.println(getName()+" going to sleep");
           
           // put thread to sleep
           Thread.sleep(sleepTime);
           }
       // if thread interrupted during sleep, catch exception and 
       // display error message
       catch (InterruptedException ie)
           {
           System.out.println(ie.toString());
           }
      
      // print thread name
      System.out.println(getName() + "done sleeping");
      }
   }
   

