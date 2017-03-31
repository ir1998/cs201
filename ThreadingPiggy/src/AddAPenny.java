   import java.util.concurrent.ExecutorService;
   import java.util.concurrent.Executors;

   public class AddAPenny implements Runnable {
     private static PiggyBank piggy = new PiggyBank();

     public void run() {
       piggy.deposit(1);
     }

    public static void main(String [] args) {
      ExecutorService executor = Executors.newCachedThreadPool();
      for (int i=0; i < 100; i++) {
        executor.execute(new AddAPenny());
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
    public synchronized void deposit(int amount) {
      int newBalance = balance + amount;
      Thread.yield();
      balance = newBalance;
    }
  }