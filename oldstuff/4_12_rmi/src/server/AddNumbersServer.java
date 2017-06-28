package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import sharedobjects.AddNumbersInterface;
import sharedobjects.AddNumbersTask;

public class AddNumbersServer implements AddNumbersInterface {
	public long addNumbers(AddNumbersTask ant) {
		System.out.println("Adding numbers from " + ant.getMinimum() + " to " + ant.getMaximum() + " on server");
		return ant.getSum();
	}

	public static void main(String[] args) {
		try {
			String name = "AddNumbers";
			AddNumbersInterface remoteAddNumbers = new AddNumbersServer();
			// 0 is the port on which the AddNumbersInterface is exported
			AddNumbersInterface stub = (AddNumbersInterface) UnicastRemoteObject.exportObject(remoteAddNumbers, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(name, stub);
			System.out.println("AddNumbers remote object bound");
		} catch (Exception e) {
			System.err.println("AddNumbersServer exception:");
			e.printStackTrace();
		}
	}
}