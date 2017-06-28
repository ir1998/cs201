package GameLobby;
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
	private static Lock lock = new ReentrantLock();
	public GameClient(int id) {
		try {
			Socket s = new Socket("localhost", 6789);
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			pw.println(id);
			pw.flush();
			lock.lock();
			try {
				ArrayList<Integer> players = (ArrayList<Integer>)ois.readObject();
				//align print statements
				if(id < 10) {
					System.out.print("Player  " + id + ": " + players.size() + " in game - ");
				} else if (id == 100) {
					System.out.print("Player " + id + ":" + players.size() + " in game - ");
				} else {
					System.out.print("Player " + id + ": " + players.size() + " in game - ");
				}
				for (int i=0; i < players.size(); i++) {
					System.out.print(players.get(i));
					if (i < players.size() - 1) {
						System.out.print(",");
					}
				}
				System.out.println();
			} finally {
				lock.unlock();
			}
			pw.close();
			ois.close();
			s.close();
		} catch (IOException ioe) {
			System.out.println("IOE in GameClient constructor: " + ioe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("CNFE in GameClient constructor: " + cnfe.getMessage());
		}
	}
	public static void main(String [] args) {
		ExecutorService executors = Executors.newCachedThreadPool();
		for (int i=1; i < 101; i++) {
			System.out.println("Creating Player " + i);
			executors.execute(new ClientThread(i));
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executors.shutdown();
	}
}
