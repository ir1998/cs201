package client;

public class FactoryStockperson extends FactoryWorker {
	
	private static final long serialVersionUID = 1L;
	
	private FactoryResource toStock;

	public FactoryStockperson(int inNumber, FactoryNode startNode, Factory inFactory) {
		super(inNumber, startNode, inFactory);
		setImage("Stockperson" + Constants.png);
		setName("Stockperson " + (inNumber - inFactory.getNumberOfWorkers()));
	}

	@Override
	public void run()
	{
		mLock.lock();
		try
		{
			// get an assignment from the table
			mDestinationNode = mFactory.getNode("Task Board");
			mShortestPath = currentNode.findShortestPath(mDestinationNode);
			// SEND SHORTEST PATH TO FRONT END
			mFactory.sendWorkerMoveToPath(this, mShortestPath);
			// all workers need to go to the Task Board in order to
			// acquire a task
			atLocation.await(); // waiting for animation
			
			while (true)
			{
				if (toStock == null)
				{
					mDestinationNode = mFactory.getNode(Constants.mailboxString);
					mShortestPath = currentNode.findShortestPath(mDestinationNode);
					// SEND SHORTEST PATH TO FRONT END
					mFactory.sendWorkerMoveToPath(this, mShortestPath);
					atLocation.await(); // waiting for animation
					while (!mDestinationNode.aquireNode())
					Thread.sleep(1);
					toStock = mFactory.getMailbox().getStock();
					Thread.sleep(1000);
					mDestinationNode.releaseNode();
					//update image
					if(toStock != null) {
						setImage("Stockperson_withBox" + Constants.png);
					}
				}
				else
				{
					mDestinationNode = mFactory.getNode(toStock.getName());
					mShortestPath = currentNode.findShortestPath(mDestinationNode);
					mFactory.sendWorkerMoveToPath(this, mShortestPath);
					atLocation.await();
					FactoryResource toGive = (FactoryResource) mDestinationNode.getObject();
					toGive.giveResource(toStock.getQuantity());
					mFactory.sendResources();
					toStock = null;
					setImage("Stockperson" + Constants.png);
				}
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		mLock.unlock();
	}


}
