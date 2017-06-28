package client;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CSCI-201 Web Factory
 * FactoryResource.java
 * Purpose: FactoryResource holds information about its quantity
 * 
 * @version 2.0
 * @since 01/12/2017
 */
public class FactoryResource extends FactoryObject {
	private static final long serialVersionUID = 1L;
	
	private int quantity;
	Lock mLock;
	Condition isEmpty;

	// CONSTRUCTORS
	public FactoryResource(Factory inFactory, String name, int quantity, int x, int y) {
		super(name, name + Constants.png, x, y);
		setName(name);
		setQuantity(quantity);
		mLock = new ReentrantLock();
		isEmpty = mLock.newCondition();
	}
	public FactoryResource(String name, int quantity) {
		super(name, name + Constants.png);
		setQuantity(quantity);
	}

	// QUANTITY
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void takeResource(int amount) throws InterruptedException {
		mLock.lock();
		while(amount > getQuantity()) isEmpty.await();
		setQuantity(this.quantity - amount);
		mLock.unlock();
	}
	
	public void giveResource(int amount)
	{
		mLock.lock();
		setQuantity(this.quantity + amount);
		isEmpty.signalAll();
		mLock.unlock();
	}

	// UTILITIES
	public String toString() {
		return utilities.Constants.resourceString + ": " + name + " has quantity " + quantity;
	}
	public boolean equals(FactoryResource resource) {
		return (getName().equals(resource.getName()));
	}
}
