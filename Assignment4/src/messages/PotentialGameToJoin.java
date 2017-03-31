package messages;

import java.io.Serializable;

public class PotentialGameToJoin implements Serializable {
	private static final long serialVersionUID = 1L;
	public String name;
	
	public PotentialGameToJoin(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
}
