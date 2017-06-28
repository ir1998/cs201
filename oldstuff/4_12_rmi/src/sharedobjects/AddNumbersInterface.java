package sharedobjects;

import java.rmi.Remote;
import java.rmi.RemoteException;

// this interface is implemented by the server
// it should have a method that takes an object that was implemented on the client
// that object will have the code in it that will be executed on the server
public interface AddNumbersInterface extends Remote {
	public long addNumbers(AddNumbersTask ant) throws RemoteException;
}