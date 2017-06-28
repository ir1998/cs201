package client;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FactoryMailbox extends FactoryObject implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<FactoryResource> available;
	private Vector<FactoryResource> stock;
	Random rand;
	
	Lock mLock;

	protected FactoryMailbox(Vector<FactoryResource> deliveries) {
		super(Constants.mailboxString, "Mailbox" + Constants.png, 0, 0);
		available = deliveries;
		rand = new Random();
		stock = new Vector<FactoryResource>();
		mLock = new ReentrantLock();
		new Thread(this).start();
	}
	
	public FactoryResource getStock() {
		mLock.lock(); //Lock the MailBox, no concurrent modification exceptions allowed!
		if(stock.isEmpty()) //No resources to give out
		{
			mLock.unlock(); //unlock it
			return null; //buh bye
		}
		mLock.unlock(); //Unlock it
		return stock.remove(0); //return the first one and delete it from the list
	}
	
	@Override
	public void run()
	{
		while(true){ //always generate
			try{
				int toStock = Math.abs(rand.nextInt() % available.size()); //Generate an index for a random resource
				int number = Math.abs(rand.nextInt() % 25 + 1); //Generate a random amount
				FactoryResource temp = new FactoryResource(available.elementAt(toStock).getName(),number); //create a new resource
				System.out.println("Generated a " + temp.getName() + " with amount " + temp.getQuantity() + " and stored it in mailbox, currently " + stock.size() + " items in the mailbox."); //Debugging
				stock.addElement(temp); //add it to the stock
				Thread.sleep(3000); //sleep for 3 seconds real time
			} catch (InterruptedException e) {
				System.out.println("Interrupted exception in FactoryMailbox!");
				e.printStackTrace();
			}
		}
	}

}
