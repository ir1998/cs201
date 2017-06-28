package networking;

public class ClientThread extends Thread{
	public int id;
	
	public ClientThread(int id){
		this.id = id;
	}
	
	public void run(){
		new GameClient(this.id);
	}
}
