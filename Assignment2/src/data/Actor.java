package data;

public class Actor {
	private String fname;
	private String lname;
	private String image;
	
	public Actor(){

	}
	
	public String getName() {
		return fname + " " + lname;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
