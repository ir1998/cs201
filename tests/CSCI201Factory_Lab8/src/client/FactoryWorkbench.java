package client;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FactoryWorkbench extends FactoryObject {

	private static final long serialVersionUID = 1L;
	
	private Lock mLock;
	
	public FactoryWorkbench(int x, int y) {
		super(Constants.workbenchString, "Workbench" + Constants.png, x, y);
		mLock = new ReentrantLock();
	}
	
	public void lock()
	{
		mLock.lock();
	}
	
	public void unlock()
	{
		mLock.unlock();
	}
	
	public void assemble(FactoryProduct mProductToMake) throws InterruptedException
	{
		for(int i = 0; i < mProductToMake.getResourcesNeeded().size(); ++i)
		{
			Thread.sleep(500);
		}
	}
}



