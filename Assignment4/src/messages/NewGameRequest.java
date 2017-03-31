package messages;

import java.io.Serializable;

public class NewGameRequest implements Serializable{
	public String name;
	public String username;
	public int players;
	
	public NewGameRequest(String username, String name, int players){
		this.name = name;
		this.players = players;
		this.username = username;
	}
}
