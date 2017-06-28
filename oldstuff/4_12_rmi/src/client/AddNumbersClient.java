package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sharedobjects.AddNumbersInterface;
import sharedobjects.AddNumbersTask;

public class AddNumbersClient {
	public static void main(String args[]) {
		try {
			String name = "AddNumbers";
			long minNum = 0;
			long maxNum = 1_000_000;
			Registry registry = LocateRegistry.getRegistry("localhost");
			AddNumbersInterface remoteAddNumbers = (AddNumbersInterface) registry.lookup(name);
			AddNumbersTask ant = new AddNumbersCalculation(minNum, maxNum);
			long solution = remoteAddNumbers.addNumbers(ant);
			System.out.println(solution);
		} catch(RemoteException re){
			System.out.println("re: "+re.getMessage());
		}catch(NotBoundException nbe){
			System.out.println("nbe: "+nbe.getMessage());
		} catch (Exception e) {
			System.err.println("AddNumbersClient exception:");
			e.printStackTrace();
		}
	}
}