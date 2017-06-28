package GameLobby;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class GameServer {
	private ArrayList<Integer> playerList = new ArrayList<Integer>();
	private Semaphore numConcurrentConnections = new Semaphore(20);
	private Lock tenPlayersLock = new ReentrantLock(true);
	private Condition tenPlayersCondition = tenPlayersLock.newCondition();

	public GameServer() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(6789);
			while(true) {
				System.out.println("Waiting for connection...");
				numConcurrentConnections.acquire();
				Socket s = ss.accept();
				System.out.println("Accepted connection: " + s.getInetAddress() + ":" + s.getPort());
				ServerThread st = new ServerThread(this, s);
				st.start();
			}
		} catch (IOException ioe) {
			System.out.println("IOE in GameServer constructor: " + ioe.getMessage());
		} catch (InterruptedException ie) {
			System.out.println("IE in GameServer constructor: " + ie.getMessage());
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException ioe) {
					System.out.println("IOE closing ss: " + ioe.getMessage());
				}
			}
		}
	}

	public ArrayList<Integer> addPlayerAndGetPlayerList(int num) {
		addNumber(num);
		return getPlayerList();
	}
	private void addNumber(int num) {
		tenPlayersLock.lock();
		try {
			playerList.add(num);
			if (playerList.size() % 10 == 0) {
				tenPlayersCondition.signalAll();
			}
		} finally {
			tenPlayersLock.unlock();
		}
	}
	private ArrayList<Integer> getPlayerList() {
		tenPlayersLock.lock();
		try {
			while (playerList.size() % 10 != 0) {
				System.out.println("Waiting for " + (10 - playerList.size()%10) + " player(s).");
				tenPlayersCondition.await();
			}
			return playerList;
		} catch (InterruptedException ie) {
			System.out.println("IE in GameServer.getPlayerList(): " + ie.getMessage());
		} finally {
			tenPlayersLock.unlock();
		}
		return playerList;
	}
	public Semaphore getNumConcurrentConnections() {
		return numConcurrentConnections;
	}
	public Lock getTenElementsLock() {
		return tenPlayersLock;
	}

	public static void main(String [] args) {
		new GameServer();
	}
}