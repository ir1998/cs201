package messages;

import java.io.Serializable;

public class Entry implements Serializable{
	public String player;
	public String guess;
	
	public Entry(String guess, String player){
		this.player = player;
		this.guess = guess;
	}
}
