package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
	public int id;
	public GameServer gs;
	public Socket s;
	public ObjectOutputStream oos;
	public BufferedReader br;

	public ServerThread(GameServer gs, Socket s) {
		this.gs = gs;
		this.s = s;
	}

	public void run() {
		try {
			this.br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			this.id = Integer.parseInt(this.br.readLine());

			// must lock this because we cannot wait for 100 ois's at the same
			// time, esp given the waiting time for each one within the
			// getPLayerList function
			this.gs.lock.lock();
			ArrayList<Integer> players = this.gs.addPlayerAndGetList(this.id);
			this.oos = new ObjectOutputStream(this.s.getOutputStream());
			this.oos.writeObject(players);
			this.oos.flush();
			this.gs.lock.unlock();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//release this here because we no longer are using this thread
			//on the server
			this.gs.numConcurrentConns.release();
			System.out.println("Releasing permit on thread: " + this.id);
			try {
				this.br.close();
				this.oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
