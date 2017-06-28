package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerListener extends Thread{
	public Socket s;
	public GameServer gs;
	public ObjectOutputStream oos;
	public BufferedReader bw;
	public int id;
	
	public ServerListener(Socket s, GameServer gs){
		this.s = s;
		this.gs = gs;
	}
	
	public void run(){
		try {
			this.bw = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			this.id = Integer.parseInt(this.bw.readLine());
			try{
				this.gs.lock.lock();
				ArrayList<Integer> players = this.gs.addPlayerAndGetList(this.id);
				this.oos = new ObjectOutputStream(this.s.getOutputStream());
				this.oos.writeObject(players);
				this.oos.flush();
			}finally{
				this.gs.lock.unlock();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error in servlistener: " + this.id);
		}finally{
			System.out.println("Releasing permit on thread: " + this.id);
			this.gs.releaseConnection();
			//this.gs.lock.unlock();
			try {
				if(this.bw != null)
					this.bw.close();
				if(this.oos != null)
					this.oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
