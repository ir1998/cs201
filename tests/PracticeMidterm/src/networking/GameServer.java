package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import networking.GameServer;
import networking.ServerListener;

public class GameServer {
	public ServerSocket ss;
	public Semaphore numConcurrentConn = new Semaphore(20);
	public Lock lock = new ReentrantLock(true);
	public ArrayList<Integer> playerList = new ArrayList<Integer>();
	public Condition tenPlayersCondition = lock.newCondition();

	public GameServer() {
		try {
			this.ss = new ServerSocket(6789);
			while (true) {
				System.out.println("Waiting for connection...");
				this.numConcurrentConn.acquire();
				Socket s = ss.accept();
				System.out.println("Accepted connection: " + s.getInetAddress() + ":" + s.getPort());
				ServerListener sl = new ServerListener(s, this);
				sl.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addNumberToPlayerList(int id) {
		this.lock.lock();
		this.playerList.add(id);
		try{
			if(this.playerList.size() % 10 == 0){
				this.tenPlayersCondition.signalAll();
			}
		}finally{
			this.lock.unlock();
		}
	}

	public ArrayList<Integer> addPlayerAndGetList(int id) {
		addNumberToPlayerList(id);
		return getPlayerList();
	}
	
	public ArrayList<Integer> getPlayerList(){
		this.lock.lock();
		try {
			while(this.playerList.size() % 10 != 0){
				System.out.println("Waiting for " + (10 - playerList.size()%10) + " player(s).");
				this.tenPlayersCondition.await();
			}
			return this.playerList;
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("error in addplayergetlist line 61 gameserver");
		} finally{
			this.lock.unlock();
		}
		return this.playerList;
	}
	
	public void releaseConnection(){
		this.numConcurrentConn.release();
	}

	public static void main(String[] args) {
		new GameServer();
	}
}
