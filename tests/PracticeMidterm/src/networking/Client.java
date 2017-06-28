package networking;

public class Client extends Thread{
	public int id;
	
	public Client(int id){
		this.id = id;
	}
	
	public void run(){
		new GameClient(this.id);
	}
}
