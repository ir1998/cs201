   import java.util.concurrent.ExecutorService;
   import java.util.concurrent.Executors;

   public class AddAPenny implements Runnable {
     private static PiggyBank piggy = new PiggyBank();
     public int num;

     public void run() {
       piggy.deposit(1, this.num);
     }

     public AddAPenny(int num){
    	 this.num = num;
     }
     
    public static void main(String [] args) {
      ExecutorService executor = Executors.newCachedThreadPool();
      for (int i=0; i < 100; i++) {
        executor.execute(new AddAPenny(i));
      }
      executor.shutdown();
      // wait until all tasks are finished
      while(!executor.isTerminated()) { }

      System.out.println("Balance = " + piggy.getBalance());
    }
  }

  class PiggyBank {
    private int balance = 0;
    public int getBalance() {
      return balance;
    }
    public synchronized void deposit(int amount, int t) {
      int newBalance = balance + amount;
      System.out.println("before yield: " + balance + " , thread: " + t);
      Thread.yield();
      balance = newBalance;
      System.out.println("before yield: " + balance + " , thread: " + t);
    }
  }