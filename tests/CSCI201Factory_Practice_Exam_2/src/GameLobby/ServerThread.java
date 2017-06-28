package GameLobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

class ServerThread extends Thread {
	private Socket s;
	private GameServer ns;
	public ServerThread(GameServer ns, Socket s) {
		this.s = s;
		this.ns = ns;
	}
	public void run() {
		int num = -1;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			num = Integer.parseInt(br.readLine());
			try {
				ns.getTenElementsLock().lock();
				ArrayList<Integer> players = ns.addPlayerAndGetPlayerList(num);
				oos.writeObject(players);
				oos.flush();
			} finally {
				ns.getTenElementsLock().unlock();
			}
			oos.close();
			br.close();
			s.close();
			Thread.sleep(100);
		} catch (IOException ioe) {
			System.out.println("IOE in ServerThread.run(): " + ioe.getMessage());
		} catch (InterruptedException ie) {
			System.out.println("IE in ServerThread.run(): " + ie.getMessage());
		} finally {
			System.out.println("Releasing permit on thread " + num);
			ns.getNumConcurrentConnections().release();
		}
	}
}