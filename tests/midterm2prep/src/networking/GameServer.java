package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameServer {
	public ServerSocket ss;
	public ArrayList<Integer> playerList = new ArrayList<Integer>();
	public Lock lock = new ReentrantLock(true);
	public Condition condition = lock.newCondition();
	public Semaphore numConcurrentConns = new Semaphore(20);

	public GameServer() {
		this.ss = null;
		try {
			this.ss = new ServerSocket(6789);
			while (true) {
				System.out.println("Waiting for connection...");
				Socket s = this.ss.accept();
				System.out.println("Connection from:" + s.getInetAddress() + ":" + s.getPort());
				this.numConcurrentConns.acquire();
				ServerThread st = new ServerThread(this, s);
				st.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				this.ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Integer> addPlayerAndGetList(int id) {
		addPlayer(id);
		return getPlayerList();
	}

	public void addPlayer(int id) {
		this.lock.lock();
		//no other threads can add to playerList at this time
		this.playerList.add(id);
		if (this.playerList.size() % 10 == 0) {
			this.condition.signalAll();
		}
		this.lock.unlock();
	}

	public ArrayList<Integer> getPlayerList() {
		this.lock.lock();
		while (this.playerList.size() % 10 != 0) {
			System.out.println("Waiting for " + (10 - this.playerList.size() % 10) + " player(s)");
			try {
				//waits to be signaled from another thread
				this.condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.lock.unlock();
		return this.playerList;
	}

	public static void main(String[] args) {
		new GameServer();
	}
}
