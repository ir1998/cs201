package messages;

import java.io.Serializable;

public class PotentialUsername implements Serializable {

	private static final long serialVersionUID = 1L;
	public String username, game;
	
	public PotentialUsername(String username, String game){
		this.username = username;
		this.game = game;
	}
	
}
