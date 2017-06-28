package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameClient {
	public int id;
	public Socket s;
	public PrintWriter pw;
	public ObjectInputStream oos;
	public Lock lock = new ReentrantLock();
	
	public GameClient(int id){
		this.id = id;
		try {
			s = new Socket("localhost", 6789);
			
			//sends int to server listener
			this.pw = new PrintWriter (s.getOutputStream());
			this.pw.println(id);
			this.pw.flush();
			
			this.lock.lock();
			this.oos = new ObjectInputStream(s.getInputStream());
			ArrayList<Integer> players = (ArrayList<Integer>)this.oos.readObject();
			System.out.print("Player " + this.id + ": " + players.size() + " in game - ");
			for(int i = 0; i< players.size(); i++){
				System.out.print(players.get(i));
				if(i != players.size() - 1){
					System.out.print(", ");
				}
			}
			System.out.println("");
			this.lock.unlock();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				this.oos.close();
				this.pw.close();
				this.s.close();
			} catch (IOException e) {
				System.out.println("error in GameClient 55");
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String [] args){
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=1; i<101; i++){
			System.out.println("Creating player " + i);
			es.execute(new Client(i));
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		es.shutdown();
		try {
			Thread.sleep(2000);
			es.awaitTermination(2000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
