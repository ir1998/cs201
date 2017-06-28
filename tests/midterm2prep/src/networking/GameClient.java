package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameClient { 
	public Socket s;
	public ObjectInputStream ois;
	public PrintWriter pw;
	public int id;
	private static Lock lock = new ReentrantLock();
	
	public GameClient(int id){
		this.id = id;
		try{
			this.s = new Socket("localhost", 6789);
			this.pw = new PrintWriter(this.s.getOutputStream());
			this.pw.println(this.id);
			this.pw.flush();
			this.lock.lock();
			//must be locked so we dont have too many streams at once
			this.ois = new ObjectInputStream(this.s.getInputStream());
			ArrayList<Integer> players = (ArrayList<Integer>) this.ois.readObject();
			System.out.print("Player " + this.id + ": " + players.size() +" in game - ");
			for(int i=0;i<players.size(); i++){
				System.out.print(i);
				if(i != players.size() -1){
					System.out.print(",");
				}
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			this.lock.unlock();
			this.pw.close();
			try {
				this.ois.close();
				this.s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 1; i < 101; i++) {
			es.execute(new ClientThread(i));
			System.out.println("Creating player " + i);
			try {
				//prevents threads from overloading server(?)
				//sleeps this main() method
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//ends threads after they have all finished
		es.shutdown();
	}
}
